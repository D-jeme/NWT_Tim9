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
  url_slike: String='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS0WMIMCWCCZ8LxgACZMv3eO441cvSIsUIFH_8aR_e7JGy3UTiJTA';
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

  homePage()
  {
    this.router.navigateByUrl('/');
  }
  print() {
      if(this.ime=='' || this.prezime=='' || this.email=='' || this.password==''|| this.url_slike=='')
      {
        this.errorMessage='Molimo popunite sva polja!';
        this.messageUspjesno='';
        return;
      }

      if((this.ime.length)<3 || (this.prezime.length)<3){
        this.errorMessage='Polja ime i prezime trebaju sadržavati minimalno 3 karaktera!';
        this.messageUspjesno='';
        return;
      }
      if(!this.email.includes('@') || !this.email.includes('.')){
        this.errorMessage='Polje email nije ispravno uneseno!';
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
      //this.messageUspjesno='Uspjesno!';
    }

    login(){
      console.log("LOGOVII",this.errorMessage);
          console.log("LOGOVII",this.messageUspjesno);
  if(this.email_login=='' || this.password_login=='')
  {
    this.errorMessage='Molimo popunite sva polja!';
    this.messageUspjesno='';
    return;
  }  console.log("ima li te");
    this.noviLogin=new OsobaLogin(this.email_login, this.password_login);
    this._registracijaService.login(this.noviLogin).subscribe(data=>{

      localStorage.setItem('key', data.data.id);
      if(data.data.role!=null)
     localStorage.setItem('uloga', data.data.role.tip);
     else
    localStorage.setItem('uloga', 'user');

      console.log("sad",data);
      if(data.data==null)   this.errorMessage='Email ili password nisu validni';
      if(data.data.role!=null){ this.messageUspjesno='Uspješno ste se ulogovali';   console.log('Ispiiis', this.messageUspjesno);  this.router.navigateByUrl('/aarticles');}///ovdje dodaj admin rutu
      else { this.messageUspjesno='Uspješno ste se ulogovali 1'; console.log('Ispiiis', this.messageUspjesno); this.router.navigateByUrl('/');
      console.log("podaci o prijavi",data.data.role);}

    });

    setTimeout(()=>{

      this.errorMessage='Email ili password nisu validni';

    },500)
    console.log('Ispiiis', this.messageUspjesno);

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
