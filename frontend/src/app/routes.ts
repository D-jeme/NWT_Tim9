import { Routes, RouterModule } from '@angular/router';



import { DodajslikuComponent } from './components/dodajsliku/dodajsliku.component';
import { MainpageComponent } from './components/mainpage/mainpage.component';
import { RegistracijaComponent } from './components/registracija/registracija.component';
import {PreviewArticleComponent} from './components/previewArticle/previewArticle.component';
import {AdminPreviewArticleComponent} from './components/apreviewArticle/apreviewArticle.component';
import { AdminArticlesComponent } from './components/aarticles/aarticles.component';
import { UpdateProfileComponent } from './components/updateProfile/updateProfile.component';
import { MainpictureComponent } from './components/mainpicture/mainpicture.component';






const app_routes:Routes = [

  { path:'all', component: MainpageComponent },
  { path:'aarticles', component: AdminArticlesComponent},
    { path:'', component: RegistracijaComponent},
    {path:'dodajsliku',component:DodajslikuComponent},
        {path:'previewArticle',component:PreviewArticleComponent},
          { path:'previewArticle/:id', component: PreviewArticleComponent },
          {path:'apreviewArticle',component:AdminPreviewArticleComponent},
            { path:'apreviewArticle/:id', component: AdminPreviewArticleComponent },
            {path:'updateProfile',component:UpdateProfileComponent},
            { path:'updateProfile/:id', component: UpdateProfileComponent },
            { path:'mainpicture', component: MainpictureComponent },



  { path: '**', pathMatch: 'full', redirectTo: '' }

];



export const AppRouting = RouterModule.forRoot(app_routes);
