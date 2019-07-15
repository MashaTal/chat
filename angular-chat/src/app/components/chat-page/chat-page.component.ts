import { Component, OnInit, ViewChildren, ElementRef, QueryList} from '@angular/core';
import { CurrentSessionService } from "../../services/currentSession.service";
import { Room } from '../../entities/Room';
import { ChatService } from 'src/app/services/chat.service';
import { Message } from '../../entities/Message';
import { Router } from "@angular/router";
import { HttpService } from '../../services/http.service';
import { User } from 'src/app/entities/User';
import {EventListenerService } from "../../services/event-listener.service";
import { takeUntil } from 'rxjs/operators';


@Component({
  selector: 'app-chat-page',
  templateUrl: './chat-page.component.html',
  styleUrls: ['./chat-page.component.css']
})
export class ChatPageComponent implements OnInit {

  login:string = "";
  isAdmin:boolean;
  rooms: Room[];
  users: User[];
  selectedRoom: Room;
  selectedField: string;
  textMessage:string;

  constructor(private currentSessionService: CurrentSessionService,
    private chatService: ChatService,
    private http: HttpService,
    private eventListenerService: EventListenerService,
    private router: Router) { 
        this.init();
  }

  ngOnInit() {}

  init():void {
    this.selectedField = 'room';
    this.login = this.currentSessionService.getUserLogin();
    this.isAdmin = this.currentSessionService.isUserAdmin();
    this.rooms = this.currentSessionService.getRooms();
    if(this.rooms.length!= 0) {
      this.currentSessionService.setSelectedRoom(this.rooms[0]);
      this.selectedRoom = this.currentSessionService.getSelectedRoom();
      this.chatService.init(this.selectedRoom.id, this.login);
    }
  }

  selectField(field:string):void {
    if(this.selectedField != field) {
      this.selectedField = field;
    }  
    if(field === 'user') {
      this.getUsers(this.currentSessionService.getSelectedRoom().id);
    }
  }

  sendMessage():void {
    let message: Message = { 
    'author': this.login,
    'message': this.textMessage
    };
    this.textMessage = "";
    this.chatService.sendMessage(message, this.selectedRoom.id);
  }

  createNewEntity(entity: string):void {
      this.currentSessionService.setTypeEntity(entity);
      this.router.navigate(['/create']);
  }

  getUsers(roomId: number):void {
      this.http.getUsers(roomId)
      .pipe(takeUntil(this.eventListenerService.destroySubject))
      .subscribe((data:User[])=>{
         this.users = data;
      });
  }

  selectRoom(room: Room):void {
    this.chatService.disconnect();
    this.currentSessionService.setSelectedRoom(room);
    this.selectedRoom = this.currentSessionService.getSelectedRoom();

    this.chatService.init(this.selectedRoom.id, this.login);
  }

  ngOnDestroy():void {
    this.chatService.disconnect();
    this.eventListenerService.destroySubject.next();
  }

}
