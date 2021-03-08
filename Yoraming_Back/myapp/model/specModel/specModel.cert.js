"use strict";

const db = require("../../config/db");

// 쿼리문
const deleteQuery =
  "DELETE FROM certificate WHERE user_id = ? AND certificate_id = ?";
const insertQuery =
  "INSERT INTO certificate(user_id, certificate_name, certificate_date, certificate_endDate) VALUES(?,?,?,?)";
const getQuery = "SELECT * FROM certificate WHERE user_id = ?";
const updateQuery =
  "UPDATE certificate SET certificate_name=?, certificate_date=?, certificate_endDate=? WHERE certificate_id=?";

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
          if (err) {
            console.log(err);
            resolve();
          } else {
            db.query("SELECT LAST_INSERT_ID()", (err, rows) => {
              resolve({
                certificate_id: rows[0]["LAST_INSERT_ID()"],
                success: true,
              });
            });
          }
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
    // API
    const certificate_id = this.body.certificate_id;
    const certificate_name = this.body.certificate_name;
    const certificate_date = this.body.certificate_date;
    const certificate_endDate = this.body.certificate_endDate;

    return new Promise((resolve, reject) => {
      db.query(
        updateQuery,
        [
          certificate_name,
          certificate_date,
          certificate_endDate,
          certificate_id,
        ],
        (err) => {
          if (err) console.log(err);
          resolve({ certificate_id: certificate_id, success: true });
        }
      );
    });
  }
}

module.exports = SpecCertModel;
