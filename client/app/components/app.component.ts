import { Component } from '@angular/core';

@Component({
  selector: 'my-app',
  template: `
   <commodities-toolbar></commodities-toolbar>
   <div class="container">
    <router-outlet></router-outlet>
   </div>
   <div class="footer"></div>
  `,
  styles: [`
    .container {
      background-color: #272D4E;
      min-height: 85%;
      text-align: center;
      padding: 35px;
    }
    .footer {
      border-top: 1px solid white;
      text-align: center;
    }
  `]
})
export class AppComponent {
  title = 'Commodities works fine!';
}