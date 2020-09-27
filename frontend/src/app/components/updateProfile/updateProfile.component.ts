import { Component, OnInit, OnDestroy } from '@angular/core';
import { UserService } from '../../services/users.service';
import { Artikal } from '../../models/artikal';
import { ActivatedRoute } from '@angular/router';

import { Router} from '@angular/router';
@Component({
  templateUrl: './updateProfile.component.html',
  styleUrls: ['./updateProfile.component.css'],
  providers: [UserService],
})
export class UpdateProfileComponent implements OnInit, OnDestroy {
  korisnici:Array<any>;
    Artikal:any;
    id: Number;
    ime: String='';
    artikli: Artikal;
    private sub:any;
    prezime: String='';
    email: String='';
    errorMessage: String='';
    messageUspjesno: String='';
    proizvod: String='';


constructor(private _userService: UserService, private route: ActivatedRoute,private router:Router) {
  this.korisnici=[];
 }

  ngOnInit() {
  }

  logout()
  {
    localStorage.setItem('key', '');
    localStorage.setItem('uloga', '');
    this.router.navigateByUrl('/');
  }

  openMyProfile()
  {
     this.router.navigateByUrl('/updateProfile');
  }

  homePAge()
  {
    this.router.navigateByUrl('/all');
  }

  updateUser(){
    if(this.ime=='' || this.prezime=='')
    {
      this.errorMessage='Molimo popunite sva polja!';
      this.messageUspjesno='';
      return;
    }
  }

  ngOnDestroy(){
  }

}
