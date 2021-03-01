const { resolveInclude } = require("ejs");
const db = require("../config/db");

class sbjtModel {
  static addSbjt(data) {
    return new Promise(async (resolve, reject) => {
      for (let i in data) {
        let ComSbjt_id = await this.insertComSbjt(data[i]);
        await this.insertRecSbjt(ComSbjt_id, data[i].recognitionSbjt);
      }
      resolve();
    });
  }

  static insertComSbjt(sbjt) {
    return new Promise((resolve, reject) => {
      db.query(
        "INSERT INTO completeSbjt(user_id, comSbjt_name, comSbjt_date, comSbjt_credit, comSbjt_grade) VALUES(?, ?, ?, ?, ?)",
        [
          sbjt.user_id,
          sbjt.comSbjt_name,
          sbjt.comSbjt_date,
          sbjt.comSbjt_credit,
          sbjt.comSbjt_grade,
        ],
        (err) => {
          if (err) console.log(err);
          db.query("SELECT LAST_INSERT_ID()", (err, data) => {
            if (err) console.log(err);
            resolve(data[0]["LAST_INSERT_ID()"]);
          });
        }
      );
    });
  }

  static insertRecSbjt(ComSbjt_id, recSbjt) {
    return new Promise((resolve, reject) => {
      for (let i in recSbjt) {
        let curData = recSbjt[i];
        db.query(
          "INSERT INTO recognitionSbjt(comSbjt_id, recSbjt_recCategory, yoram_id) VALUES(?,?,?)",
          [ComSbjt_id, curData.recSbjt_recCategory, curData.yoram_id],
          (err) => {
            if (err) console.log(err);
            resolve();
          }
        );
      }
    });
  }

  static delSbjt(user_id, date) {
    return new Promise((resolve) => {
      db.query(
        "DELETE FROM completeSbjt WHERE user_id = ? AND comSbjt_date = ?",
        [user_id, date],
        (err) => {
          if (err) console.log(err);
          db.query(
            "DELETE FROM recognitionSbjt WHERE comSbjt_id IS NULL",
            (err) => {
              if (err) console.log(err);
              resolve("해당 학기 수강 과목이 삭제됐습니다.");
            }
          );
        }
      );
    });
  }
}

module.exports = sbjtModel;
