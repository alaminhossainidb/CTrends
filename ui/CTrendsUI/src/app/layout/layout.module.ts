import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';

import { LayoutRoutingModule } from './layout-routing.module';
import { NavbarComponent } from './navbar/navbar.component';
import { LayoutComponent } from './layout.component';
import { HomeComponent } from './home/home.component';
import { FormComponent } from './form/form.component';
import { FormsModule } from '@angular/forms';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { AttendenceHistoryComponent } from './attendence-history/attendence-history.component';


@NgModule({
  declarations: [
    LayoutComponent,
    NavbarComponent,
    HomeComponent,
    FormComponent,
    EmployeeListComponent,
    AttendenceHistoryComponent
  ],
  imports: [
    CommonModule,
    LayoutRoutingModule,
    FormsModule
  ],
  providers : [DatePipe]
})
export class LayoutModule { }
