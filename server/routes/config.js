var currencyController = require('./../controllers/currency.controller');

function init(app) {
   app.get('/currencies/:symbol', currencyController.get);
}

module.exports = init;