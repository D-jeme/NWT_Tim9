import { Component, OnInit } from '@angular/core';
import{ArticlesService} from '../../services/articles.service';
import { Router} from '@angular/router';

@Component({
  selector: 'app-mainpage',
  templateUrl: './aarticles.component.html',
  styleUrls: ['./aarticles.component.css'],
  providers:[ArticlesService]
})
export class AdminArticlesComponent implements OnInit {

artikli:Array<any>

  constructor(private _articlesService: ArticlesService, private router: Router) {
    this.artikli=[];
   }

   onClick (id) {
 this.router.navigate(['/apreviewArticle', id]);
 console.log("Rutaa",id);

}
logout()
{
  localStorage.setItem('key', '');
 localStorage.setItem('uloga', '');
 this.router.navigateByUrl('/registracija');
}
  ngOnInit() {

    if(localStorage.getItem('uloga')!='admin') this.router.navigateByUrl('/registracija');
    console.log("spasio",localStorage.getItem('key'));

      console.log("uloga",localStorage.getItem('uloga'));
    console.log("kliknuo");
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
