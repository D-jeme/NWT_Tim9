import { Component, OnInit,DoCheck } from '@angular/core';
import { UserService } from '../../services/users.service';
import { ArticlesService } from '../../services/articles.service';
import { CloudinaryOptions, CloudinaryUploader } from 'ng2-cloudinary';
import { FileUploader } from 'ng2-file-upload';
import { Osoba } from '../../models/osoba';
import {Popup} from 'ng2-opd-popup'
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


/*  constructor(private _registracijaService: RegistracijaService) {
}*/
uploader: CloudinaryUploader = new CloudinaryUploader(
       new CloudinaryOptions({ cloudName: 'du4cgdhn8', uploadPreset: 'd4hf19h6' })
   );
constructor(private _userService: UserService,private _articlesService:ArticlesService,private popup: Popup ) {

  this.korisnici=[];
  this.uploader.onSuccessItem = (item: any, response: string, status: number, headers: any): any => {
            let res: any = JSON.parse(response);
            console.log("slika",res.public_id);

          this.url="https://res.cloudinary.com/du4cgdhn8/image/upload/"+res.public_id;

 }}
 ngDoCheck() {
       let elem=document.getElementById("loader-1");
     if(this.uploader.isUploading){

       elem.style.display="block";
       this.poceoUpload=true;}
     else if(!this.uploader.isUploading && this.poceoUpload){

              elem.style.display="none";
    /*   elem.style.display="none";

                 this.poceoUpload=false;
       this.popup.options={
         header:"Upload-anje",
         color:"green",
         animationDuration:1.5,
           cancleBtnContent: "Ostanite ovdje",
           confirmBtnContent: "Vratite se na pocetnu",
         animation: "fadeInDown"*/
       }
        //   this.popup.show();
     }





  ngOnInit() {
    this._userService.getUsers().subscribe(data=>{
      this.korisnici=data;
      console.log("ovi su podaci",this.korisnici);
    })
  }

      upload() {

          this.uploader.uploadAll();}

  getUsers(){
    console.log("kliknuo");
    this._userService.getUsers().subscribe(data=>{
      this.korisnici=data;
      console.log("ovi su podaci",this.korisnici);
    })
  }
  print() {

    console.log("ime ",this.name);
    this._articlesService.addArticle(this.name,this.kratki_tekst,this.dugi_tekst,this.cijena,this.kolicina,this.popust,1,this.url)
    /*  if(this.ime=='' || this.prezime=='' || this.email=='' || this.password=='')
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
      this.errorMessage='';
      this.messageUspjesno='Uspjesno!';*/






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
