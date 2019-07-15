import { Injectable } from '@angular/core';
import * as Rx from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventListenerService {
  destroySubject;
  constructor() { 
    this.destroySubject = new Rx.Subject();
  }
}
