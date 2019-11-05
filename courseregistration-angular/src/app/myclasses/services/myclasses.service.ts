import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { User } from 'src/app/user/model/user';

@Injectable({
  providedIn: 'root'
})
export class MyclassesService {
viewGradesUrl=environment.api + '/RegistrationController/viewGrades';
  constructor(private http: Http,
    private router: Router) { }
    public viewGrades(user: User): any{
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
      return this.http.post(this.viewGradesUrl, user, httpHeaderOptions);
    }
}
