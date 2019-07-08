import { Injectable } from '@angular/core';
import { SessionParam } from './sessionParameters';

@Injectable({
  providedIn: 'root'
})
export class ResponceProcessingService {

  constructor() { }

  isUserExist(sessionParam : SessionParam): boolean {
    return sessionParam.exist;
  }

  isUserAdmin(sessionParam : SessionParam): boolean {
    return sessionParam.admin;
  }
}
