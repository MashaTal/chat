import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, from } from 'rxjs';
import { SessionParam } from './sessionParameters';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  private heroesUrl = "";

  constructor( private http: HttpClient) { 
  }

  getParamSession(): Observable<SessionParam> {
    return this.http.get<SessionParam>(this.heroesUrl);
  }

}
