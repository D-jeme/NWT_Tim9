import { Component, OnInit, OnDestroy } from '@angular/core';
import { ArticlesService } from '../../services/articles.service';
import {Artikal} from '../../models/artikal'
import { PRODUCTS  } from "../../products";
import { CHART_ARTICLES } from "../../chart";
import { ActivatedRoute } from '@angular/router';

import { Router} from '@angular/router';

@Component({
  templateUrl: './previewArticle.component.html',
  styleUrls: ['./previewArticle.component.css'],
  providers: [ArticlesService],
})
export class PreviewArticleComponent implements OnInit, OnDestroy {
  korisnici:Array<any>;
    Artikal:any;
    id: number;
    private sub: any;
    artikli: Artikal;
    naziv: String;
    kratki_tekst: String;
    dugi_tekst: String;
    cijena: number;
    popust: number;
    stara_cijena: any;
    img: String;
    nizArtikala: Artikal[];


constructor(private _articlesService: ArticlesService, private route: ActivatedRoute,private router:Router) {

  this.korisnici=[];

 }
 logout()
 {
   localStorage.setItem('key', '');
  localStorage.setItem('uloga', '');
  this.router.navigateByUrl('/');
 }


  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
    this.id = params['id'];

    this.nizArtikala = JSON.parse(localStorage.getItem("charts")) as Artikal[];
  });

  PRODUCTS.forEach(product => {
    if(product.id == this.id) {

      this.naziv=product.naziv;
      this.kratki_tekst=product.kratki_tekst;
      this.dugi_tekst=product.dugi_tekst;
      this.cijena=product.cijena;
      this.popust=product.popust;
      this.img=product.img;
      this.stara_cijena=(product.cijena/(100-product.popust)*100).toFixed(2);
    }
  });
  }

  print(id) {
    let exists = false;
    let chosenArticle = {} as Artikal;

    PRODUCTS.forEach(element => {
      if(element.id == id) {
        chosenArticle = element;
      }
    });

    this.nizArtikala.forEach(article => {
      if(article.id == id) {
        exists = true;
      }
    });


    if (!exists) {
      this.nizArtikala.push(chosenArticle);
      } else {
      let objIndex = this.nizArtikala.findIndex((obj => obj.id == id));
      this.nizArtikala[objIndex].kolicina += 1;

    }
    localStorage.setItem("charts", JSON.stringify(this.nizArtikala))
     this.router.navigateByUrl('/mainpicture');
  }

  openMyProfile()
{
   this.router.navigateByUrl('/updateProfile');
}


homePage()
{
  localStorage.setItem("charts", JSON.stringify(this.nizArtikala));
  this.router.navigateByUrl('/all');
}

cartPreview() {
  this.router.navigateByUrl('/mainpicture')
}

  ngOnDestroy(){

}


}
