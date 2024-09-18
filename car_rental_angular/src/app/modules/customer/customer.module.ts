import { NgModule } from '@angular/core';
import { CustomerDashboardComponent } from './components/customer-dashboard/customer-dashboard.component';
import { CustomerRoutingModule } from './customer-routing.module';

@NgModule({
    declarations: [
        CustomerDashboardComponent,
      ],
    imports: [
      CustomerRoutingModule
    ],
    providers: []
    
  })
  export class CustomerModule { }