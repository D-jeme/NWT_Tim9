import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/users.service';
import { RegistracijaService } from '../../services/registracija.service';
import { Osoba } from '../../models/osoba';
import { OsobaLogin } from '../../models/osobaLogin';
import { Router} from '@angular/router';
import { USERS } from "../../users";

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

constructor(private _userService: UserService,private _registracijaService:RegistracijaService, private router: Router) {

  this.korisnici=[];
 }


  ngOnInit() {
  }


  getUsers(){
  }

  homePage()
  {
    this.router.navigateByUrl('/all');
  }
  print() {
      if(this.ime=='' || this.prezime=='' || this.email=='' || this.password==''|| this.url_slike=='')
      {
        this.errorMessage='Please fill all fields!';
        this.messageUspjesno='';
        return;
      }

      if((this.ime.length)<3 || (this.prezime.length)<3){
        this.errorMessage='Fields name and last name should contain at least three characters!';
        this.messageUspjesno='';
        return;
      }
      if(!this.email.includes('@') || !this.email.includes('.')){
        this.errorMessage='Invalid email field!';
        this.messageUspjesno='';
        return;
      }
      else if(this.password!=this.password2) {
        this.errorMessage='Passwords are not maching!';
        this.messageUspjesno='';
        return;
      }
      this.novi=new Osoba(this.ime, this.prezime, this.email, this.password, this.url_slike);
      USERS.push(this.novi);
      this.errorMessage="";
      this.router.navigateByUrl('/mainpage');
    }

    login(){
      if(this.email_login=='' || this.password_login=='')
      {
        this.errorMessage='Please fill all fields!';
        this.messageUspjesno='';
        return;
      }

      let objIndex = USERS.findIndex((obj => obj.email == this.email_login));

      if (objIndex < 0) {
        this.errorMessage = "Invalid email or password";
      } else {
        if (USERS[objIndex].password == this.password_login) {
          this.router.navigateByUrl('/all');
        } else {
          this.errorMessage = "Invalid email or password";
        }
      }
    }

}
