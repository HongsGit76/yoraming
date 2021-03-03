var express = require("express");
var router = express.Router();
const sbjtCtrl = require("./ctrl/sbjtCtrl");

// 기능 1 수강과목 읽기
router.post("/", sbjtCtrl.process.addSbjt);
router.post("/yoram", sbjtCtrl.process.getSbjtYoram);
router.get("/date", sbjtCtrl.process.getSbjtDate);

module.exports = router;
