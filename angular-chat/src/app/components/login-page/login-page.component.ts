import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../services/http.service';
import { Router } from "@angular/router";
import { CurrentSessionService } from "../../services/currentSession.service";
import { EventListenerService } from "../../services/event-listener.service";
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  login: string = "";
  password: string = "";
  isError:boolean = false;

  constructor( private httpService: HttpService,
     private currentSessionService: CurrentSessionService,
     private eventListenerService: EventListenerService,
     private router: Router) { }

  ngOnInit() {
  }
  
  join() {
    if(this.login.length != 0 && this.password.length !=0) {
      this.httpService.getParamSession(this.login,this.password)
      .pipe(takeUntil(this.eventListenerService.destroySubject))
      .subscribe((data)=> {
        if(data.exist) {
            this.router.navigate(['/chat']);
            this.currentSessionService.setUserLogin(this.login);
            this.currentSessionService.setIsAdmin(data.admin);
            this.currentSessionService.setRooms(data.rooms);
            this.currentSessionService.setUserId(data.userId);
        } else if(!data.exist) {
          this.isError = true;
        }
      });     
    }
  }

  ngOnDestroy() {
    this.eventListenerService.destroySubject.next();
  }
}
