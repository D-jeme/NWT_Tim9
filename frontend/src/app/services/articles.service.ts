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
    this.idArtikla='/'+id;
    console.log("Id artikla",this.idArtikla);
  }

  dajArtikal( ) {
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this._http.get( this.url + this.idArtikla , {
      headers: headers
    } )
      .map( data => {
        return data.json();
      } );
  }

  getArticle(id:Number){

    console.log("tu saam");
    var headers=new Headers();
    headers.append('Content-Type', 'application/json');
  //  headers.append('Access-Control-Allow-Origin','https://localhost:8082');
    console.log("header je ",headers);
    this.url=this.url + '/';
    return this._http.get( this.url +id, {headers:headers
    } )
      .map( data => {
        console.log("Artikli",data);
        return data.json();
      } );
  }



  getArticles(){
    console.log("tu saam");
    var headers=new Headers();
    headers.append('Content-Type', 'application/json');
  //  headers.append('Access-Control-Allow-Origin','https://localhost:8082');
    console.log("header je ",headers);

    return this._http.get( this.url + '/', {headers:headers
    } )
      .map( data => {
        console.log("Artikli",data);
        return data.json();
      } );
  }




  addArticle(name:String,kratki_tekst:String,dugi_tekst:String,cijena:Number,kolicina:Number,popust:Number,objavio:Number=1,slika:String){

    var body = JSON.stringify({"naziv":name,kratki_tekst,dugi_tekst,cijena,kolicina,popust,"objavio":1,"pictures":{"broj":1,slika}});

    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    //headers.append('authorization', 'Bearer ' + localStorage.getItem("currentUser").token);
console.log("body",body);
    return this._http.post(this.url + '/',
    body,
      {
        headers: headers
      }
    ).map(
      data => {
          console.log(data);
          return data.json();

        });
  }


  updateAr(id:Number,kratki_tekst:String,dugi_tekst:String,cijena:Number,popust:Number){

    var body = JSON.stringify({kratki_tekst,dugi_tekst,cijena,popust,});

    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    //headers.append('authorization', 'Bearer ' + localStorage.getItem("currentUser").token);
  console.log("body",body);
    return this._http.put(this.url + id,
    body,
      {
        headers: headers
      }
    ).map(
      data => {
          console.log(data);
          return data.json();

        });
  }



//postaviStaruCijenu(stara_cijena: Number)
}
