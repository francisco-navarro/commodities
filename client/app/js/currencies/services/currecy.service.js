(function() {
   angular.module('app.currencies').factory('CurrencyService', CurrencyService
    );

    /*@ngInject*/
   function CurrencyService($http) {
      return {
         getCurrencies: getCurrencies
      };
      function getCurrencies() {
         return $http.get('api/currencies').then(function(data) {
            return data;
         });
      }
   }
}());
