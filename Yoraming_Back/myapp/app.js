"use strict";

// 모듈 정의
var createError = require("http-errors");
var express = require("express");
var path = require("path");
var cookieParser = require("cookie-parser");
var logger = require("morgan");
const dotenv = require("dotenv").config();

// 라우터 정의
var indexRouter = require("./routes/index");
var ocrRouter = require("./routes/ocr");
var sbjtRouter = require("./routes/sbjt");
var specRouter = require("./routes/spec");
var YoramRouter = require("./routes/yoram");
var noticeRouter = require("./routes/notice");
const userRouter = require("./routes/user");

var app = express();

// view engine setup
app.set("views", path.join(__dirname, "views"));
app.set("view engine", "ejs");

app.use(logger("dev"));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, "public")));
//port setup
// app.set("port", process.env.PORT || 9000);

// urls pattern
app.use("/", indexRouter);
app.use("/subject", sbjtRouter);
app.use("/ocr", ocrRouter);
app.use("/spec", specRouter);
app.use("/yoram", YoramRouter);
app.use("/notice", noticeRouter);
app.use("/user", userRouter);

// catch 404 and forward to error handler
app.use(function (req, res, next) {
  next(createError(404));
});

// error handler
app.use(function (err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get("env") === "development" ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render("error");
});

module.exports = app;
