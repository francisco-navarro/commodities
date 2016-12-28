import { Component, OnInit } from '@angular/core';

import { CurrencyService } from '../services/currency.service';

@Component({
  selector: 'my-dashboard',
  template: `
  <span>Hello world</span>`
})
export class DashboardComponent implements OnInit {

    constructor(private currencyService: CurrencyService) { }

    ngOnInit() : void {
      //nothing yet
    }
}