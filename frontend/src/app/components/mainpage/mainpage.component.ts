import { Component, OnInit } from '@angular/core';
import{ArticlesService} from '../../services/articles.service';
import {Artikal} from '../../models/artikal'
import { PRODUCTS  } from "../../products";
import { Router} from '@angular/router';

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css'],
  providers:[ArticlesService]
})
export class MainpageComponent implements OnInit {

artikli:Array<Artikal>
// a1 = new Artikal(1, "amina", "amina", "amina", 400, 25);

  constructor(private _articlesService: ArticlesService, private router: Router) {
    this.artikli=[];
   }

   onClick (id) {
     this.router.navigate(['/previewArticle', id]);
     console.log("Rutaa",id);
   }

logout()
{
  localStorage.setItem('key', '');
 localStorage.setItem('uloga', '');
 this.router.navigateByUrl('/registracija');
}

  ngOnInit() {
    console.log("spasio",localStorage.getItem('key'));

      console.log("uloga",localStorage.getItem('uloga'));
     console.log("kliknuo",JSON.stringify(PRODUCTS));
     this.artikli = PRODUCTS;
     // this.artikli.push(this.a1);
    this._articlesService.getArticles().subscribe(data=>{
      this.artikli=data;
      console.log("ovi su podaci",this.artikli);
    })
  }

  getArticles(){
    console.log("kliknuo");
    this._articlesService.getArticles().subscribe(data=>{
      this.artikli=data;
      console.log("ovi su podaci",this.artikli);
    })
  }

  openLogin(){
      this.router.navigateByUrl('/registracija');
  }

  openPanel(){
    this.router.navigateByUrl('/dodajsliku');
  }

  openMyProfile()
{
   this.router.navigateByUrl('/updateProfile');
}


}
