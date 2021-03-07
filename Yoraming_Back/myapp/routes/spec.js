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

// 2. career 스펙
// 스펙 보기
router.get("/career", specCtrl.process.getCareerSpec);
// 스펙 수정
router.put("/career", specCtrl.process.editCareerSpec);
// 스펙 추가
router.post("/career", specCtrl.process.addCareerSpec);
// 스펙 제거
router.delete("/career", specCtrl.process.deleteCareerSpec);

// 3. certificate 스펙
// 스펙 보기
router.get("/certificate", specCtrl.process.getCertSpec);
// 스펙 수정
router.put("/certificate", specCtrl.process.editCertSpec);
// 스펙 추가
router.post("/certificate", specCtrl.process.addCertSpec);
// 스펙 제거
router.delete("/certificate", specCtrl.process.deleteCertSpec);

// 4. language 스펙
// 스펙 보기
router.get("/language", specCtrl.process.getLangSpec);
// 스펙 수정
router.put("/language", specCtrl.process.editLangSpec);
// 스펙 추가
router.post("/language", specCtrl.process.addLangSpec);
// 스펙 제거
router.delete("/language", specCtrl.process.deleteLangSpec);

module.exports = router;
