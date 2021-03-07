"use strict"

const { resolveInclude } = require("ejs");
const { connect } = require("../config/db");
const db = require("../config/db");

const findNULLQuery = "SELECT * FROM yoram WHERE user_id = 'NULLitem'";
const findIndexQuery = "SELECT COUNT(*) FROM yoram";
const insertQuery = "INSERT INTO yoram(yoram_id, user_id, yoram_major, yoram_total, yoram_majorR, yoram_majorS, yoram_univR, yoram_basicR) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
const insertNULLQuery = "UPDATE yoram SET user_id = ?, yoram_major = ?, yoram_total = ?, yoram_majorR = ?, yoram_majorS = ?, yoram_univR = ?, yoram_basicR = ? WHERE yoram_id = ?";
const deleteQuery = "UPDATE yoram SET user_id = 'NULLitem' WHERE yoram_id = ?";
const updateQuery = "UPDATE yoram SET yoram_major = ?, yoram_total = ?, yoram_majorR = ?, yoram_majorS = ?, yoram_univR = ?, yoram_basicR = ? WHERE yoram_id = ?";
const getQuery = " SELECT * FROM yoram WHERE user_id = ?";

class yoramModel {
    constructor(req,JSONtype) {
        if (JSONtype === true) this.body = req.body;
        else this.query = req.query;
    }

    async curYoramIndex(){
        return new Promise((resolve,reject)=>{
            db.query(findNULLQuery,
            (err,data) =>{
                if (err) resolve("err");
                else if (data.length === 0){
                    db.query(findIndexQuery,
                        (err,data) =>{
                            if (err) resolve("err");
                            else resolve(
                                {
                                    idx : JSON.stringify(data[0]["COUNT(*)"]),
                                    type : "NotNullItem",
                                });
                        });
                }
                else resolve(
                    {
                        idx : JSON.stringify(data[0].yoram_id -1),
                        type : "NullItem",
                    });
            });
        });
    }

    async postYoram(){
        return new Promise(async (resolve,reject)=>{
            const curidx = await this.curYoramIndex();
            const newidx = Number(curidx.idx)+1;
            if (curidx ==="err") resolve( {success: false, msg: "indexErr"});
            else if (curidx.type === "NotNullItem"){
                db.query(
                    insertQuery,
                    [
                        newidx,
                        this.body.user_id,
                        this.body.yoram_major,
                        this.body.yoram_total,
                        this.body.yoram_majorR,
                        this.body.yoram_majorS,
                        this.body.yoram_univR,
                        this.body.yoram_basicR,
                    ],(err) =>{
                        if (err) resolve({success: false, msg: "postNotNullErr"});
                        else resolve({success: true, yoramid: newidx });
                    }
                );
            }
            else{
                db.query(
                    insertNULLQuery,
                    [
                        this.body.user_id,
                        this.body.yoram_major,
                        this.body.yoram_total,
                        this.body.yoram_majorR,
                        this.body.yoram_majorS,
                        this.body.yoram_univR,
                        this.body.yoram_basicR,
                        newidx,
                    ],(err) =>{
                        if (err) resolve({success: false, msg: "postNullErr"});
                        else resolve({success: true, yoramid: newidx });
                    }
                );
            }
        });
    }

    async delYoram(){
        return new Promise((resolve,reject)=>{
            db.query(
                deleteQuery,
                [
                    this.query.yoram_id,
                ],(err)=>{
                    if(err) resolve({success: false, msg: "deleteErr"});
                    else resolve({success: true});
                }
            );
        });
    }

    async putYoram(){
        return new Promise((resolve,reject)=>{
            db.query(
                updateQuery,
                [
                    this.body.yoram_major,
                    this.body.yoram_total,
                    this.body.yoram_majorR,
                    this.body.yoram_majorS,
                    this.body.yoram_univR,
                    this.body.yoram_basicR,
                    this.body.yoram_id,
                ],(err)=>{
                    if(err) resolve({success: false, msg: "putErr"});
                    else resolve({success: true});
                }
            );
        });
    }

    async getYoram(){
        return new Promise((resolve,reject)=>{
            db.query(
                getQuery,
                [
                    this.query.user_id,
                ],(err,data)=>{
                    // console.log(JSON.stringify(data));
                    if(err) resolve({success: false, msg: "getErr"});
                    else resolve({success: true, yoram: data});
                }
            );
        });
    }
        
}
module.exports = yoramModel;