import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, from } from 'rxjs';
import { SessionParam } from '../entities/sessionParameters';
import { User } from '../entities/User';
import { StringMap } from '@angular/compiler/src/compiler_facade_interface';
import { Room } from '../entities/Room';

const httpOptions = {
  headers: new HttpHeaders({ 
  'Content-Type': 'application/json',
  'Access-Control-Allow-Origin': '*' ,
  'Access-Control-Allow-Methods': 'GET, POST, OPTIONS, PUT, PATCH, DELETE',
  'Access-Control-Allow-Headers': 'X-Requested-With,content-type',
  'Access-Control-Allow-Credentials': 'true'
 })
};


@Injectable({
  providedIn: 'root'
})
export class HttpService {

  private chatUrl = "http://localhost:8080/chat";

  constructor(private http: HttpClient) {}

  getParamSession(login:string, password:string): Observable<SessionParam> {
    return this.http.get<SessionParam>(`${this.chatUrl}/users/validate?login=${login}&password=${password}`, httpOptions);
  }

  getUsers(roomId:number):Observable<User[]> {
    return this.http.get<User[]>(`${this.chatUrl}/users/list?roomId=${roomId}`, httpOptions);
  }

  addNewUser(user: User, roomId: string):Observable<StringMap> {
    return this.http.post<StringMap>(`${this.chatUrl}/users/add?roomId=${roomId}`, JSON.stringify(user), httpOptions);
  }

  addNewRoom(userId:number, room:Room):Observable<StringMap> {
    return this.http.post<StringMap>(`${this.chatUrl}/rooms/add?userId=${userId}`, JSON.stringify(room), httpOptions);
  }
}
