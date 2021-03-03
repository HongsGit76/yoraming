const db = require("../config/db");

const selectCurIdQuery = "SELECT LAST_INSERT_ID()";

const insertComSbjtQuery =
  "INSERT INTO completeSbjt(user_id, comSbjt_name, comSbjt_date, comSbjt_credit, comSbjt_grade) VALUES(?, ?, ?, ?, ?)";
const insertRecSbjtQuery =
  "INSERT INTO recognitionSbjt(comSbjt_id, recSbjt_recCategory, yoram_id) VALUES(?,?,?)";

const delComSbjtQuery =
  "DELETE FROM completeSbjt WHERE user_id = ? AND comSbjt_date = ?";
const delRecSbjtQuery = "DELETE FROM recognitionSbjt WHERE comSbjt_id IS NULL";

const selectSbjtQuery =
  "SELECT comSbjt_name, comSbjt_date, comSbjt_credit, comSbjt_grade, recSbjt_recCategory FROM completeSbjt AS cs LEFT JOIN recognitionSbjt AS rs ON cs.comSbjt_id = rs.comSbjt_id WHERE cs.user_id = ? AND rs.yoram_id = ? ";

const selectRecSbjtQuery =
  "SELECT recSbjt_recCategory, yoram_id FROM recognitionSbjt WHERE comSbjt_id = ? AND yoram_id = ?";

class sbjtModel {
  static addSbjt(data) {
    return new Promise(async (resolve, reject) => {
      try {
        for (let i in data) {
          let ComSbjt_id = await this.insertComSbjt(data[i]);
          await this.insertRecSbjt(ComSbjt_id, data[i].recognitionSbjt);
        }
        resolve();
      } catch (error) {
        reject(error);
      }
    });
  }

  static insertComSbjt(sbjt) {
    return new Promise((resolve, reject) => {
      db.query(
        insertComSbjtQuery,
        [
          sbjt.user_id,
          sbjt.comSbjt_name,
          sbjt.comSbjt_date,
          sbjt.comSbjt_credit,
          sbjt.comSbjt_grade,
        ],
        (err) => {
          if (err) reject(err);
          db.query(selectCurIdQuery, (err, data) => {
            if (err) reject(err);
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
          insertRecSbjtQuery,
          [ComSbjt_id, curData.recSbjt_recCategory, curData.yoram_id],
          (err) => {
            if (err) reject(err);
            resolve();
          }
        );
      }
    });
  }

  static delSbjt(user_id, date) {
    return new Promise((resolve, reject) => {
      db.query(delComSbjtQuery, [user_id, date], (err) => {
        if (err) reject(err);
        db.query(delRecSbjtQuery, (err) => {
          if (err) reject(err);
          resolve("해당 학기 수강 과목이 삭제됐습니다.");
        });
      });
    });
  }

  // 아래부분 픽스 노!

  static getSbjt(user_id, yoram_id) {
    return new Promise((resolve, reject) => {
      db.query(selectSbjtQuery, [user_id, yoram_id], (err, data) => {
        if (err) reject(err);
        resolve(data);
      });
    });
  }

  static sortSbjt(comSbjtData) {
    return new Promise((resolve, reject) => {
      console.log(comSbjtData);

      const sortSbjt = {
        totalCredit: 0,
        totalGrade: 0,
        majorR: 0,
        majorS: 0,
        univR: 0,
        basicR: 0,
      };

      for (let i of comSbjtData) {
        sortSbjt.totalCredit += i.comSbjt_credit;
        sortSbjt.totalGrade += i.comSbjt_grade;
        sortSbjt[`${i.recSbjt_recCategory}`] += i.comSbjt_credit;

        if (!(i.comSbjt_date in sortSbjt)) {
          sortSbjt[`${i.comSbjt_date}`] = {
            totalCredit: 0,
            totalGrade: 0,
            majorR: [],
            majorS: [],
            univR: [],
            basicR: [],
          };
        }
        sortSbjt[`${i.comSbjt_date}`].totalCredit += i.comSbjt_credit;
        sortSbjt[`${i.comSbjt_date}`].totalGrade += i.comSbjt_grade;
        //prettier-ignore
        sortSbjt[`${i.comSbjt_date}`][`${i.recSbjt_recCategory}`].push(i.comSbjt_name);
      }

      resolve(sortSbjt);
    });
  }

  // static calRecSbjt(comSbjtData, yoram_id) {
  //   return new Promise(async (resolve, reject) => {
  //     await this.selectRecSbjt(comSbjtData, yoram_id);
  //     resolve();
  //   });
  // }

  // static selectRecSbjt(comSbjtData, yoram_id) {
  //   return new Promise((resolve, reject) => {
  //     for (let i in comSbjtData) {
  //       db.query(
  //         selectRecSbjtQuery,
  //         [comSbjtData[i].comSbjt_id, yoram_id],
  //         (err, data) => {
  //           if (err) reject(err);
  //           console.log(data);
  //         }
  //       );
  //     }
  //     resolve();
  //   });
  // }
}

module.exports = sbjtModel;
