import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Osoba } from '../models/osoba';
import { OsobaLogin } from '../models/osobaLogin';
import 'rxjs/add/operator/map';

@Injectable()
export class RegistracijaService {
  url:string="http://localhost:8081";

  constructor(private _http:Http){}

  registracija(osoba: Osoba){


  }

  prijava(clan: Osoba) {

    var body = JSON.stringify(clan);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    //headers.append('authorization', 'Bearer ' + localStorage.getItem("currentUser").token);

console.log(body);
    this._http.post(this.url + '/rest/users/',
    body,
      {
        headers: headers
      }
    ).map(res=> res.json()).subscribe(
      data => {
          console.log(data);
          //localStorage.setItem('currentUser', JSON.stringify({ token: data.token, clan: data.clan }));
        },
    error =>{
      console.log(error);
    }

    );
}




login(clan: OsobaLogin){
  console.log("ima li te ", clan);
  var body = JSON.stringify(clan);
  var headers = new Headers();
  headers.append('Content-Type', 'application/json');
  //headers.append('authorization', 'Bearer ' + localStorage.getItem("currentUser").token);
console.log(body);
return this._http.post(this.url + '/rest/users/login',
  body,
    {
      headers: headers
    }
  ).map(
    data => {
        console.log('login podaci',data);
      //  localStorage.setItem('currentUser', JSON.stringify({ token: data.token, clan: data.clan }));
        return data.json();
      },error=>{return null;}

  );
}
}
