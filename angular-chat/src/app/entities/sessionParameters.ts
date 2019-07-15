import { Room } from './Room';

export class SessionParam {
    exist: boolean;
    admin: boolean;
    rooms: Room[];
    userId:number;
}