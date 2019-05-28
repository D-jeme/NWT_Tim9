import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/users.service';
import { Osoba } from '../../models/osoba';
@Component({
  templateUrl: './registracija.component.html',
  styleUrls: ['./registracija.component.css'],
  providers: [UserService],
})
export class RegistracijaComponent implements OnInit {

  korisnici:Array<any>
/*
  ime: String='';
  prezime: String='';
  email: String='';
  password: String='';
  password2: String='';
  url_slike: String='';
  errorMessage: String='';
  messageUspjesno: String='';
    novi: Osoba;
*/
/*  constructor(private _registracijaService: RegistracijaService) {
}*/

constructor(private _userService: UserService) {
  this.korisnici=[];
 }


  ngOnInit() {
    this._userService.getUsers().subscribe(data=>{
      this.korisnici=data;
      console.log("ovi su podaci",this.korisnici);
    })
  }


  getUsers(){
    console.log("kliknuo");
    this._userService.getUsers().subscribe(data=>{
      this.korisnici=data;
      console.log("ovi su podaci",this.korisnici);
    })
  }
  /*print() {
      if(this.ime=='' || this.prezime=='' || this.email=='' || this.password=='')
      {
        this.errorMessage='Molimo popunite sva polja!';
        this.messageUspjesno='';
        return;
      }
      else if(this.password!=this.password2) {
        this.errorMessage='Lozinke se ne podudaraju!';
        this.messageUspjesno='';
        return;
      }
      console.log("ima li te");
      this.novi=new Osoba(this.ime, this.prezime, this.email, this.password, this.url_slike);
      this._registracijaService.prijava(this.novi);
      this.errorMessage='';
      this.messageUspjesno='Uspjesno!';
    }*/


    /*this.errorMessage='';
    if(this.ime=='' || this.prezime=='' || this.email=='' || this.emailFakultet=='' || this.brojTelefona=='' ||
    this.brojIndeksa=='' || this.korisnickoIme=='' || this.lozinka=='' || this.lozinka2=='') {
      this.errorMessage='Molimo popunite sva polja!';
      this.messageUspjesno='';
      return;
    }
    else if(this.lozinka!=this.lozinka2) {
      this.errorMessage='Lozinke se ne podudaraju!';
      this.messageUspjesno='';
      return;
    }
    console.log("ima li te");
    this.novi=new RegistrovaniClan(this.ime, this.prezime, this.email, this.emailFakultet, this.brojTelefona, this.brojIndeksa,
    this.korisnickoIme, this.lozinka, this.odsjek, this.godinaStudija);
    this._registracijaService.prijava(this.novi);
    this.errorMessage='';
    this.messageUspjesno='Uspjesno!';*/

}
