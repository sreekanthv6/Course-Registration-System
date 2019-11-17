import { Injectable, EventEmitter } from '@angular/core';
import { Http, RequestOptionsArgs, Headers } from '@angular/http';
import { User } from '../model/user';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  public submitMessage: EventEmitter<any> = new EventEmitter();
  registrationUrl = environment.api + '/RegistrationController/signup';
  loginUrl = environment.api + '/RegistrationController/login';
  fetchDeptsUrl = environment.api + '/RegistrationController/fetchDeptNames';
  resetPasswordUrl=environment.api+'/RegistrationController/resetPassword';
  user: User;
  constructor(private http: Http,
    private router: Router) {
  }
  setSession(key: string, value: User): void {
    sessionStorage.setItem(key, JSON.stringify(value));
   
    
}
getSession(key: string): any {
  if (typeof window !== 'undefined') {
      //let retrievedObject = sessionStorage.getItem(key) as string;
      let retrievedObject = JSON.parse(sessionStorage.getItem(key)) as any;
      console.log(JSON.stringify(retrievedObject)); 
      return retrievedObject;
  }
}
clearSession(): void {
  localStorage.clear();
}
  signup(user: User): any {
    const httpHeaderOptions =
    {
      headers: new Headers({
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT, DELETE',
        'Access-Control-Allow-Headers': 'Origin, Content-Type, Accept, Authorization, X- Request-With'
      })
    };
    return this.http.post(this.registrationUrl, user, httpHeaderOptions);
  }
  login(user: User): any {
    const httpHeaderOptions =
    {
      headers: new Headers({
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT, DELETE',
        'Access-Control-Allow-Headers': 'Origin, Content-Type, Accept, Authorization, X- Request-With'
      })
    };
    return this.http.post(this.loginUrl, user, httpHeaderOptions);
  }
  resetPassword(user: User): any {
    const httpHeaderOptions =
    {
      headers: new Headers({
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT, DELETE',
        'Access-Control-Allow-Headers': 'Origin, Content-Type, Accept, Authorization, X- Request-With'
      })
    };
    return this.http.post(this.resetPasswordUrl, user, httpHeaderOptions);
  }
  fetchAllDepartments(): any {
    return this.http.get(this.fetchDeptsUrl);
  }
  public sendSubmitMessage(msg: String) {
    console.log(msg);
    this.submitMessage.next(msg);

  }
}
