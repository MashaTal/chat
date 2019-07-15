export class User {
    id:string;
    login:string;
    state:string;
    password:string;

    constructor(login:string, state:string, password:string) {
        this.login = login;
        this.state = state;
        this.password = password;
    }
}