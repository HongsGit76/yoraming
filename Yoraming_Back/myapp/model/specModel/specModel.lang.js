"use strict";

const db = require("../../config/db");

// 쿼리문
const deleteQuery =
  "DELETE FROM language WHERE user_id = ? AND language_id = ?";
const insertQuery =
  "INSERT INTO language(user_id, language_name, language_grade, language_date) VALUES(?,?,?,?)";
const getQuery = "SELECT * FROM language WHERE user_id = ?";
const updateQuery =
  "UPDATE language SET language_name=?, language_grade=?, language_date=? WHERE language_id=?";

// 스펙 모델 클래스
class SpecLangModel {
  constructor(body) {
    this.body = body;
  }

  async addLangSpec() {
    // API
    const user_id = this.body.user_id;
    const language_name = this.body.language_name;
    const language_grade = this.body.language_grade;
    const language_date = this.body.language_date;

    // Database 쿼리문 수행
    return new Promise((resolve, reject) => {
      db.query(
        insertQuery,
        [user_id, language_name, language_grade, language_date],
        (err) => {
          if (err) {
            console.log(err);
            resolve();
          } else {
            db.query("SELECT LAST_INSERT_ID()", (err, rows) => {
              if (err) console.log(err);
              resolve({
                language_id: rows[0]["LAST_INSERT_ID()"],
                success: true,
              });
            });
          }
        }
      );
    });
  }

  async deleteLangSpec() {
    // API
    const user_id = this.body.user_id;
    const language_id = this.body.language_id;

    return new Promise((resolve, reject) => {
      db.query(deleteQuery, [user_id, language_id], (err) => {
        if (err) console.log(err);
        resolve({ success: true });
      });
    });
  }

  async getLangSpec() {
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

  async editLangSpec() {
    // API
    const language_id = this.body.language_id;
    const language_name = this.body.language_name;
    const language_grade = this.body.language_grade;
    const language_date = this.body.language_date;

    return new Promise((resolve, reject) => {
      db.query(
        updateQuery,
        [language_name, language_grade, language_date, language_id],
        (err) => {
          if (err) console.log(err);
          resolve({ language_id: language_id, success: true });
        }
      );
    });
  }
}

module.exports = SpecLangModel;
