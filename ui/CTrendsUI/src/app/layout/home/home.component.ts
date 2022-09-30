import { Component, OnInit } from '@angular/core';
import { CommonServiceService } from 'src/app/common-service.service';

export enum PresentAbsent{
  PRESENT = "PRESENT",
	ABSENT = "ABSENT"
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public ps = PresentAbsent;
  public employeeList : any = []

  public todayPresent : any = [];

  constructor(
    private cs : CommonServiceService
  ) { }

  ngOnInit(): void {
    this.loadEmployeeList();
    this.loadTodayPresent();
  }

  loadEmployeeList(){
    debugger
    this.cs.get("/get/all/employee").subscribe((res : any)=>{
      console.log(res);
      this.employeeList = res;
    })
  }

  loadTodayPresent(){
debugger
    this.cs.get("/attnd/get/today/present").subscribe((res : any) =>{
      console.log(res);
      if (res) {
        this.todayPresent = res;

        this.todayPresent.forEach((el : any) => {
          el.empName = this.employeeList.find((e : any) => e.empId == el.empId).empName;
          el.designation = this.employeeList.find((e : any) => e.empId == el.empId).empDesignation;
        });
      }
      console.log(this.todayPresent)
    })
  }

  btnPresentAbsent(data : any , status : any){
    debugger
    console.log(status);

    var payload = {
      empId : data.empId,
      presentStatus : status
    }

    
      this.cs.post(payload, "/attnd/create/attendence").subscribe((res : any) =>{
        console.log(res);
        if (res) {
          this.employeeList = this.employeeList.filter((e : any) => e.empId != res.empId);
          this.loadTodayPresent();
        }
      });

    

    
  }

  btnLeave(data : any){
    debugger
    console.log(data)

    var payload = {
      attndId : data.attndId
    }

    if (data.attndId > 0) {
      this.cs.post(payload, "/attnd/create/leave").subscribe((res : any) =>{
        console.log(res)
        if(res){
          this.todayPresent = this.todayPresent.filter((e : any) => e.attndId != data.attndId);
        }
      })
    }

  }
}
