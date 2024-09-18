import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './auth/components/signup/signup.component';
import { LoginComponent } from './auth/components/login/login.component';
import { PostCarComponent } from './modules/admin/components/post-car/post-car.component';

const routes: Routes = [
  { path: "register", component: SignupComponent },
  { path: "login", component: LoginComponent},
  { path: "admin", loadChildren: () => import("./modules/admin/admin.module").then(m => m.AdminModule) },
  { path: "customer", loadChildren: () => import("./modules/customer/customer.module").then(m => m.CustomerModule) },
  { path: "car" ,component: PostCarComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
