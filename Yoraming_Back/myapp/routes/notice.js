"use strict";

var express = require("express");
var router = express.Router();

//컨트롤러 변수
const noticeCtrl = require("./ctrl/noticeCtrl");

//공지사항 키워드 등록
router.post("/", noticeCtrl.process.postNotice);
//공지사함 키워드 삭제
router.delete("/", noticeCtrl.process.delNotice);
//공지사항 키워드 불러오기
router.get("/", noticeCtrl.process.getNotice);
// 현재 공지사항 크롤링
router.get("/all", noticeCtrl.output.getNoticeAll);

module.exports = router;
