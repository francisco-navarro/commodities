var bodyParser = require('body-parser');

function init(app) {
  app.use(bodyParser.json());

  app.get('/api', function(req, res) {
    res.json({
      message: 'It works'
    });
    res.status(200).end();
  });
}

module.exports = {
  init: init
};