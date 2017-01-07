import { Component, OnInit } from '@angular/core';

import { CurrencyService } from '../services/currency.service';

@Component({
  selector: 'my-dashboard',
  template: `
    <div>
      <h2 class="title">
        <i class="material-icons icon" style="color: #FBC02D;">face</i>
        <span class="caption">Get started today</span>
      </h2>
    </div>
  `,
  styles: [`
    div {
      background-image: url("img/back-intro.png");
      background-origin: content-box;
      min-height: 400px;
    }
    .title {
      font-size: 1.2em;
      font-family: Tahoma;
      font-weight: 100;
      padding-top: 100px;
    }
  `]
})
export class DashboardComponent {
}