import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import { Router} from '@angular/router';

@Injectable()
export class ArticlesService{
  url:string="http://localhost:8084/articles/articles";

  constructor(private _http:Http){}

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


}
