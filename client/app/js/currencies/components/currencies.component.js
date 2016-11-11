(function() {
   function currencyCtrl(CurrencyService) {
      var ctrl = this;

      CurrencyService.getCurrencies().then(function(data) {
         ctrl.money = data;
      });
   }

   angular.module('app.currencies').component('dashboardWidgetMoney', {
      templateUrl: 'html/currencies/dashboard-widget-money.html',
      controller: currencyCtrl,
      bindings: {}
   });
}());