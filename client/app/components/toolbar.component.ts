import { Component } from '@angular/core';

@Component({
  selector: 'commodities-toolbar',
  template: `
   <div>
    <h1 class="title">commodities play</h1>
    <ul>
      <li *ngFor="let option of options" 
        (click)="onSelect(option)"
        [class.selected]="option === selected">
        {{option.text}}
      </li>
    </ul>
   </div>
  `,
  styles: [`
    div {
      display: inline-block;
      background-color: white;
      color: #263153;
      width: 100%;
    }
    ul li {
      color: black;
      list-style-type: none;
    }
    li { 
      cursor: pointer;
      float: left;
      margin-left: 10px;
      padding: 10px;
      padding-bottom: 20px;
    }
    ul {
      display: inline-block;
      float: right;
      margin: 0;
      margin-top: 10px;
    }
    li:hover {
      background-color: #CFD8DC !important;
      border-bottom: 2px solid #CFD8DC;
    }
    .title {
      margin: 10px;
      float: left;
    }
    .selected {
      background-color: #CFD8DC !important;
      border-bottom: 2px solid green !important;
    }
  `]
})
export class ToolbarComponent {
  options: Option[] = [
    new Option('Commodities'),
    new Option('Currencies'),
    new Option('Markets'),
    new Option('Sign Up'), 
    new Option('Login')
  ];
  selected: Option;

  onSelect(selected: Option): void {
    this.selected = selected;
  }
}

class Option {
  text: string;
  constructor(text: string) {
      this.text = text;
  }
}