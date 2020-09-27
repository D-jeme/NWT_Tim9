import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import { Router} from '@angular/router';

@Injectable()
export class ArticlesService{
  url:string="http://localhost:8084/articles/articles";
  idArtikla:String;


  constructor(private _http:Http){this.idArtikla="";}

  postaviIdArtikla(id:String){
  }

  dajArtikal( ) {
  }

  getArticle(id:Number){
  }

  getArticles(){
  }

  addArticle(name:String,kratki_tekst:String,dugi_tekst:String,cijena:Number,kolicina:Number,popust:Number,objavio:Number=1,slika:String){
  }


  updateAr(id:Number,kratki_tekst:String,dugi_tekst:String,cijena:Number,popust:Number){
    var body = JSON.stringify({kratki_tekst,dugi_tekst,cijena,popust,});
  }

}
