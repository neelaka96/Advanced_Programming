const express = require('express');
const router = express.Router();
const routeProtector = require('../middlewares/routeProtector');
const superagent = require('superagent');
const config = require('../../config/config');
const Stock = require('../models/stock');
var moment = require('moment');

module.exports = (app) => {
    app.use('/stock', router);
};

router.get('/', routeProtector, (req, res, next) => {

    //get all products
    superagent
        .get(config.backEndBaseUrl + '/stock')
        .end((err, response) => {
            // Calling the end function will send the request
            if (response.ok) {
                if (Object.keys(response.body).length !== 0) {
                    var jsonString = JSON.stringify(response.body);
                    var objList = JSON.parse(jsonString);

                    res.render('stock', {
                        stocksList: objList,
                        moment: require('moment')
                    });
                } else {
                    res.render('stock', {
                        stocksList: []
                    });
                }

            } else {
                console.log(err);
                next(err);
            }
        });
});

router.post('/save', routeProtector, (req, res, next) => {
    var stock = new Stock();
    stock.fromBranchId = req.body.fromBranchId;
    stock.toBranchId = req.body.toBranchId;
    stock.productId = req.body.productId;
    stock.stock_qty = req.body.stock_qty;
    stock.date = moment().format('yyyy-MM-DD');

    superagent
        .post(config.backEndBaseUrl + '/stock')
        .send(JSON.stringify(stock))
        .set('Content-Type', 'application/json')
        .end((err, response) => {
            if (response.ok) {
                res.redirect('/stock');
            } else {
                console.log("stocksave failed " + err);
                res.redirect('back');
            }
        });
});

router.post('/delete', routeProtector, (req, res, next) => {

    superagent
        .delete(config.backEndBaseUrl + '/stock/' + req.body.stockId)
        .end((err, response) => {
            if (response.ok) {
                res.redirect('/stock');
            } else {
                console.log("stock delete failed " + err);
                res.redirect('back');
            }
        });
});