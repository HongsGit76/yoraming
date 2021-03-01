const sbjtModel = require("../../model/sbjtModel");

module.exports = {
  output: {},
  process: {
    addSbjt: async (req, res) => {
      const body = req.body;
      await sbjtModel.delSbjt(body[0].user_id, body[0].comSbjt_date);
      await sbjtModel.addSbjt(body);
      res.json({ Success: true });
    },
  },
};
