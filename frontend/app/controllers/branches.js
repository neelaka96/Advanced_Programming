const express = require('express');
const router = express.Router();
const routeProtector = require('../middlewares/routeProtector');
const superagent = require('superagent');
const Branch = require('../models/branch');
const config = require('../../config/config');

module.exports = (app) => {
    app.use('/branches', router);
};

router.post('/save', routeProtector, (req, res, next) => {
    var branch = new Branch();
    branch.name = req.body.name;
    branch.location = req.body.location;
    branch.contact = req.body.contact;

    superagent
        .post(config.backEndBaseUrl + '/branch')
        .send(JSON.stringify(branch))
        .set('Content-Type', 'application/json')
        .end((err, response) => {
            if (response.ok) {
                res.redirect('/branches');
            } else {
                console.log("branch save failed " + err);
                res.redirect('back');
            }
        });
});

router.post('/delete', routeProtector, (req, res, next) => {

    superagent
        .delete(config.backEndBaseUrl + '/branch/' + req.body.branchId)
        .end((err, response) => {
            if (response.ok) {
                res.redirect('/branches');
            } else {
                console.log("branch delete failed " + err);
                res.redirect('back');
            }
        });
});

router.get('/', routeProtector, (req, res, next) => {
    superagent
        .get(config.backEndBaseUrl + '/branch')
        .end((err, response) => {
            // Calling the end function will send the request
            if (response.ok) {
                if (Object.keys(response.body).length !== 0) {
                    var jsonString = JSON.stringify(response.body);
                    var objList = JSON.parse(jsonString);

                    res.render('branches', {
                        branchesList: objList
                    });
                } else {
                    res.render('branches', {
                        branchesList: []
                    });
                }

            } else {
                console.log("error in get all branches : " + err);
                next(err);
            }
        });
});