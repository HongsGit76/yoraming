"use strict";

const db = require("../../config/db");

// 쿼리문
const deleteQuery = "DELETE FROM etc WHERE user_id = ? AND etc_id = ?";
const insertQuery =
  "INSERT INTO etc(user_id, etc_title, etc_content, etc_date) VALUES(?,?,?,?)";
const getQuery = "SELECT * FROM etc WHERE user_id = ?";

// 스펙 모델 클래스
class SpecCertModel {
  constructor(body) {
    this.body = body;
  }

  async addEtcSpec() {
    // API
    const user_id = this.body.user_id;
    const title = this.body.title;
    const content = this.body.content;
    const date = this.body.date;

    return new Promise((resolve, reject) => {
      db.query(insertQuery, [user_id, title, content, date], (err) => {
        if (err) console.log(err);
        resolve({ success: true });
      });
    });
  }

  async test() {
    return { success: true };
  }
}

module.exports = SpecCertModel;
