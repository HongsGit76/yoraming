"use strict";

const specEtcModel = require("../../model/specModel/specModel.etc");

const output = {};

const process = {
  // 스펙 보기
  getEtcSpec: async (req, res) => {
    const spec = new specEtcModel(req.body);
    const response = await spec.test();
    return res.json(response);
  },
  // 스펙 추가
  addEtcSpec: async (req, res) => {
    const spec = new specEtcModel(req.body);
    const response = await spec.addEtcSpec();
    return res.json(response);
  },
  // 스펙 삭제
  deleteEtcSpec: async (req, res) => {
    const spec = new specEtcModel(req.body);
    const response = await spec.test();
    return res.json(response);
  },

  // 스펙 수정
  editEtcSpec: async (req, res) => {
    const spec = new specEtcModel(req.body);
    const response = await spec.test();
    return res.json(response);
  },
};

module.exports = {
  output,
  process,
};
