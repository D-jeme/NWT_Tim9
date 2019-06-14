import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import { Router} from '@angular/router';

@Injectable()
export class UserService{
  url:string="http://localhost:8084/users/rest/users";

  constructor(private _http:Http){}

  getUsers(){
    console.log("tu saam");
    var headers=new Headers();
    headers.append('Content-Type', 'application/json');
  //  headers.append('Access-Control-Allow-Origin','https://localhost:8082');
    console.log("header je ",headers);

    return this._http.get( this.url + '/', {headers:headers
    } )
      .map( data => {
        console.log("Korisnici",data);
        return data.json();
      } );
  }




    getUser(id:Number){

      console.log("tu saam");
      var headers=new Headers();
      headers.append('Content-Type', 'application/json');
    //  headers.append('Access-Control-Allow-Origin','https://localhost:8082');
      console.log("header je ",headers);
      this.url+ '/';
      return this._http.get( this.url +'/'+id, {headers:headers
      } )
        .map( data => {
          console.log("User",data);
          return data.json();
        } );
    }

    updateUser(id:Number,ime:String,prezime:String,email:String){

      var body = JSON.stringify({ime,prezime,email});

      var headers = new Headers();
      headers.append('Content-Type', 'application/json');
      //headers.append('authorization', 'Bearer ' + localStorage.getItem("currentUser").token);
    console.log("body",body);
      return this._http.put(this.url + '/novi/'+id,
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

   /*deleteU(email:String){
      var body = JSON.stringify({email});

      var headers = new Headers();
      headers.append('Content-Type', 'application/json');
      //headers.append('authorization', 'Bearer ' + localStorage.getItem("currentUser").token);
    console.log("body",body);
      return this._http.delete(this.url + '/',

        {
          headers: headers
        }
      ).map(
        data => {
            console.log(data);
            return data.json();

          });
    }*/



}
