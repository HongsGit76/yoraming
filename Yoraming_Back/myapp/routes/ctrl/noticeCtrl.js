"use strict";

const noticeModel = require("../../model/noticeModel");

const output = {
  getNoticeAll: async (req, res) => {
    const noticeData = await noticeModel.getNoticeAll();
    res.json(noticeData);
  },
};

const process = {
  postNotice: async (req, res) => {
    const notice = new noticeModel(req, true);
    const response = await notice.postNotice();
    res.json(response);
  },
  delNotice: async (req, res) => {
    const notice = new noticeModel(req, false);
    const response = await notice.delNotice();
    res.json(response);
  },
  getNotice: async (req, res) => {
    const notice = new noticeModel(req, false);
    const response = await notice.getNotice();
    res.json(response);
  },
};

module.exports = {
  output,
  process,
};
