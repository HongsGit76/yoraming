"use strict";

const db = require("../../config/db");

// 쿼리문
const deleteQuery = "DELETE FROM career WHERE user_id = ? AND career_id = ?";
const insertQuery =
  "INSERT INTO career(user_id, career_office, career_content, career_date) VALUES(?,?,?,?)";
const getQuery = "SELECT * FROM career WHERE user_id = ?";

// 스펙 모델 클래스
class SpecCertModel {
  constructor(body) {
    this.body = body;
  }

  async addCareerSpec() {
    // API
    const user_id = this.body.user_id;
    const career_office = this.body.career_office;
    const career_content = this.body.career_content;
    const career_date = this.body.career_date;

    // Database 쿼리문 수행
    return new Promise((resolve, reject) => {
      db.query(
        insertQuery,
        [user_id, career_office, career_content, career_date],
        (err) => {
          if (err) console.log(err);
          resolve({ success: true });
        }
      );
    });
  }

  async deleteCareerSpec() {
    // API
    const user_id = this.body.user_id;
    const career_id = this.body.career_id;

    return new Promise((resolve, reject) => {
      db.query(deleteQuery, [user_id, career_id], (err) => {
        if (err) console.log(err);
        resolve({ success: true });
      });
    });
  }

  async getCareerSpec() {
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

  async editCareerSpec() {
    return { success: true };
  }
}

module.exports = SpecCertModel;
