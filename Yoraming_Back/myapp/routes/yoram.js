var express = require("express");
var router = express.Router();

//컨트롤러 변수
const yoramCtrl = require("./ctrl/yoramCtrl");

// 기능 1 요람 DB등록
router.post("/", yoramCtrl.process.postYoram);
// 기능 2 요람 DB삭제
router.delete("/", yoramCtrl.process.delYoram);
// 기능 3 요람 DB수정
router.put("/", yoramCtrl.process.putYoram);
// 기능 4 요람 DB불러오기
router.get("/", yoramCtrl.process.getYoram);

module.exports = router;
