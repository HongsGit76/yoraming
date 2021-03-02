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

// 1. etc 스펙
// 스펙 보기
router.get("/etc", specCtrl.process.getCareerSpec);
// 스펙 수정
router.put("/etc", specCtrl.process.editCareerSpec);
// 스펙 추가
router.post("/etc", specCtrl.process.addCareerSpec);
// 스펙 제거
router.delete("/etc", specCtrl.process.deleteCareerSpec);

// 1. etc 스펙
// 스펙 보기
router.get("/etc", specCtrl.process.getCertSpec);
// 스펙 수정
router.put("/etc", specCtrl.process.editCertSpec);
// 스펙 추가
router.post("/etc", specCtrl.process.addCertSpec);
// 스펙 제거
router.delete("/etc", specCtrl.process.deleteCertSpec);

// 1. etc 스펙
// 스펙 보기
router.get("/etc", specCtrl.process.getLangSpec);
// 스펙 수정
router.put("/etc", specCtrl.process.editLangSpec);
// 스펙 추가
router.post("/etc", specCtrl.process.addLangSpec);
// 스펙 제거
router.delete("/etc", specCtrl.process.deleteLangSpec);

module.exports = router;
