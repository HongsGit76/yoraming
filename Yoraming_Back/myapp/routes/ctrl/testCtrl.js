module.exports = {
  output: {
    index: function (req, res, next) {
      res.render("index", { title: "Express" });
    },
  },
  process: {},
};
