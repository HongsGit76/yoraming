"use strict";

// 모듈
var express = require("express");

// 라우터
var router = express.Router();

// 컨트롤러
const specCtrl = require("./ctrl/specCtrl");

// 1. etc 스펙
// 스펙 보기
router.get("/etc", specCtrl.process.getEtcSpec);
// 스펙 수정
router.put("/etc", specCtrl.process.editEtcSpec);
// 스펙 추가
router.post("/etc", specCtrl.process.addEtcSpec);
// 스펙 제거
router.delete("/etc", specCtrl.process.deleteEtcSpec);

module.exports = router;
