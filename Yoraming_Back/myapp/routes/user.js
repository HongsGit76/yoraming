const express = require("express");
const router = express.Router();
const userCtrl = require("./ctrl/userCtrl");

router.get("/", userCtrl.output.getUser);
router.post("/", userCtrl.process.addUser);

module.exports = router;
