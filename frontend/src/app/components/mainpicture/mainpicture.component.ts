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
    nizArtikala: Artikal[];
    emptyArray: boolean;

  constructor(private router:Router) {
        this.nizArtikala = JSON.parse(localStorage.getItem("charts")) as Artikal[];
        if(this.nizArtikala.length == 0) {
          this.emptyArray = true;
        } else {
          this.emptyArray = false;
        }
        localStorage.setItem("charts", JSON.stringify(this.nizArtikala));
    // this.nizArtikala = CHART_ARTICLES;
  }

  ngOnInit() {
    console.log("Local storage", localStorage.getItem("charts"));
    // localStorage.setItem("charts", JSON.stringify(CHART_ARTICLES));
    this.nizArtikala = JSON.parse(localStorage.getItem("charts")) as Artikal[];
    if(this.nizArtikala.length == 0) this.emptyArray = true;
    console.log("Niiz ", this.nizArtikala);
    localStorage.setItem("charts", JSON.stringify(this.nizArtikala));

    // console.log("Local storage 2", localStorage);
  }

  removeItem(artikal) {

    if (artikal.kolicina >1) {
      let objIndex = this.nizArtikala.findIndex((obj => obj.id == artikal.id));
      this.nizArtikala[objIndex].kolicina -= 1;
      console.log("After update: ", this.nizArtikala[objIndex]);
      this.errorMessage='Uspješno ste uklonili artikal' + artikal.naziv;
    } else {
      this.nizArtikala.splice(artikal,1);
      this.errorMessage='Uspješno ste uklonili artikal' + artikal.naziv;
    }

      if(this.nizArtikala.length == 0) this.emptyArray = true;
      localStorage.setItem("charts", JSON.stringify(this.nizArtikala))

  }

  logout() {
      localStorage.setItem("charts", JSON.stringify(this.nizArtikala))
      this.router.navigateByUrl('/registracija');
  }

  getArticles()
  {
    localStorage.setItem("charts", JSON.stringify(this.nizArtikala))
    this.router.navigateByUrl('/');
  }

  buyItems() {
    this.nizArtikala = [] as Artikal[];
    if(this.nizArtikala.length == 0) this.emptyArray = true;
    this.messageUspjesno = "You have successfully finished buying process. Enjoy your products."
  }


}
