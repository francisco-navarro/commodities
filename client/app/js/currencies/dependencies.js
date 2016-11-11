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