import { Injectable } from '@angular/core';
import { Room } from '../entities/Room';
import { Message } from '../entities/Message';

class messageStorage {
    roomId: number;
    messages: Message[];
}

@Injectable({
  providedIn: 'root'
})
export class CurrentSessionService {
  private userLogin:string;
  private userId: number;
  private isAdmin:boolean;
  private rooms: Room[];
  private selectedRoom: Room;
  private typeEntity: string;
  private messageStorage: messageStorage[];


  constructor() {}

  getUserLogin(): string {
    return this.userLogin;
  }

  setUserLogin(login:string):void {
     this.userLogin = login;
  }

  getUserId(): number {
    return this.userId;
  }

  setUserId(id:number) {
    this.userId = id;
  }

  getSelectedRoom() {
    return this.selectedRoom;
  }

  setSelectedRoom(room: Room) {
    this.selectedRoom = room;
  }

  isUserAdmin():boolean {
    return this.isAdmin;
  }

  setIsAdmin(isAdmin: boolean) {
    this.isAdmin = isAdmin;
  }

  getTypeEntity(): string {
      return this.typeEntity;
  }

  setTypeEntity(type: string): void {
      this.typeEntity = type;
  }

  getRooms() {
    return this.rooms;
  }

  setRooms(rooms:Room[]) {
    this.rooms = rooms
  }

}
