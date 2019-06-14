import { Component, OnInit, OnDestroy } from '@angular/core';
import { ArticlesService } from '../../services/articles.service';
import { Artikal } from '../../models/artikal';
import { ActivatedRoute } from '@angular/router';

import { Router} from '@angular/router';
@Component({
  templateUrl: './apreviewArticle.component.html',
  styleUrls: ['./apreviewArticle.component.css'],
  providers: [ArticlesService],
})
export class AdminPreviewArticleComponent implements OnInit, OnDestroy {
  korisnici:Array<any>;
    Artikal:any;
    id: Number;
    naziv: String='';
    artikli: Artikal;
    private sub:any;
    kratki_tekst: String='';
    dugi_tekst: String='';
    cijena: Number;
    kolicina: Number;
    popust: Number;
    slika: String;
    errorMessage: String='';
    messageUspjesno: String='';
        proizvod:String='';


constructor(private _articlesService: ArticlesService, private route: ActivatedRoute, private router: Router) {

  this.korisnici=[];

 }
 logout()
 {
   localStorage.setItem('key', '');
  localStorage.setItem('uloga', '');
  this.router.navigateByUrl('/registracija');
 }


  ngOnInit() {
    if(localStorage.getItem('uloga')=='admin') this.router.navigateByUrl('/registracija');
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

)


  }

  updateArticle(){

    if(this.kratki_tekst=='' || this.dugi_tekst=='' || this.cijena==null || this.popust==null)
    {
      this.errorMessage='Molimo popunite sva polja!';
      this.messageUspjesno='';
      return;
    }

    this._articlesService.updateAr(this.id,this.kratki_tekst,this.dugi_tekst,this.cijena,this.popust).subscribe(data=>{console.log("vraceno",data); this.proizvod=data.MESSAGE;})
  }

  ngOnDestroy(){

}



}
