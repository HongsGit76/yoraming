const db = require("../config/db");

const getUserQuery = "SELECT user_id, user_name FROM user WHERE user_id = ? ";

class userModel {
  static getUser(user_id) {
    return new Promise((resolve, reject) => {
      db.query(getUserQuery, [user_id], (err, data) => {
        if (err) reject(err);
        resolve(data[0]);
      });
    });
  }
}

module.exports = userModel;
