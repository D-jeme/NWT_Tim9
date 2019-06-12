import { BrowserModule } from '@angular/platform-browser';

import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';

import { HttpModule } from '@angular/http';

import { AppRouting } from './routes';


import { FileUploadModule } from 'ng2-file-upload';
import { Ng2CloudinaryModule } from 'ng2-cloudinary';
import {PopupModule} from 'ng2-opd-popup';
import { AngularDateTimePickerModule } from 'angular2-datetimepicker';




import { AppComponent } from './app.component';


import { MainpictureComponent } from './components/mainpicture/mainpicture.component';


import { DodajslikuComponent } from './components/dodajsliku/dodajsliku.component';
import { MainpageComponent } from './components/mainpage/mainpage.component';
import { RegistracijaComponent } from './components/registracija/registracija.component';
import { PreviewArticleComponent } from './components/previewArticle/previewArticle.component';
import { AdminArticlesComponent } from './components/aarticles/aarticles.component';
import { AdminPreviewArticleComponent } from './components/apreviewArticle/apreviewArticle.component';
import { UpdateProfileComponent } from './components/updateProfile/updateProfile.component';





@NgModule({

  declarations: [

    AppComponent,
DodajslikuComponent,
    MainpictureComponent,
AdminArticlesComponent,
    MainpageComponent,
    RegistracijaComponent,
    PreviewArticleComponent,
    AdminPreviewArticleComponent,
    UpdateProfileComponent

],

  imports: [
            Ng2CloudinaryModule,
      FileUploadModule,

    BrowserModule,

    FormsModule,

    HttpModule,
    AngularDateTimePickerModule,

    AppRouting,
        PopupModule.forRoot()

  ],

  providers: [],

  bootstrap: [AppComponent]

})

export class AppModule { }
