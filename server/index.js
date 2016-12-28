var bodyParser = require('body-parser');
var routes = require('./routes');

function init(app) {
  app.use(bodyParser.json());
    
}

module.exports = {
  init: init
};