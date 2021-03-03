var mysql = require("mysql");

var db = mysql.createConnection({
  host: process.env.MYSQL_HOST,
  port: process.env.MYSQL_PORT,
  user: process.env.MYSQL_USER,
  password: process.env.MYSQL_PASSWORD,
  database: process.env.MYSQL_DATABASE,
  // host: "13.58.240.144",
  // port: "3306",
  // user: "new_master_user",
  // password: '1234',
  // database: "yoraming",
});

db.connect((err) => {
  if (err) throw err;
});

module.exports = db;
