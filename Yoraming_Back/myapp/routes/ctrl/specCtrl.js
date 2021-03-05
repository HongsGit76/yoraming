"use strict";

const specEtcModel = require("../../model/specModel/specModel.etc");
const specCareerModel = require("../../model/specModel/specModel.career");
const specCertModel = require("../../model/specModel/specModel.cert");
const specLangModel = require("../../model/specModel/specModel.lang");

const output = {};

const process = {
  // 1. etc 스펙
  // 스펙 보기
  getEtcSpec: async (req, res) => {
    const spec = new specEtcModel(req.query);
    const response = await spec.getEtcSpec();
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
    const spec = new specEtcModel(req.query);
    const response = await spec.deleteEtcSpec();
    return res.json(response);
  },
  // 스펙 수정
  editEtcSpec: async (req, res) => {
    const spec = new specEtcModel(req.body);
    const response = await spec.editEtcSpec();
    return res.json(response);
  },

  // 2. career 스펙
  // 스펙 보기
  getCareerSpec: async (req, res) => {
    const spec = new specCareerModel(req.query);
    const response = await spec.getCareerSpec();
    return res.json(response);
  },
  // 스펙 추가
  addCareerSpec: async (req, res) => {
    const spec = new specCareerModel(req.body);
    const response = await spec.addCareerSpec();
    return res.json(response);
  },
  // 스펙 삭제
  deleteCareerSpec: async (req, res) => {
    const spec = new specCareerModel(req.query);
    const response = await spec.deleteCareerSpec();
    return res.json(response);
  },
  // 스펙 수정
  editCareerSpec: async (req, res) => {
    const spec = new specCareerModel(req.body);
    const response = await spec.editCareerSpec();
    return res.json(response);
  },

  // 3. certificate 스펙
  // 스펙 보기
  getCertSpec: async (req, res) => {
    const spec = new specCertModel(req.query);
    const response = await spec.getCertSpec();
    return res.json(response);
  },
  // 스펙 추가
  addCertSpec: async (req, res) => {
    const spec = new specCertModel(req.body);
    const response = await spec.addCertSpec();
    return res.json(response);
  },
  // 스펙 삭제
  deleteCertSpec: async (req, res) => {
    const spec = new specCertModel(req.query);
    const response = await spec.deleteCertSpec();
    return res.json(response);
  },
  // 스펙 수정
  editCertSpec: async (req, res) => {
    const spec = new specCertModel(req.body);
    const response = await spec.editCertSpec();
    return res.json(response);
  },

  // 4. Language 스펙
  // 스펙 보기
  getLangSpec: async (req, res) => {
    const spec = new specLangModel(req.query);
    const response = await spec.getLangSpec();
    return res.json(response);
  },
  // 스펙 추가
  addLangSpec: async (req, res) => {
    const spec = new specLangModel(req.body);
    const response = await spec.addLangSpec();
    return res.json(response);
  },
  // 스펙 삭제
  deleteLangSpec: async (req, res) => {
    const spec = new specLangModel(req.query);
    const response = await spec.deleteLangSpec();
    return res.json(response);
  },
  // 스펙 수정
  editLangSpec: async (req, res) => {
    const spec = new specLangModel(req.body);
    const response = await spec.editLangSpec();
    return res.json(response);
  },
};

module.exports = {
  output,
  process,
};
