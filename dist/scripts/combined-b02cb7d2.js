(function() {
   angular.module('app', [
      'app.core',
      'app.currencies'
   ]).run(function($rootScope) {
      $rootScope.userName = 'packarbell';
   });
}());
(function () {
   angular.module('app.core', [
      'ui.router',
      'ngMaterial'
   ]);
}());
!function(){angular.module("app.core").run(["$templateCache",function(e){e.put("html/currencies/index.html","<!DOCTYPE html>\n<h3>windjammers players</h3>")}])}();
(function() {
   angular.module('app.currencies', [
      'app.core']).config(function($stateProvider, $urlRouterProvider) {
          var state = 'currencies';
          var config = {
              abstract: false,
              url: '/currencies',
              templateUrl: 'html/currencies/index.html'
           };

          $urlRouterProvider.otherwise(state);
          $stateProvider.state(state, config);
       });
}());
(function() {
   function currencyCtrl(CurrencyService) {
      var ctrl = this;

      CurrencyService.getCurrencies().then(function(data) {
         ctrl.money = data;
      });
   }

   angular.module('app.currencies').component('dashboardWidgetMoney', {
      templateUrl: 'html/dashboard/dashboard-widgets-data.html',
      controller: currencyCtrl,
      bindings: {}
   });
}());
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
