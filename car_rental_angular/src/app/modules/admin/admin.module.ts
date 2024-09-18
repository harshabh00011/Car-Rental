import { NgModule } from '@angular/core';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AdminRoutingModule } from './admin-routing.module';
import { PostCarComponent } from './components/post-car/post-car.component';
import { CommonModule } from '@angular/common';
import { NgZorroImportsModule } from 'src/app/NgZorroImportsModule';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';


@NgModule({
    declarations: [
        AdminDashboardComponent,
        PostCarComponent,
      ],
    imports: [
      CommonModule,
      AdminRoutingModule,
      NgZorroImportsModule,
      ReactiveFormsModule,
      FormsModule
    ],
    providers: []
    
  })
  export class AdminModule { }