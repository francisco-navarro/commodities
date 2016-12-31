import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule }    from '@angular/http';

import { AppComponent }       from './components/app.component';
import { DashboardComponent } from './components/dashboard.component';
import { ToolbarComponent }   from './components/toolbar.component';
import { CurrencyComponent }  from './components/currency.component';

import { CurrencyService } from './services/currency.service';

import { AppRoutingModule } from './app-routing.module';

@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    CurrencyComponent,
    ToolbarComponent
  ],
  providers: [ CurrencyService ],
  bootstrap: [ AppComponent ]
})

export class AppModule { }