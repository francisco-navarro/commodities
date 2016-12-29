import { Component, OnInit } from '@angular/core';

import { CurrencyService } from '../services/currency.service';

import { Currency } from '../model/currency';

@Component({
  selector: 'currency',
  template: `
    <h2 class="title">List of currencies</h2>
    <ul>
      <li *ngFor="let currency of list">
        {{currency.id}} {{currency.name}}
      </li>
    </ul>
  `,
  styles: [`
    h2{
        font-family: 'Monserrat', sans-serif;
        font-weight: normal;
    }
  `]
})
export class CurrencyComponent implements OnInit {

  list : Currency[];

  constructor(private currencyService: CurrencyService) { }

  ngOnInit() : void {
    this.currencyService.getCurrencies()
      .then(currencies => this.list = currencies); 
  }
}