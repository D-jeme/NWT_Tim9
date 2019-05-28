import { Injectable, Input, Output, EventEmitter } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import {Md5} from 'ts-md5/dist/md5';

@Injectable()

export class PrijavaService {
  url:string="http://localhost:8080";
  rola:string;
prijavljen:boolean;




  constructor(private _http:Http){this.rola="";this.prijavljen=false}


/*


  prijava(user:string, password:string) {

    var sifra=Md5.hashStr(password);
    if(user=="superadmin")sifra=password;
    var body = JSON.stringify({username: user, password: sifra});
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    //headers.append('authorization', 'Bearer ' + localStorage.getItem("currentUser").token);

    this._http.post(this.url + '/api/moderators/login',
    body,
      {
        headers: headers,
        withCredentials: true
      }
    ).map(res=> res.json()).subscribe(
      data => {

          this.rola=data.role;
          this.prijavljen=true;

          console.log("prijavljen je :",data.username);
          localStorage.setItem('signed',JSON.stringify(this.prijavljen));
          localStorage.setItem('reload',JSON.stringify(true));
          localStorage.setItem('username',data.username);
          localStorage.setItem('rola',data.role);



        },
    error =>{
      console.log(error);
      this.prijavljen=false;
    }

    );
}
dodajModeratora(user:string, password:string) {
  var sifra=Md5.hashStr(password);


  var body = JSON.stringify({username: user, password: sifra});
  var headers = new Headers();
  headers.append('Content-Type', 'application/json');
  //headers.append('authorization', 'Bearer ' + localStorage.getItem("currentUser").token);

  this._http.post(this.url + '/api/moderators/',
  body,
    {
      headers: headers,
      withCredentials: true
    }
  ).map(res=> res.json()).subscribe(
    data => {

      },
  error =>{
    console.log(error);
    this.prijavljen=false;
  }

);
}

dodajAdmina(user:string, password:string) {
  var sifra=Md5.hashStr(password);
  var body = JSON.stringify({username: user, password: sifra});
  var headers = new Headers();
  headers.append('Content-Type', 'application/json');
  //headers.append('authorization', 'Bearer ' + localStorage.getItem("currentUser").token);

  this._http.post(this.url + '/api/moderators/admin',
  body,
    {
      headers: headers,
      withCredentials: true
    }
  ).map(res=> res.json()).subscribe(
    data => {
      },
  error =>{
    console.log(error);
    this.prijavljen=false;
  }

  );
}

updateAdmina(user:string, password:string) {
  var sifra=Md5.hashStr(password);
  var body = JSON.stringify({username: user, password: sifra});
  var headers = new Headers();
  headers.append('Content-Type', 'application/json');
  //headers.append('authorization', 'Bearer ' + localStorage.getItem("currentUser").token);

  this._http.put(this.url + '/api/moderators/admin',
  body,
    {
      headers: headers,
      withCredentials: true
    }
  ).map(res=> res.json()).subscribe(
    data => {
      },
  error =>{
    console.log(error);
    this.prijavljen=false;
  }

  );
}


updateModeratora (user:string, password:string) {
  var sifra=Md5.hashStr(password);
  var body = JSON.stringify({username: user, password: sifra});
  var headers = new Headers();
  headers.append('Content-Type', 'application/json');
  //headers.append('authorization', 'Bearer ' + localStorage.getItem("currentUser").token);

  this._http.put(this.url + '/api/moderators/',
  body,
    {
      headers: headers,
      withCredentials: true
    }
  ).map(res=> res.json()).subscribe(
    data => {
      },
  error =>{
    console.log(error);
    this.prijavljen=false;
  }

  );
}
odjava(){
  console.log("odjavljen sam");
  var headers = new Headers();
  headers.append('Content-Type', 'application/json');

  return this._http.get( this.url + '/api/moderators/logout', {
    headers: headers,
    withCredentials: true
  } )
    .map( data => {
      console.log("sta se desava");

      return data.json();
    } );


}

izbrisiModeratora(osoba: Osoba) {
  var body = JSON.stringify({username: osoba.username});
  var headers = new Headers();
  headers.append('Content-Type', 'application/json');
  //headers.append('authorization', 'Bearer ' + localStorage.getItem("currentUser").token);

  this._http.delete(this.url + '/api/moderators',

    {
      body:body,
      headers: headers,
      withCredentials: true
    }
  ).subscribe((ok)=>{console.log(ok)});
}



dajModeratore(){
  console.log("tu saam");
  var headers = new Headers();
  headers.append('Content-Type', 'application/json');

  return this._http.get( this.url + '/api/moderators', {
    headers: headers,
    withCredentials: true
  } )
    .map( data => {

      return data.json();
    } );


}
dajAdmine(){
  console.log("tu saam");
  var headers = new Headers();
  headers.append('Content-Type', 'application/json');

  return this._http.get( this.url + '/api/moderators/admin', {
    headers: headers,
    withCredentials: true
  } )
    .map( data => {

      return data.json();
    } );


}
dajRolu(){
  return this.rola;
}
 dajPrijavu():boolean{
  console.log("servis",this.prijavljen);
  return this.prijavljen;
}*/

}
