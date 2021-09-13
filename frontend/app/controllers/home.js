const express = require('express');
const router = express.Router();
const Article = require('../models/article');
const routeProtector = require('../middlewares/routeProtector');

module.exports = (app) => {
  app.use('/', router);
};

router.get('/dashboard', routeProtector, (req, res, next) => {
  res.render('dashboard');
});
