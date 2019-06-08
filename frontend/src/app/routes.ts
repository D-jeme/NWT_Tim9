import { Routes, RouterModule } from '@angular/router';



import { DodajslikuComponent } from './components/dodajsliku/dodajsliku.component';
import { MainpageComponent } from './components/mainpage/mainpage.component';
import { RegistracijaComponent } from './components/registracija/registracija.component';
import {PreviewArticleComponent} from './components/previewArticle/previewArticle.component';





const app_routes:Routes = [

  { path:'', component: MainpageComponent },
    { path:'registracija', component: RegistracijaComponent},
    {path:'dodajsliku',component:DodajslikuComponent},
        {path:'previewArticle',component:PreviewArticleComponent},
          { path:'previewArticle/:id', component: PreviewArticleComponent },




  { path: '**', pathMatch: 'full', redirectTo: '' }

];



export const AppRouting = RouterModule.forRoot(app_routes);
