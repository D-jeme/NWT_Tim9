import { Component, OnInit } from '@angular/core';
import{ArticlesService} from '../../services/articles.service';
import { Router} from '@angular/router';

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css'],
  providers:[ArticlesService]
})
export class MainpageComponent implements OnInit {

artikli:Array<any>

  constructor(private _articlesService: ArticlesService, private router: Router) {
    this.artikli=[];
   }

   onClick (id) {
 this.router.navigate(['/previewArticle', id]);
 console.log("Rutaa",id);

}

  ngOnInit() {  console.log("kliknuo");
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

}
