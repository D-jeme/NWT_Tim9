import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Osoba } from '../models/osoba';
import { OsobaLogin } from '../models/osobaLogin';
import 'rxjs/add/operator/map';

@Injectable()
export class RegistracijaService {

  constructor(private _http:Http){}

  registracija(osoba: Osoba){


  }

  prijava(clan: Osoba) {
}
  login(clan: OsobaLogin){

  }
}
