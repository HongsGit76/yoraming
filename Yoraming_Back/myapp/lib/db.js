var mysql = require("mysql");

var db = mysql.createConnection({
  host: "127.0.0.1",
  // port: "3306",
  user: "new_master_user",
  password: "1234",
  database: "yoraming",
});

db.connect((err) => {
  if (err) throw err;
});

module.exports = db;
