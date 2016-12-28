import { Injectable } from '@angular/core';

import { Currency } from '../model/currency';

@Injectable()
export class CurrencyService {
  getCurrencies(): Promise<Currency[]> {
    return Promise.resolve(<Currency[]>[]);
  }
}