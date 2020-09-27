import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import { Router} from '@angular/router';

@Injectable()
export class UserService{

  constructor(private _http:Http){}

  getUsers(){
  }




    getUser(id:Number){
    }

    updateUser(id:Number,ime:String,prezime:String,email:String){
    }


}
