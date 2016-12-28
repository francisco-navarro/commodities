import { Component } from '@angular/core';

@Component({
  selector: 'my-app',
  template: `
   <commodities-toolbar></commodities-toolbar>
   <router-outlet></router-outlet>
  `
})
export class AppComponent {
  title = 'Commodities works fine!';
}