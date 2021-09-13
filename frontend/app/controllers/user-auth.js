const { use } = require('chai');
const express = require('express');
const router = express.Router();
const User = require('../models/user');
const config = require('../../config/config');

const superagent = require('superagent');

module.exports = (app) => {
    app.use('/', router);
};

//protection middleware
const protectedAuth = (req, res, next) => {
    if (typeof req.session.user_name === 'undefined' || typeof req.session.user_role === 'undefined') {
        next();
    } else {
        console.log("already authenticated " + req.session.user_name);
        res.redirect('dashboard');
    }
};

router.get(['/', '/login'], protectedAuth, (req, res, next) => {
    res.render('login');
});

router.post('/login', protectedAuth, (req, res, next) => {
    var user = new User();
    user.id = req.body.id;
    user.password = req.body.password;

    // callback
    superagent
        .get(config.backEndBaseUrl+'/user/' + user.id)
        .end((err, response) => {
            // Calling the end function will send the request
            if (response.ok) {
                // console.log(JSON.stringify(response));

                if (response.body.password == user.password) {
                    console.log("authenticate success ");
                    req.session.user_id = response.body.id;
                    req.session.user_name = response.body.userName;
                    req.session.user_role = response.body.userType;
                    res.redirect('/dashboard');
                } else {
                    console.log("authenticate failed");
                    res.redirect('back');
                }
            } else {
                res.redirect('back');
            }
        });
});

router.get('/register', protectedAuth, (req, res, next) => {
    res.render('register');
});

router.post('/register', protectedAuth, (req, res, next) => {
    var user = new User();
    user.nic = req.body.nic;
    user.name = req.body.name;
    user.userType = 'customer';
    user.address = req.body.address;
    user.contact = req.body.contact;
    user.branchId = req.body.branch;
    user.userName = req.body.username;
    user.password = req.body.password;

    superagent
        .post(config.backEndBaseUrl+'/user')
        .send(JSON.stringify(user))
        .set('Content-Type', 'application/json')
        .end((err, response) => {
            if (response.ok) {
                console.log("registration success ");
                req.session.user_id = response.body.id;
                req.session.user_name = response.body.userName;
                req.session.user_role = response.body.userType;
                res.redirect('/dashboard');
            } else {
                console.log("registration failed " + err);
                res.redirect('back');
            }
        });
});

router.get('/logout', (req, res, next) => {
    req.session.destroy();
    res.redirect('/login');
});