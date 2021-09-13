const path = require('path');
const rootPath = path.normalize(__dirname + '/..');
const env = process.env.NODE_ENV || 'development';

const config = {
  development: {
    root: rootPath,
    app: {
      name: 'adminbooksfront'
    },
    port: process.env.PORT || 3000,
    backEndBaseUrl:'http://localhost:8080'
  },

  test: {
    root: rootPath,
    app: {
      name: 'adminbooksfront'
    },
    port: process.env.PORT || 3000,
  },

  production: {
    root: rootPath,
    app: {
      name: 'adminbooksfront'
    },
    port: process.env.PORT || 3000,
  }
};

module.exports = config[env];
