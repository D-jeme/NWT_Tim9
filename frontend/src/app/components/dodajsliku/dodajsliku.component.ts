import { Component, OnInit,DoCheck } from '@angular/core';
import { UserService } from '../../services/users.service';
import { ArticlesService } from '../../services/articles.service';
import { CloudinaryOptions, CloudinaryUploader } from 'ng2-cloudinary';
import { FileUploader } from 'ng2-file-upload';
import { Osoba } from '../../models/osoba';
import {Popup} from 'ng2-opd-popup'

import { Router} from '@angular/router';
@Component({
  templateUrl: './dodajsliku.component.html',
  styleUrls: ['./dodajsliku.component.css'],
  providers: [UserService,ArticlesService],
})
export class DodajslikuComponent implements OnInit,DoCheck {

  korisnici:Array<any>
 poceoUpload:boolean=false;
  name: String='';
  kratki_tekst: String='';
  dugi_tekst: String='';
  cijena: Number;
  kolicina: Number;
  popust: Number;
  slika: String='';
  url:String;
  errorMessage: String='';
  messageUspjesno: String='';
  proizvod:String='';
  email:String='';

constructor(private _userService: UserService, private router: Router,private _articlesService:ArticlesService,private popup: Popup ) {

  this.korisnici=[];
}

logout()
{
  localStorage.setItem('key', '');
 localStorage.setItem('uloga', '');
 this.router.navigateByUrl('/');
}


  ngOnInit() {
  }

  homePage()
  {
    this.router.navigateByUrl('/all');
  }

  openMyProfile()
  {
     this.router.navigateByUrl('/updateProfile');
  }

  print() {

    console.log("ime ",this.name);


    if(this.name=='' || this.kratki_tekst=='' || this.dugi_tekst=='' || this.cijena==null || this.kolicina==null)
    {
      this.errorMessage='Molimo popunite sva polja!';
      this.messageUspjesno='';
      return;
    }

    this.messageUspjesno='Uspješno ste dodali novi artikal.';

    }

    ngOnDestroy(){

    }

    ngDoCheck() {

    }

}
