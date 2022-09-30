import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AttendenceHistoryComponent } from './attendence-history/attendence-history.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { FormComponent } from './form/form.component';
import { HomeComponent } from './home/home.component';
import { LayoutComponent } from './layout.component';

const routes: Routes = [
    {
      path : "",
      component : LayoutComponent,
      children : [
        {
          path : "",
          component : HomeComponent
        },
        {
          path : "home",
          component : HomeComponent
        },
        {
          path : "form",
          component : FormComponent
        },
        {
          path : "emp/list",
          component : EmployeeListComponent
        },
        {
          path : "attendence/history",
          component : AttendenceHistoryComponent
        }
      ]
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LayoutRoutingModule { }
