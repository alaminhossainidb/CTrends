import { Component, OnInit } from '@angular/core';
import { CommonServiceService } from 'src/app/common-service.service';
import { PresentAbsent } from '../home/home.component';

@Component({
  selector: 'app-attendence-history',
  templateUrl: './attendence-history.component.html',
  styleUrls: ['./attendence-history.component.scss']
})
export class AttendenceHistoryComponent implements OnInit {

  public ps = PresentAbsent;

  todayPresentList : any = [];
  public employeeList : any = []

  list : any = [];
  public type : any = "";

  empId : number = 0;
  fromDate : any;
  toDate : any;

  dynamicTitle : string = "Todays' Attendence List"

  total : number = 0;
  present : number = 0;
  absent : number = 0;
  inTime : number = 0;
  late : number = 0;
  earlyLeave : number = 0;

  constructor(
    private cs : CommonServiceService
  ) { }

  ngOnInit(): void {
    this.loadEmployeeList();
    this.loadTodayPresentList();
  }

  loadEmployeeList(){
    debugger
    this.cs.get("/get/all/employee").subscribe((res : any)=>{
      console.log(res);
      this.employeeList = res;
    })
  }

  loadTodayPresentList(){
    debugger

    this.cs.get("/attnd/get/today/present/list").subscribe((res : any) =>{
      console.log(res)

      this.todayPresentList = res;

      this.todayPresentList.forEach((el : any) =>{
          el.empName = this.employeeList.find((e : any) => e.empId == el.empId).empName;
          el.designation = this.employeeList.find((e : any) => e.empId == el.empId).empDesignation;
      })

      this.list = this.todayPresentList;
      this.statistic();

    })
    
  }

  onChangeType(){
    debugger
    console.log(this.type)

    if (this.type == "ALL") {
      this.loadTodayPresentList();
    } else if(this.type == "LATE"){
      this.todayPresentList = this.list.filter((e : any) => e.entryStatus == this.type);
    }else if (this.type == "IN_TIME") {
      this.todayPresentList = this.list.filter((e : any) => e.entryStatus == this.type && e.leaveStatus == this.type);
    }else if (this.type == "EARLY_LEAVE") {
      this.todayPresentList = this.list.filter((e : any) => e.leaveStatus == this.type);
    }else if (this.type == "PRESENT" || this.type == "ABSENT") {
      this.todayPresentList = this.list.filter((e : any) => e.presentStatus == this.type);
    }
  }

  onEmployeeChange(){
    console.log(this.empId)
  }

  search(){
    console.log(this.empId+" "+this.fromDate+" "+this.toDate)

    if (this.empId > 0 && this.fromDate != null && this.toDate != null) {

      var payload = {
        empId : this.empId,
        fromDate : this.fromDate,
        toDate : this.toDate
      }
      this.cs.post(payload, "/attnd/search/by/employee").subscribe((res : any) =>{
        console.log(res)
        this.todayPresentList = res;
        this.todayPresentList.forEach((el : any) => {
          el.empName = this.employeeList.find((e : any) => e.empId == el.empId).empName;
          el.designation = this.employeeList.find((e : any) => e.empId == el.empId).empDesignation;
        });
        this.list = this.todayPresentList;
        this.dynamicTitle = this.employeeList.find((e : any) => e.empId == this.empId).empName;
        this.statistic();
      })

    }else if(this.empId < 1 && this.fromDate != null && this.toDate != null){
      
      var payload2 = {
        fromDate : this.fromDate,
        toDate : this.toDate
      }
      this.cs.post(payload2, "/attnd/search/by/between/date").subscribe((res : any) =>{
        console.log(res)
        this.todayPresentList = res;
        this.todayPresentList.forEach((el : any) => {
          el.empName = this.employeeList.find((e : any) => e.empId == el.empId).empName;
          el.designation = this.employeeList.find((e : any) => e.empId == el.empId).empDesignation;
        });
        this.list = this.todayPresentList;
        this.dynamicTitle = "Attendence From "+" '"+this.fromDate+"'"+" To "+" '"+this.toDate+"'";
        this.statistic();
      })

    }
  }

  statistic(){
    console.log(this.list);
    this.total = this.list.length;
    this.present = this.list.filter((e : any) => e.presentStatus == "PRESENT").length;
    this.absent = this.list.filter((e : any) => e.presentStatus == "ABSENT").length;
    this.inTime = this.list.filter((e : any) => e.entryStatus == "IN_TIME" && e.leaveStatus == "IN_TIME").length;
    this.late = this.list.filter((e : any) => e.entryStatus == "LATE").length;
    this.earlyLeave = this.list.filter((e : any) => e.leaveStatus == "EARLY_LEAVE").length;
  }
}
