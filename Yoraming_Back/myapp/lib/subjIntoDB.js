const db = require("../config/db");
const majorList = require("../json_list/majorList");
const fs = require("fs");

// 학과 이름과 연도 리스트
const majors = majorList.majorList;
const years = majorList.year;

// 테이블 생성 함수
function createTable(years) {
  for (let year of years) {
    let tableName = `subj_${year}`;
    db.query(
      `CREATE TABLE ${tableName} (major VARCHAR(45) NULL,curriculum VARCHAR(45) NULL,name VARCHAR(45) NULL,selection VARCHAR(45) NULL,code VARCHAR(45) NULL,classification VARCHAR(45) NULL,changes VARCHAR(45) NULL,credit VARCHAR(45) NULL,curiNo VARCHAR(45) NOT NULL,sbjtId VARCHAR(45) NULL)`,
      (err, data) => {
        if (err) console.log(err);
        else {
          console.log(tableName);
        }
      }
    );
  }
}

// 데이터 넣는 과정.
function insertData(majors, years) {
  for (let year of years) {
    let majorList = [];
    let tableName = `subj_${year}`;
    for (major of majors) {
      let fileName = `${major}_${year}.json`;
      let bufferData = fs.readFileSync(`./json_list/list/${fileName}`);
      let parseData = JSON.parse(bufferData);
      let majorArray = parseData.DatasetList.DS_COUR030;

      if (!hasMajor(majorArray)) continue;
      majorList.push(major);
      insertDataToDB(majorArray, tableName);
    }
    writeMajorList(majorList, year);
  }
}

// 해당년도의 학과 수업이 있는지
function hasMajor(majorArray) {
  if (Array.isArray(majorArray) && majorArray.length) {
    return true;
  } else false;
}

// 테이블에 데이터 insert
function insertDataToDB(majorArray, tableName) {
  for (let value of majorArray) {
    db.query(
      `INSERT INTO ${tableName} VALUES(?,?,?,?,?,?,?,?,?,?)`,
      [
        value.mjCdNm,
        value.submattFgNm,
        value.sbjtKorNm,
        value.stgNeceGrpNm,
        value.sbjtCd,
        value.sustLsnFgNm,
        value.sbjtChgFgNm,
        value.pnt,
        value.curiNo,
        value.sbjtId,
      ],
      (err, data) => {
        if (err) throw err;
      }
    );
  }
}

// 각 년도에 어디 학과 수업이 존재하는지 리스트 작성
function writeMajorList(majorList, year) {
  fs.writeFileSync(`./json_list/majorList_${year}`, majorList.toString());
}

// 실행
createTable(years);
insertData(majors, years);
