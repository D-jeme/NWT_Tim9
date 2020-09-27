import { Component, OnInit, OnDestroy } from '@angular/core';
import { ArticlesService } from '../../services/articles.service';
import {Artikal} from '../../models/artikal'
import { PRODUCTS  } from "../../products";
import { CHART_ARTICLES } from "../../chart";
import { ActivatedRoute } from '@angular/router';

import { Router} from '@angular/router';

@Component({
  templateUrl: './previewArticle.component.html',
  styleUrls: ['./previewArticle.component.css'],
  providers: [ArticlesService],
})
export class PreviewArticleComponent implements OnInit, OnDestroy {
  korisnici:Array<any>;
    Artikal:any;
    id: number;
    private sub: any;
    artikli: Artikal;
    naziv: String;
    kratki_tekst: String;
    dugi_tekst: String;
    cijena: number;
    popust: number;
    stara_cijena: any;
    img: String;


constructor(private _articlesService: ArticlesService, private route: ActivatedRoute,private router:Router) {

  this.korisnici=[];

 }
 logout()
 {
   localStorage.setItem('key', '');
  localStorage.setItem('uloga', '');
  this.router.navigateByUrl('/registracija');
 }


  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
    this.id = params['id'];
    console.log("param*********",this.id);
  });

  PRODUCTS.forEach(product => {
    if(product.id == this.id) {
      console.log("Pronadjen ID ", product.naziv);

      this.naziv=product.naziv;
      this.kratki_tekst=product.kratki_tekst;
      this.dugi_tekst=product.dugi_tekst;
      this.cijena=product.cijena;
      this.popust=product.popust;
      this.img=product.img;
      // this.slika=product.pictures.slika;
      this.stara_cijena=(product.cijena/(100-product.popust)*100).toFixed(2);
    }
  });
  }

  print(id) {
    let exists = false;
    let chosenArticle = {} as Artikal;
    console.log("Id jee", id);

    PRODUCTS.forEach(element => {
      if(element.id == id) {
        chosenArticle = element;
      }
    });

    CHART_ARTICLES.forEach(article => {
      if(article.id == id) {
        exists = true;
      }
    });


    if (!exists) {
      CHART_ARTICLES.push(chosenArticle);
      } else {
      let objIndex = CHART_ARTICLES.findIndex((obj => obj.id == id));
      CHART_ARTICLES[objIndex].kolicina += 1;
      console.log("After update: ", CHART_ARTICLES[objIndex])

    }

       this.router.navigateByUrl('/mainpicture');

  }

// this._articlesService.getArticle(this.id).subscribe(
//   data=>{
//     console.log("ovo je",data);


  // }



//  this._articlesService.postaviStaruCijenu(Number(this.stara_cijena));

// )

/*
    this._articlesService.postaviIdArtikla(String(this.id));

    this._articlesService.dajArtikal().subscribe(
      data =>{
        // Treba popuniti event koji dobijamo i provjeravati
        // kojeg je tipa evetn i u zavisnoti od toga birati nacin popunjavanja
console.log("velicina",data );
this.artikli=data;
console.log("artikliiii",this.artikli);
console.log("artikliiii",this.artikli.naziv);
console.log("slikaaa", this.artikli.pictures);

this.naziv=this.artikli.naziv;
this.kratki_tekst=this.artikli.kratki_tekst;
this.dugi_tekst=this.artikli.dugi_tekst;
this.cijena=this.artikli.cijena;
this.popust=this.artikli.popust;


*/
//this.nova_cijena=this.cijena-this.cijena*this.pop;


      /*  console.log("velicina",data._id );
        this.event=new Eventpreview(data._id, data.title, "Lokacija", data.longText,
         data.imgUrl, this.MONTHS[date.getMonth()], String(date.getDate()), String(end.getDate()),
         data.participantText, data.placeText, data.participationText);
         if(this.event.mjesto==null && this.event.ucesce==null && this.event.ucesce==null) this.izvjestaj=true;
         else this.izvjestaj=false;
    }
  );*/


  openMyProfile()
{
   this.router.navigateByUrl('/updateProfile');
}


homePage()
{
  this.router.navigateByUrl('/');
}
  ngOnDestroy(){

}


/*  getArticle(){
    console.log("kliknuo");
    this._articlesService.getArticle().subscribe(data=>{
      this.korisnici=data;
      console.log("ovi su podaci",this.korisnici);
    })
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
