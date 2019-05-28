import { Routes, RouterModule } from '@angular/router';



import { MainpageComponent } from './components/mainpage/mainpage.component';





const app_routes:Routes = [

  { path:'', component: MainpageComponent },




  { path: '**', pathMatch: 'full', redirectTo: '' }

];



export const AppRouting = RouterModule.forRoot(app_routes);
