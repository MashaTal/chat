import { Component, OnInit } from '@angular/core';
import {HttpService} from '../http.service';
import {ResponceProcessingService} from '../responce-processing.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  login: string = "";
  password: string = "";

  constructor( private httpService: HttpService, private responceProcessingService: ResponceProcessingService) { }

  ngOnInit() {
  }
  
  join() {
    if(this.login.length != 0 && this.password.length !=0) {
      //  this.httpService.getParamSession().subscribe((data)=> {
      //     this.responceProcessingService.isUserExist(data);
      //     this.responceProcessingService.isUserAdmin(data);
      //  });
      
    }
  }
}
