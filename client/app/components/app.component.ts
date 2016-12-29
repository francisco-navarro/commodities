import { Component } from '@angular/core';

@Component({
  selector: 'my-app',
  template: `
   <commodities-toolbar></commodities-toolbar>
   <div>
    <router-outlet></router-outlet>
   </div>
  `,
  styles: [`
    div {
      text-align: center;
      padding: 35px;
    }
  `]
})
export class AppComponent {
  title = 'Commodities works fine!';
}