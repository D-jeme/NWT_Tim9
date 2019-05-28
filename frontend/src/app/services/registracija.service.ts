import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Osoba } from '../models/osoba';
import 'rxjs/add/operator/map';

@Injectable()
export class RegistracijaService {
  url:string="http://localhost:8081";

  constructor(private _http:Http){}

  registracija(osoba: Osoba){


  }

  prijava(clan: Osoba) {
    console.log("ima li te ");
    var body = JSON.stringify({clan: Osoba});
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    //headers.append('authorization', 'Bearer ' + localStorage.getItem("currentUser").token);
console.log(body);
    this._http.post(this.url + '/user/login',
    body,
      {
        headers: headers
      }
    ).map(res=> res.json()).subscribe(
      data => {
          console.log(data);
          localStorage.setItem('currentUser', JSON.stringify({ token: data.token, clan: data.clan }));
        },
    error =>{
      console.log(error);
    }

    );
}
}
