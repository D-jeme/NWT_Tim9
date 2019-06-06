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
<<<<<<< HEAD
    console.log("ima li te ",);
=======
    console.log("ima li te ");
>>>>>>> 18fb8284c514a004efe5b145325f277d13396225
    var body = JSON.stringify(clan);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    //headers.append('authorization', 'Bearer ' + localStorage.getItem("currentUser").token);
<<<<<<< HEAD
console.log("body",body);
=======
console.log(body);
>>>>>>> 18fb8284c514a004efe5b145325f277d13396225
    this._http.post(this.url + '/rest/users/',
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
