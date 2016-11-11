(function() {
   angular.module('app', [
      'app.core',
      'app.currencies'
   ]).run(function($rootScope) {
      $rootScope.userName = 'packarbell';
   });
}());