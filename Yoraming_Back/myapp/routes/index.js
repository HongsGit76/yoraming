var express = require("express");
var router = express.Router();
const testCtril = require("./ctrl/testCtrl");

/* GET home page. */
router.get("/", testCtril.output.index);

module.exports = router;
