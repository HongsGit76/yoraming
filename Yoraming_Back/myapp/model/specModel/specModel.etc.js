"use strict";

// 모듈
const db = require("../../config/db");

// 쿼리문
const deleteQuery = "DELETE FROM etc WHERE user_id = ? AND etc_id = ?";
const insertQuery =
  "INSERT INTO etc(user_id, etc_title, etc_content, etc_date) VALUES(?,?,?,?)";
const getQuery = "SELECT * FROM etc WHERE user_id = ?";
const updateQuery =
  "UPDATE etc SET etc_title=?, etc_content=?, etc_date=? WHERE etc_id=?";

// 스펙 모델 클래스
class SpecEtcModel {
  constructor(body) {
    this.body = body;
  }

  async addEtcSpec() {
    // API
    const user_id = this.body.user_id;
    const etc_title = this.body.etc_title;
    const etc_content = this.body.etc_content;
    const etc_date = this.body.etc_date;

    // Database 쿼리문 수행
    return new Promise((resolve, reject) => {
      db.query(
        insertQuery,
        [user_id, etc_title, etc_content, etc_date],
        (err) => {
          if (err) {
            console.log(err);
            resolve();
          } else {
            db.query("SELECT LAST_INSERT_ID()", (err, rows) => {
              if (err) console.log(err);
              resolve({
                etc_id: rows[0]["LAST_INSERT_ID()"],
                success: true,
              });
            });
          }
        }
      );
    });
  }

  async deleteEtcSpec() {
    // API
    const user_id = this.body.user_id;
    const etc_id = this.body.etc_id;

    return new Promise((resolve, reject) => {
      db.query(deleteQuery, [user_id, etc_id], (err) => {
        if (err) console.log(err);
        resolve({ success: true });
      });
    });
  }

  async getEtcSpec() {
    // API
    const user_id = this.body.user_id;

    return new Promise((resolve, reject) => {
      db.query(getQuery, [user_id], (err, rows, fields) => {
        if (err) console.log(err);
        rows.success = true;
        console.log("rows: ", rows);
        resolve(rows);
      });
    });
  }

  async editEtcSpec() {
    // API
    const etc_id = this.body.etc_id;
    const etc_title = this.body.etc_title;
    const etc_content = this.body.etc_content;
    const etc_date = this.body.etc_date;

    return new Promise((resolve, reject) => {
      db.query(
        updateQuery,
        [etc_title, etc_content, etc_date, etc_id],
        (err) => {
          if (err) console.log(err);
          resolve({ etc_id: etc_id, success: true });
        }
      );
    });
  }
  //return { success: true };
}

module.exports = SpecEtcModel;
