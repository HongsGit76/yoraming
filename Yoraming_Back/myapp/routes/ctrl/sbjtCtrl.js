const sbjtModel = require("../../model/sbjtModel");

module.exports = {
  output: {},
  process: {
    addSbjt: async (req, res) => {
      try {
        const body = req.body;
        await sbjtModel.delSbjt(body[0].user_id, body[0].comSbjt_date);
        await sbjtModel.addSbjt(body);
        res.json({ success: true });
      } catch (error) {
        console.log(error);
        res.json({ success: false });
      }
    },
    // 프리뷰 용도, 요람별로 볼 수 있게
    // 학기별로 전공 대필 학필 교양 과목명, 총 학점, 평점
    // 총 이수학점

    // 요람에 대해서 학기별로 구분별로 과목명, 학점, 성적

    getSbjtYoram: async (req, res) => {
      const user_id = req.body.user_id;
      const yoram_id = req.body.yoram_id;

      try {
        const comSbjtData = await sbjtModel.getSbjt(user_id, yoram_id);
        const sortSbjtData = await sbjtModel.sortSbjt(comSbjtData);
        // await sbjtModel.calRecSbjt(comSbjtData, yoram_id);

        sortSbjtData.success = true;
        res.json(sortSbjtData);
      } catch (error) {
        console.log(error);
        res.json({ success: false });
      }
    },
  },
};
