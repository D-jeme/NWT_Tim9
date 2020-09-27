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
filterArtikli:Array<Artikal>

  constructor(private _articlesService: ArticlesService, private router: Router) {
    this.artikli=[];
    this.filterArtikli=[];
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
     this.filterArtikli = PRODUCTS;
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


getAllArticles() {
  this.filterArtikli =  this.artikli;
}

getLaptops() {
  this.filterArtikli = [] as Artikal[];
  this.artikli.forEach(element => {
    if (element.tip == "laptop") this.filterArtikli.push(element);
  });

}

getMobilePhones() {
  this.filterArtikli = [] as Artikal[];
  this.artikli.forEach(element => {
    if (element.tip == "mobitel") this.filterArtikli.push(element);
  });
}
getCameras() {
  this.filterArtikli = [] as Artikal[];
  this.artikli.forEach(element => {
    if (element.tip == "camera") this.filterArtikli.push(element);
  });
}


}
