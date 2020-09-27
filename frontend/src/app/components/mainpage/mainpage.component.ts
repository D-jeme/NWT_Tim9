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

  constructor(private _articlesService: ArticlesService, private router: Router) {
    this.artikli=[];
   }

   onClick (id) {
     this.router.navigate(['/previewArticle', id]);
   }

logout()
{
  localStorage.setItem('key', '');
 localStorage.setItem('uloga', '');
 this.router.navigateByUrl('/');
}

  ngOnInit() {
     this.artikli = PRODUCTS;
  }

  getArticles(){
  }

  openLogin(){
      this.router.navigateByUrl('/');
  }

  openPanel(){
    this.router.navigateByUrl('/dodajsliku');
  }

  openMyProfile()
{
   this.router.navigateByUrl('/updateProfile');
}

  cartPreview() {
     this.router.navigateByUrl('/mainpicture');
  }


}
