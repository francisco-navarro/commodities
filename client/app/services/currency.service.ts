import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Currency } from '../model/currency';

@Injectable()
export class CurrencyService {

  constructor(private http: Http) { }

  getCurrencies(): Promise<Currency[]> {
    return this.http.get('http://openexchangerates.org/api/currencies.json')
      .toPromise()
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
}