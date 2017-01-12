import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Currency } from '../model/currency';

@Injectable()
export class CurrencyService {

  constructor(private http: Http) { }

  getCurrencies(): Promise<Currency[]> {
    return this.http.get('http://openexchangerates.org/api/currencies.json').toPromise()
      .then(response =>
        Object.keys(response.json()).reduce((arr, next) => {
          arr.push({
            id: next,
            name: response.json()[next]
          })
          return arr;
        }, []) as Currency[]
      );
  }
 getCurrency(currency: Currency): Promise<Currency> {
    var url = `https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22USD${currency.id}%22%2C%20%22USD${currency.id}%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=`;
    return this.http.get(url).toPromise()
      .then(response => {
        response.json().query.results.rate;
        return currency;
      });
  }
}