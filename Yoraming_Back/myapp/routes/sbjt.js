var express = require("express");
var router = express.Router();
const sbjtCtrl = require("./ctrl/sbjtCtrl");

// 기능 1 수강과목 읽기
router.post("/", sbjtCtrl.process.addSbjt);

module.exports = router;
