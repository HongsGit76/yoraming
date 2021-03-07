"use strict";

const { resolveInclude } = require("ejs");
const { connect } = require("../config/db");
const db = require("../config/db");
const request = require("request");
const cheerio = require("cheerio");

const findNULLQuery = "SELECT * FROM notice WHERE user_id = 'NULLitem'";
const findIndexQuery = "SELECT COUNT(*) FROM notice";
const insertQuery =
  "INSERT INTO notice(notice_id, user_id, notice_keyword) VALUES(?, ?, ?)";
const insertNULLQuery =
  "UPDATE notice SET user_id = ?, notice_keyword = ? WHERE notice_id = ?";
const deleteQuery =
  "UPDATE notice SET user_id = 'NULLitem' WHERE notice_id = ?";
const getQuery = " SELECT * FROM notice WHERE user_id = ?";

class noticeModel {
  constructor(req, JSONtype) {
    if (JSONtype === true) this.body = req.body;
    else this.query = req.query;
  }
  async curnoticeIndex() {
    return new Promise((resolve, reject) => {
      db.query(findNULLQuery, (err, data) => {
        if (err) resolve("err");
        else if (data.length === 0) {
          db.query(findIndexQuery, (err, data) => {
            if (err) resolve("err");
            else
              resolve({
                idx: JSON.stringify(data[0]["COUNT(*)"]),
                type: "NotNullItem",
              });
          });
        } else
          resolve({
            idx: JSON.stringify(data[0].notice_id - 1),
            type: "NullItem",
          });
      });
    });
  }

  async postNotice() {
    return new Promise(async (resolve, reject) => {
      const curidx = await this.curnoticeIndex();
      const newidx = Number(curidx.idx) + 1;
      if (curidx === "err") resolve({ success: false, msg: "indexErr" });
      else if (curidx.type === "NotNullItem") {
        db.query(
          insertQuery,
          [newidx, this.body.user_id, this.body.notice_keyword],
          (err) => {
            if (err) resolve({ success: false, msg: "postNotNullErr" });
            else resolve({ success: true, noticeid: newidx });
          }
        );
      } else {
        db.query(
          insertNULLQuery,
          [this.body.user_id, this.body.notice_keyword, newidx],
          (err) => {
            if (err) resolve({ success: false, msg: "postNullErr" });
            else resolve({ success: true, noticeid: newidx });
          }
        );
      }
    });
  }

  async delNotice() {
    return new Promise((resolve, reject) => {
      db.query(deleteQuery, [this.query.notice_id], (err) => {
        if (err) resolve({ success: false, msg: "deleteErr" });
        else resolve({ success: true });
      });
    });
  }
  async getNotice() {
    return new Promise((resolve, reject) => {
      db.query(getQuery, [this.query.user_id], (err, data) => {
        console.log(JSON.stringify(data));
        if (err) resolve({ success: false, msg: "getErr" });
        else resolve({ success: true, notice: data });
      });
    });
  }

  static async getNoticeAll() {
    return new Promise((resolve, reject) => {
      process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0";
      const options = {
        method: "GET",
        url: "https://www.ajou.ac.kr/kr/ajou/notice.do",
        headers: {
          "User-Agent":
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36",
        },
      };
      request(options, function (error, res, body) {
        if (error) reject(error);
        //  이렇게 $에 html 문서를 담아야 제이쿼리처럼 사용가능
        const $ = cheerio.load(body);

        const data = { num: [], title: [], link: [], div: [] };

        $(".b-title-box a").each((index, item) => {
          data.title.push($(item).text().trim());
        });

        $(".b-title-box a").each((index, item) => {
          data.link.push(
            `https://www.ajou.ac.kr/kr/ajou/notice.do${$(item).attr("href")}`
          );
        });

        $(".b-num-box").each((index, item) => {
          data.num.push($(item).text().trim());
        });

        $(".b-cate").each((index, item) => {
          data.div.push($(item).text().trim());
        });
        resolve(data);
      });
    });
  }
}

module.exports = noticeModel;
