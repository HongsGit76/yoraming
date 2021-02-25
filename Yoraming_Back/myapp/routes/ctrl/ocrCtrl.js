const db = require("../../config/db");

module.exports = {
  output: {},
  process: {
    ocr: function (req, res, next) {
      res.send("respond with a resource");
      var sql =
        'SELECT * FROM yoraming.subj_2015 WHERE major ="전자공학전공(과)"';
      db.query(sql, function (err, result) {
        if (err) console.log(err);
        else {
          res.send("respond with a resource");
          console.log(result);
          res.json({
            result: true,
            msg: "회원가입에 성공했습니다.",
          });
        }
      });
      res.send("respond with a resource");
    },
  },
};
