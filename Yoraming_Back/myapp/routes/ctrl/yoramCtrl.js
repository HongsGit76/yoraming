"use strict"

const yoramModel = require("../../model/yoramModel");

const process = {
    postYoram: async(req,res)=>{
        const yoram = new yoramModel(req,true);
        const response = await yoram.postYoram();
        return res.json(response);
    },
    delYoram: async  (req,res)=>{
        const yoram = new yoramModel(req,false);
        const response = await yoram.delYoram();
        return res.json(response);
    },
    putYoram: async  (req,res)=>{
        const yoram = new yoramModel(req,true);
        const response = await yoram.putYoram();
        return res.json(response);
    },
    getYoram: async  (req,res)=>{
        const yoram = new yoramModel(req,false);
        const response = await yoram.getYoram();
        return res.json(response);
    },
};
module.exports = {
    process
};