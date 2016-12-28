import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent }         from './components/app.component';
import { DashboardComponent } from './components/dashboard.component';
import { ToolbarComponent } from './components/toolbar.component';

import { CurrencyService } from './services/currency.service';

import { AppRoutingModule }     from './app-routing.module';

@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    ToolbarComponent
  ],
  providers: [ CurrencyService ],
  bootstrap: [ AppComponent ]
})

export class AppModule { }