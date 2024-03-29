import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import {  AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  
  { path: '', redirectTo: 'login', pathMatch: 'full' },  
  { path: 'register', loadChildren: () => import('./auth/register/register.module').then(m => m.RegisterPageModule) },
  { path: 'login', loadChildren: () => import('./auth/login/login.module').then(m => m.LoginPageModule) },
  { path: 'resetpassword', loadChildren: () => import('./auth/resetpassword/resetpassword.module').then( m => m.ResetpasswordPageModule)},
  { path: 'changepassword/:token', loadChildren: () => import('./auth/changepassword/changepassword.module').then( m => m.ChangepasswordPageModule)},
  { path: 'home', canActivate: [AuthGuard], loadChildren: () => import('./home/home.module').then(m => m.HomePageModule) },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
