import { Component, OnInit, OnDestroy } from '@angular/core';
import { ArticlesService } from '../../services/articles.service';
import { Artikal } from '../../models/artikal';
import { ActivatedRoute } from '@angular/router'
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
    cijena: Number;
    popust: Number;
    stara_cijena: Number;
    slika: String;


constructor(private _articlesService: ArticlesService, private route: ActivatedRoute) {

  this.korisnici=[];

 }


  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
       this.id = params['id'];
 console.log("param*********",this.id);

});

this._articlesService.getArticle(this.id).subscribe(
  data=>{
    console.log("ovo je",data);

    this.naziv=data.naziv;
    this.kratki_tekst=data.kratki_tekst;
    this.dugi_tekst=data.dugi_tekst;
    this.cijena=data.cijena;
    this.popust=data.popust;
    this.slika=data.pictures.slika;
  }

//  this._articlesService.postaviStaruCijenu(Number(this.stara_cijena));

)

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
