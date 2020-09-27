import { Component, OnInit } from '@angular/core';
import {Artikal} from '../../models/artikal'
import { CHART_ARTICLES } from "../../chart";

import { Router} from '@angular/router';

@Component({
  selector: 'app-mainpicture',
  templateUrl: './mainpicture.component.html',
  styleUrls: ['./mainpicture.component.css']
})
export class MainpictureComponent implements OnInit {

  artikli:Array<Artikal>
    errorMessage: String='';
    messageUspjesno: String='';

  constructor(private router:Router) {
    this.artikli = CHART_ARTICLES;
  }

  ngOnInit() {
  }

  removeItem(artikal) {

    if (artikal.kolicina >1) {
      let objIndex = CHART_ARTICLES.findIndex((obj => obj.id == artikal.id));
      CHART_ARTICLES[objIndex].kolicina -= 1;
      console.log("After update: ", CHART_ARTICLES[objIndex]);
      this.errorMessage='Uspješno ste uklonili artikal' + artikal.naziv;
    } else {
      CHART_ARTICLES.splice(artikal,1);
      this.errorMessage='Uspješno ste uklonili artikal' + artikal.naziv;
    }

  }

  logout() {
      this.router.navigateByUrl('/registracija');
  }

  getArticles()
  {
    this.router.navigateByUrl('/');
  }

  buyItems() {
    this.messageUspjesno = "You have successfully finished buying process. Enjoy your products."
  }


}
