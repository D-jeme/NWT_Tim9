import { Component, OnInit, OnDestroy } from '@angular/core';
import { UserService } from '../../services/users.service';
import { Artikal } from '../../models/artikal';
import { ActivatedRoute } from '@angular/router'
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
        proizvod:String='';


constructor(private _userService: UserService, private route: ActivatedRoute) {

  this.korisnici=[];

 }


  ngOnInit() {


this._userService.getUser(+localStorage.getItem('key')).subscribe(
  data=>{
    console.log("ovo je",data);

    this.ime=data.ime;
    this.prezime=data.prezime;
    this.email=data.email;
  }

)


  }

  updateUser(){

    if(this.ime=='' || this.prezime=='')
    {
      this.errorMessage='Molimo popunite sva polja!';
      this.messageUspjesno='';
      return;
    }

    this._userService.updateUser(+localStorage.getItem('key'), this.ime,this.prezime,this.email).subscribe(data=>{console.log("vraceno",data); this.proizvod=data.MESSAGE;})
  }

  ngOnDestroy(){

}



}
