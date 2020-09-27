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

  ngOnInit() {

    if(localStorage.getItem('uloga')!='admin') this.router.navigateByUrl('/registracija');
  }

  openPanel(){
    this.router.navigateByUrl('/dodajsliku');
  }

  logout()
  {
    localStorage.setItem('key', '');
   localStorage.setItem('uloga', '');
   this.router.navigateByUrl('/');
 }


   homePage()
   {
     this.router.navigateByUrl('/all');
   }


  openMyProfile()
{
   this.router.navigateByUrl('/updateProfile');
}

}
