const protectedPath = (req, res, next) => {
    if (typeof req.session.user_name === 'undefined' || typeof req.session.user_role === 'undefined') {
        res.redirect("login");
    } else {
        console.log("already authenticated in (protected path middleware) " + req.session.user_name);
        res.locals.userName = req.session.user_name;
        res.locals.userType = req.session.user_role;
        next();
    }
};

module.exports = protectedPath;