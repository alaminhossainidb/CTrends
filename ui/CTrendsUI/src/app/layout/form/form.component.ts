import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonServiceService } from 'src/app/common-service.service';

 enum DesignationList {

  MANAGER = "MANAGER",
  HR = "HR",
  SENIOR_PROGRAMMER = "SENIOR_PROGRAMMER",
  PROGRAMMER = "PROGRAMMER",
  JUNIOR_PROGRAMMER  = "JUNIOR_PROGRAMMER"
}

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {

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

  constructor(
    public cs : CommonServiceService,
    public router : Router,
    public datePipe : DatePipe
  ) { }

  ngOnInit(): void {

  }



  onSubmit(){
  debugger
 
  }

  onChangeType(rt : Event){
    
    var value = (rt.target as HTMLTextAreaElement).value
  }


}
