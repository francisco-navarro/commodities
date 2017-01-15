import { Component, OnInit } from '@angular/core';

import { CurrencyService } from '../services/currency.service';

import { Currency } from '../model/currency';

@Component({
  selector: 'currency',
  template: `
    <div class="list-card">
      <h2>List of currencies</h2>
      <ul>
        <li class="currency" *ngFor="let currency of list">
          <div 
            (click)="select(currency)"
            [class.selected]="currency === selected">
            <div class="id">{{currency.id}}</div>
            <div class="name">{{currency.name}}</div>
          </div>
        </li>
      </ul>
    </div>
    <div class="list-card" 
      *ngIf="selected">
      <h2>Currency {{selected.name}}</h2>
      <div class="detail line">
        <span *ngFor="let rate of selected.rates">
          {{rate.name}} {{rate.value}}
        </span>
      </div>
    </div>
  `,
  styles: [`
    h2 {
      font-family: 'Monserrat', sans-serif;
      font-weight: normal;
      margin: 6px;
    }
    ul, .detail {
      background-color: #272D4E;
      color: white;
      list-style: none;
      margin-bottom: 0;
      margin-top: 0;
      text-align: left;
      padding: 15px;
    }
    .currency {
      cursor: pointer;
    }
    .currency .id{
      display: table-cell;
      min-width: 100px;
    }
    .currency .name {
      display: table-cell;
    }
    .list-card {
      background: white;
      border-radius: 5px 5px 5px 5px;
      -moz-border-radius: 5px 5px 5px 5px;
      -webkit-border-radius: 5px 5px 5px 5px; 
      border: 1px solid #D7D7D7;
      margin-top: 10px;
      color: #272D4E;
      -webkit-box-shadow: 1px 1px 5px 0px rgba(0,0,0,2);
      -moz-box-shadow: 1px 1px 5px 0px rgba(0,0,0,2);
      box-shadow: 1px 1px 5px 0px rgba(0,0,0,2);
      width: 500px;
    }
    .selected {
      font-weight: bold;
      color: #aaa;
    }
  `]
})
export class CurrencyComponent implements OnInit {

  list : Currency[];
  selected : Currency;

  constructor(private currencyService: CurrencyService) { }

  ngOnInit() : void {
    this.currencyService.getCurrencies()
      .then(currencies => this.list = currencies); 
  }

  select(currency :Currency) : void {
    this.currencyService.getCurrency(currency)
      .then(response => this.selected = response);
  }
}