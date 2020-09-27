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
  }

  ngOnInit() {
    this.nizArtikala = JSON.parse(localStorage.getItem("charts")) as Artikal[];
    if(this.nizArtikala.length == 0) this.emptyArray = true;
    console.log("Niiz ", this.nizArtikala);
    localStorage.setItem("charts", JSON.stringify(this.nizArtikala));
  }

  removeItem(artikal) {

    if (artikal.kolicina >1) {
      let objIndex = this.nizArtikala.findIndex((obj => obj.id == artikal.id));
      this.nizArtikala[objIndex].kolicina -= 1;
      this.errorMessage='Successfully removed article: ' + artikal.naziv;
    } else {
      this.nizArtikala.splice(artikal,1);
      this.errorMessage='Successfully removed article: ' + artikal.naziv;
    }

      if(this.nizArtikala.length == 0) this.emptyArray = true;
      localStorage.setItem("charts", JSON.stringify(this.nizArtikala))

  }

  logout() {
      localStorage.setItem("charts", JSON.stringify(this.nizArtikala))
      this.router.navigateByUrl('/');
  }

  getArticles()
  {
    localStorage.setItem("charts", JSON.stringify(this.nizArtikala))
    this.router.navigateByUrl('/all');
  }

  buyItems() {
    this.nizArtikala = [] as Artikal[];
    if(this.nizArtikala.length == 0) this.emptyArray = true;
    localStorage.setItem("charts", JSON.stringify(this.nizArtikala))
    this.messageUspjesno = "You have successfully finished buying process. Enjoy your products."
  }


}
