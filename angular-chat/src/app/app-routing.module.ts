import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginPageComponent} from './components/login-page/login-page.component';
import {ChatPageComponent} from './components/chat-page/chat-page.component';
import {CreateEntityPageComponent} from './components/create-entity-page/create-entity-page.component';


const routes: Routes = [
  {path:'', redirectTo:'/login', pathMatch: 'full'},
  {path: 'login', component: LoginPageComponent},
  {path: 'chat', component: ChatPageComponent},
  {path: 'create', component: CreateEntityPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
