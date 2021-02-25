var express = require("express");
var router = express.Router();
const ocrCtril = require("./ctrl/ocrCtrl");
/* GET users listing. */
router.get("/", ocrCtril.process.ocr);

module.exports = router;
