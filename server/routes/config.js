var currencyController = require('./../controllers/currency.controller');

function init(app) {
   app.get('/currencies/:symbol', currencyController.get);
   app.get('/currencies', currencyController.getAll);
}

module.exports = init;