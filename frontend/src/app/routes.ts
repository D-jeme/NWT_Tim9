import { Routes, RouterModule } from '@angular/router';



import { MainpageComponent } from './components/mainpage/mainpage.component';
import { RegistracijaComponent } from './components/registracija/registracija.component';





const app_routes:Routes = [

  { path:'', component: MainpageComponent },
    { path:'registracija', component: RegistracijaComponent},




  { path: '**', pathMatch: 'full', redirectTo: '' }

];



export const AppRouting = RouterModule.forRoot(app_routes);
