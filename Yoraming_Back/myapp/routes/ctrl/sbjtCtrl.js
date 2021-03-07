const sbjtModel = require("../../model/sbjtModel");

module.exports = {
  output: {
    getSbjtYoram: async (req, res) => {
      const user_id = req.query.user_id;
      const yoram_id = req.query.yoram_id;
      try {
        const comSbjtData = await sbjtModel.getSbjt(user_id, yoram_id);
        const sortSbjtData = await sbjtModel.sortSbjt(comSbjtData);
        const calSbjtData = await sbjtModel.calSbjt(sortSbjtData);
        calSbjtData.success = true;
        res.json(calSbjtData);
      } catch (error) {
        console.log(error);
        res.json({ success: false, msg: error });
      }
    },

    getSbjtDate: async (req, res) => {
      const user_id = req.query.user_id;
      const comSbjt_date = req.query.comSbjt_date;
      try {
        const dateSbjtData = await sbjtModel.getSbjtDate(user_id, comSbjt_date);
        const dateRecSbjtData = await sbjtModel.getRecSbjtDate(dateSbjtData);
        const result = { success: true, data: dateRecSbjtData };
        res.json(result);
      } catch (error) {
        res.json({ success: false, msg: error });
      }
    },
  },
  process: {
    addSbjt: async (req, res) => {
      try {
        const body = req.body;
        await sbjtModel.delSbjt(body[0].user_id, body[0].comSbjt_date);
        await sbjtModel.addSbjt(body);
        res.json({ success: true });
      } catch (error) {
        console.log(error);
        res.json({ success: false, msg: error });
      }
    },
  },
};
