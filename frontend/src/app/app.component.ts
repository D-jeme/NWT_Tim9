import { Component } from '@angular/core';
import {PRODUCTS} from "./products";
import {Artikal} from "./models/artikal"

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app works!';
  public productList: Artikal[]= PRODUCTS;

}
