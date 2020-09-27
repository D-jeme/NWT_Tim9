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

}
