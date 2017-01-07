import { Component } from '@angular/core';

@Component({
  selector: 'my-app',
  template: `
   <commodities-toolbar></commodities-toolbar>
   <div class="container">
    <router-outlet></router-outlet>
   </div>
   <div class="footer">
      <i class="mdi mdi-github-box"></i>
      <i class="mdi mdi-linkedin-box"></i>
      <i class="mdi mdi-twitter-box"></i>
      Paco Navarro 2017
   </div>
  `,
  styles: [`
    .container {
      background-color: #272D4E;
      min-height: 85%;
      text-align: center;
      padding: 35px;
    }
    .footer {
      color: gray;
      border-top: 1px solid white;
      font-size: 10px;
      text-align: center;
    }
    .footer .mdi {
      cursor: pointer;
      font-size: 20px;
    }
  `]
})
export class AppComponent {
  title = 'Commodities works fine!';
}