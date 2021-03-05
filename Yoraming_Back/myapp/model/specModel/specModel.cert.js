"use strict";

const db = require("../../config/db");

// 쿼리문
const deleteQuery =
  "DELETE FROM certificate WHERE user_id = ? AND certificate_id = ?";
const insertQuery =
  "INSERT INTO certificate(user_id, certificate_name, certificate_date, certificate_endDate) VALUES(?,?,?,?)";
const getQuery = "SELECT * FROM certificate WHERE user_id = ?";

// 스펙 모델 클래스
class SpecCertModel {
  constructor(body) {
    this.body = body;
  }

  async addCertSpec() {
    // API
    const user_id = this.body.user_id;
    const certificate_name = this.body.certificate_name;
    const certificate_date = this.body.certificate_date;
    const certificate_endDate = this.body.certificate_endDate;

    // Database 쿼리문 수행
    return new Promise((resolve, reject) => {
      db.query(
        insertQuery,
        [user_id, certificate_name, certificate_date, certificate_endDate],
        (err) => {
          if (err) console.log(err);
          resolve({ success: true });
        }
      );
    });
  }

  async deleteCertSpec() {
    // API
    const user_id = this.body.user_id;
    const certificate_id = this.body.certificate_id;

    return new Promise((resolve, reject) => {
      db.query(deleteQuery, [user_id, certificate_id], (err) => {
        if (err) console.log(err);
        resolve({ success: true });
      });
    });
  }

  async getCertSpec() {
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

  async editCertSpec() {
    return { success: true };
  }
}

module.exports = SpecCertModel;
