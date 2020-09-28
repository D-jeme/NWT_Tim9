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
  cardType: String='';
  cardNumber: String='';
  securityCode: String='';
  expirationDate: String='';


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

    var cardno = /^(?:(4[0-9]{12}(?:[0-9]{3})?)|(5[1-5][0-9]{14})|(6(?:011|5[0-9]{2})[0-9]{12})|(3[47][0-9]{13})|(3(?:0[0-5]|[68][0-9])[0-9]{11})|((?:2131|1800|35[0-9]{3})[0-9]{11}))$/;
    var expiration = /^(0[1-9]|1[0-2])\/?([0-9]{4}|[0-9]{2})$/;


    var expirationDateArray = this.expirationDate.split("/");
    var month;
    var year;
    var validExpirationDateFormat = false;

    if(expirationDateArray.length !== 2) {
      validExpirationDateFormat = false;
    } else if(/^-?\d+$/.test(expirationDateArray[0]) && /^-?\d+$/.test(expirationDateArray[1])) {
        validExpirationDateFormat = true;
        month = parseInt(expirationDateArray[0],10);
        year = parseInt(expirationDateArray[1],10);
      } else {
        validExpirationDateFormat = false;
      }


      if(this.ime=='' || this.prezime=='' || this.email=='' || this.password==''|| this.password2=='' || this.cardNumber=='' || this.securityCode=='' || this.expirationDate == '' )
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
      } else if(!this.cardNumber.match(cardno)) {
          this.errorMessage='Invalid card number. If entered number contains spaces, remove them!';
          this.messageUspjesno='';
          return;
        } else if(!validExpirationDateFormat) {
          this.errorMessage='Expiration date should be in format 11/22.'
          return;
        } else if(month<9 || month>12 || year<20 || year>30) {
          this.errorMessage='Invalid expiration date!';
          return;
        }
      this.novi=new Osoba(this.ime, this.prezime, this.email, this.password, this.password2);
      USERS.push(this.novi);
      this.errorMessage="";

      this.router.navigateByUrl('/all');
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
        this.errorMessage = "Invalid email";
      } else {
        if (USERS[objIndex].password == this.password_login) {
          this.router.navigateByUrl('/all');
        } else {
          this.errorMessage = "Invalid email or password";
        }
      }
    }

}
