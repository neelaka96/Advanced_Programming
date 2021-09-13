const express = require('express');
const router = express.Router();
const routeProtector = require('../middlewares/routeProtector');
const superagent = require('superagent');
const Product = require('../models/product');
const config = require('../../config/config');

module.exports = (app) => {
    app.use('/product', router);
};

router.get('/', routeProtector, (req, res, next) => {

    //get all products
    superagent
        .get(config.backEndBaseUrl + '/product')
        .end((err, response) => {
            // Calling the end function will send the request
            if (response.ok) {
                if (Object.keys(response.body).length !== 0) {
                    var jsonString = JSON.stringify(response.body);
                    var objList = JSON.parse(jsonString);

                    res.render('product', {
                        productsList: objList
                    });
                } else {
                    res.render('product', {
                        productsList: []
                    });
                }

            } else {
                console.log(err);
                next(err);
            }
        });
});

router.post('/save', routeProtector, (req, res, next) => {
    var product = new Product();
    product.name = req.body.name;
    product.productCode = req.body.productCode;
    product.unitPrice = req.body.unitPrice;

    superagent
        .post(config.backEndBaseUrl + '/product')
        .send(JSON.stringify(product))
        .set('Content-Type', 'application/json')
        .end((err, response) => {
            if (response.ok) {
                res.redirect('/product');
            } else {
                console.log("product save failed " + err);
                res.redirect('back');
            }
        });
});

router.post('/delete', routeProtector, (req, res, next) => {

    superagent
        .delete(config.backEndBaseUrl + '/product/' + req.body.productId)
        .end((err, response) => {
            if (response.ok) {
                res.redirect('/product');
            } else {
                console.log("product delete failed " + err);
                res.redirect('back');
            }
        });
});