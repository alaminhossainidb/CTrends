import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonServiceService } from 'src/app/common-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.scss']
})
export class EmployeeListComponent implements OnInit {

  public desigList = [
    {
      value : "Manager"
    },
    {
      value : "HR"
    },
    {
      value : "Senior Programmer"
    },
    {
      value : "Programmer"
    },
    {
      value : "Junior Programmer"
    }
  ];

  public empId : number = 0;
  public empName : string = "";
  public empDOB : any;
  public empMobile : string = "";
  public empGender : string = "";
  public designation : String = "";

  public employeeList : any = [];

  constructor(
    public cs : CommonServiceService,
    public router : Router,) { }

  ngOnInit(): void {
    this.loadEmployeeList();
  }

  displayStyle : any = "none";
  openPopup(){
    this.displayStyle = 'block'
  }

  closePopup(){
    this.empName = "";
    this.empDOB = null;
    this.empGender = "";
    this.empMobile = "";
    this.designation = "";

    this.displayStyle = "none"
  }

  onChangeType(gndr : Event){
    debugger
    var value = (gndr.target as HTMLTextAreaElement).value
    //this.empGender = value;
  }

  onChangeDesignationType(desig : Event){
    debugger
    var value = (desig.target as HTMLTextAreaElement).value
    //this.designation = value;
  }

  loadEmployeeList(){
    debugger
    this.cs.get("/get/all/employee").subscribe((res : any)=>{
      console.log(res);
      this.employeeList = res;
    })
  }

  onUpdateOrAddEmployee(){
    debugger
    var payload = {
      empId : this.empId ? this.empId : 0,
      empName : this.empName,
      empDOB : this.empDOB,
      empGender : this.empGender,
      empMobileNo : this.empMobile,
      empDesignation : this.designation,
      isActive : 1
    }
    console.log(payload);

    this.cs.post(payload, "/insert/update/employee").subscribe((res : any) =>{
      console.log(res)
      if (res) {
        Swal.fire({
          title: "Employee successfully Saved",
          toast: true,
          timer: 2000 // mili sec
        });
        this.closePopup();
        this.loadEmployeeList();
      }
    })
  }

  onEditClick(data : any){
    debugger
    console.log(data)
    this.empId = data.empId;
    this.empName = data.empName;
    this.empDOB = formatDate(data.empDOB, 'yyyy-MM-dd' ,'en'); ;
    this.empGender = data.empGender;
    this.empMobile = data.empMobileNo;
    this.designation = data.empDesignation;

    this.displayStyle = "block"
  }

  onDeleteClick(data : any){
    debugger

    Swal.fire({
      title: "Are you sure?",
      text: "Once deleted, you will not be able to recover it.!",
      icon: "warning",
      showCancelButton : true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    })
    .then((willDelete) => {
      if (willDelete.value) {
        console.log("Delete"+ willDelete.value);
        
        debugger
        var payload = {
          empId : data.empId
         }
    
         this.cs.post(payload, "/delete/employee").subscribe((res : any)=>{
          console.log(res)
          if(res){
            this.loadEmployeeList();
          }
         })
      }
    });
     
  }

}
