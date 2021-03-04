const db = require("../config/db");

const getUserQuery = "SELECT user_id, user_name FROM user WHERE user_id = ? ";
const addUserQuery = "INSERT INTO user(user_id, user_name) VALUES(?, ?) ";

class userModel {
  static getUser(user_id) {
    return new Promise((resolve, reject) => {
      db.query(getUserQuery, [user_id], (err, data) => {
        if (err) reject(err);
        resolve(data[0]);
      });
    });
  }

  static addUser(data) {
    return new Promise((resolve, reject) => {
      db.query(addUserQuery, [data.user_id, data.user_name], (err) => {
        if (err) reject(err);
        resolve();
      });
    });
  }
}

module.exports = userModel;
