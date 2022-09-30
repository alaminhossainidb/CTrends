import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CommonServiceService {

  public data : any;

  public value : any;

  baseUrl : string = "http://localhost:8085"
  constructor(
    public http : HttpClient
  ) { }


  public get(target: string){
    return this.http.get(this.baseUrl+target);
  }


  public post(payload: any, url : string) {
    return this.http.post(this.baseUrl+url, payload);
  }
}
