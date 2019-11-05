import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { User } from 'src/app/user/model/user';
import { Payment } from '../model/payment';

@Injectable({
  providedIn: 'root'
})
export class FinanceService {
historyUrl=environment.api + '/RegistrationController/pastPayments';
duesUrl=environment.api + '/RegistrationController/viewDues';
paymentUrl=environment.api + '/RegistrationController/postPayment';
  constructor(private http: Http,
    private router: Router) { }
    public pastPayments(user: User): any{
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
      return this.http.post(this.historyUrl, user, httpHeaderOptions);
    }
    public viewDues(user: User): any{
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
      return this.http.post(this.duesUrl, user, httpHeaderOptions);
    }
    public postPayment(payment: Payment): any{
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
      return this.http.post(this.paymentUrl, payment, httpHeaderOptions);
    }
}
