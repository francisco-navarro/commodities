var rp = require('request-promise');
var constants = require('./../constants');
var currencies = {};

function init() {
   recordAllCurrencies();
}

function recordAllCurrencies() {
   rp(constants.CURRENCY_URL)
      .then(function(response) {
         var data = JSON.parse(response);

         data.list.resources.forEach(function(item) {
            currencies[item.resource.fields.name] =
               item.resource.fields;
         });
      }).catch(function(err) {
         console.error(err);
      });
}

function getAll(req, res){
      res.json(currencies);
      res.status(200).end();
}

function getCurrency(req, res) {
   var symbol = req.params.symbol;

   if (currencies[symbol]) {
      res.json(currencies[symbol]);
      res.status(200).end();
   } else {
      res.status(404).end();
   }
}

init();

module.exports = {
   getCurrency: getCurrency,
   get: getCurrency,
   getAll: getAll
};