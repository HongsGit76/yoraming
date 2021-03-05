const userModel = require("../../model/userModel");

module.exports = {
  output: {
    getUser: async function (req, res) {
      try {
        const getUser = await userModel.getUser(req.query.user_id);

        if (getUser) {
          var result = { success: true, data: getUser };
        } else {
          var result = { success: false };
        }
        res.json(result);
      } catch (error) {
        const result = { success: false, msg: error };
        res.json(result);
      }
    },
  },
  process: {
    addUser: async function (req, res) {
      try {
        await userModel.addUser(req.body);
        const result = { success: true };
        res.json(result);
      } catch (error) {
        const result = { success: false, msg: error };
        res.json(result);
      }
    },
  },
};
