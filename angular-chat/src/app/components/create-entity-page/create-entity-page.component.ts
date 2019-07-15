import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { User } from 'src/app/entities/User';
import { HttpService } from '../../services/http.service';
import { CurrentSessionService } from "../../services/currentSession.service";
import { Room } from 'src/app/entities/Room';
import { EventListenerService } from "../../services/event-listener.service";
import { takeUntil } from 'rxjs/operators';

class Field {
  name: string;
  value: string;
}

@Component({
  selector: 'app-create-entity-page',
  templateUrl: './create-entity-page.component.html',
  styleUrls: ['./create-entity-page.component.css']
})
export class CreateEntityPageComponent implements OnInit {
  typeEntity: string;
  isError : boolean = false;
  fields: Field[];
  constructor(private http: HttpService, 
    private currentSessionService: CurrentSessionService,
    private eventListenerService: EventListenerService,
    private router: Router) { 
      this.typeEntity = this.currentSessionService.getTypeEntity();
      if(this.typeEntity === "user") {
        this.fields =[{'name': 'login', 'value':''}, {'name': 'password', 'value':''}, {'name': 'password again', 'value':''}];
      } else if(this.typeEntity ==="room") {
        this.fields=[{'name': 'room id', 'value':''}];
      }
    }

  ngOnInit() {
  }

  save() {
    var condition: boolean =  this.fields.every((element: Field) => {
      return element.value.length != 0;
    });

    if(condition) {
      if(this.typeEntity === "user") {
        this.createUser();
      } else if(this.typeEntity ==="room") {
        this.createRoom();
      }
    }
   }

   createUser():void {
    if(this.fields[1].value === this.fields[2].value) {
      let user: User = new User(this.fields[0].value,  'offline', this.fields[1].value);
      this.http.addNewUser(user, this.currentSessionService.getSelectedRoom().id +"")
      .pipe(takeUntil(this.eventListenerService.destroySubject))
      .subscribe((data)=>{
        if(data.text === "success") {
          this.router.navigate(['/chat']);
        } else if(data.text === "error") {
            this.isError = true;
        }
      });
    }
   }

   createRoom(): void {
      let room:Room = new Room(+this.fields[0].value);
      this.http.addNewRoom(this.currentSessionService.getUserId(), room)
      .pipe(takeUntil(this.eventListenerService.destroySubject))
      .subscribe((data)=> {
        if(data.text === "success") {
          let rooms: Room[] = this.currentSessionService.getRooms();
          rooms.push(room);
          this.currentSessionService.setRooms(rooms);
          this.router.navigate(['/chat']);
        } else if(data.text === "error") {
          this.isError = true;
        }
      });
   }

   ngOnDestroy() {
    this.eventListenerService.destroySubject.next();
  }
}
