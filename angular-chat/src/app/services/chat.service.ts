import { Injectable, ElementRef, QueryList } from '@angular/core';
import { Message } from '../entities/Message';
import SockJS from 'sockjs-client';
import { Stomp } from "../stomp/stomp.min";
import { NewMessageService } from "./new-message.service";


@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private stompClient = null;

  constructor(private newMessageService: NewMessageService) {}

  init(roomId: number, login: string):void {
    var socket = new SockJS('http://localhost:8080/chat/meeting/?roomId=' + roomId);
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, (msg)=> {
      console.log('Connected: ' + msg);
      this.stompClient
      .subscribe('/topic/room?roomId=' + roomId, (message)=> {
         let newMessage: Message = JSON.parse(message.body);
         if(login === newMessage.author) {
          this.newMessageService.addNewMessage(newMessage, 'current-user-message');
         } else {
          this.newMessageService.addNewMessage(newMessage, 'message-class');
         }
      });
    });
  }

  sendMessage(message:Message, roomId: number):void {
      this.stompClient.send("/app/meeting/?roomId=" + roomId, {}, JSON.stringify(message));
  }

  disconnect():void {
    if(this.stompClient !== null ) {
      this.stompClient.disconnect();
    }
  }

}
