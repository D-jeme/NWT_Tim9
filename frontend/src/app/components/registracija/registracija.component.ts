import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/users.service';
import { RegistracijaService } from '../../services/registracija.service';
import { Osoba } from '../../models/osoba';
import { OsobaLogin } from '../../models/osobaLogin';
import { Router} from '@angular/router';
@Component({
  templateUrl: './registracija.component.html',
  styleUrls: ['./registracija.component.css'],
  providers: [UserService,RegistracijaService],
})
export class RegistracijaComponent implements OnInit {

  korisnici:any;
  korisniklogin:any;

  ime: String='';
  prezime: String='';
  email: String='';
  password: String='';
  password2: String='';
  url_slike: String='';
  errorMessage: String='';
  messageUspjesno: String='';
  novi: Osoba;
  email_login: String='';
  password_login: String='';
  noviLogin: OsobaLogin;

/*  constructor(private _registracijaService: RegistracijaService) {
}*/

constructor(private _userService: UserService,private _registracijaService:RegistracijaService, private router: Router) {

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
  print() {
      if(this.ime=='' || this.prezime=='' || this.email=='' || this.password==''|| this.url_slike=='')
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
      console.log("korisnik moj je",this.novi);
      this._registracijaService.prijava(this.novi);

      this.errorMessage="";
      this.messageUspjesno='Uspjesno!';
    }

    login(){
  if(this.email_login=='' || this.password_login=='')
  {
    this.errorMessage='Molimo popunite sva polja!';
    this.messageUspjesno='';
    return;
  }  console.log("ima li te");
    this.noviLogin=new OsobaLogin(this.email_login, this.password_login);
    this._registracijaService.login(this.noviLogin).subscribe(data=>{

      if(data.data.role!=null)this.router.navigateByUrl('/aarticles');///ovdje dodaj admin rutu
      else this.router.navigateByUrl('/');
      console.log("podaci o prijavi",data.data.role.id);

    });
    this.errorMessage='';
    this.messageUspjesno='Uspjesno!';

}


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
