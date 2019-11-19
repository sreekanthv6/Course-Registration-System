import { Injectable, EventEmitter } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';
import { Http, Headers } from '@angular/http';
import { SelectCriteria } from '../model/select-criteria';
import {Subject} from 'rxjs';
import { EnrollObject } from '../model/enroll-object';
import { User } from 'src/app/user/model/user';
import { SwapObject } from '../model/swap-object';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  public submitMessage: EventEmitter<any> = new EventEmitter();
  public coursesList: EventEmitter<any> = new EventEmitter();
  public courseId: string;
  invokeEvent: Subject<any> = new Subject(); 
  getCoursesUrl = environment.api + '/RegistrationController/getCourses';
  findCourseUrl = environment.api + '/RegistrationController/findCourse';
  EnrollCourseUrl = environment.api + '/RegistrationController/enroll';
  fetchEnrolledCoursesUrl= environment.api+ '/RegistrationController/fetchEnrolledCourses';
  fetchNotEnrolledCoursesUrl= environment.api+ '/RegistrationController/fetchNotEnrolledCourses';
  swapUrl =environment.api+'/RegistrationController/swap';
  dropCourseUrl=environment.api+'/RegistrationController/dropCourse';
  fetchAvailableCoursesUrl=environment.api+'/RegistrationController/fetchAvailableCourses';
  constructor(private http: Http,
    private router: Router) {
  }
  getCourses(selectCriteria: SelectCriteria): any {
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
    return this.http.post(this.getCoursesUrl, selectCriteria, httpHeaderOptions);
  }
  fetchAvailableCourses(selectCriteria: SelectCriteria): any {
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
    return this.http.post(this.fetchAvailableCoursesUrl, selectCriteria, httpHeaderOptions);
  }
  public sendSubmitMessage(msg: String) {
    console.log(msg);
    this.submitMessage.next(msg);
  }
  public find(courseId: String): any {
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
    return this.http.post(this.findCourseUrl, courseId, httpHeaderOptions);
  }
  public enroll(enrollObject: EnrollObject): any{
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
    return this.http.post(this.EnrollCourseUrl, enrollObject, httpHeaderOptions);
  }
  public fetchEnrolledCourses(user: User): any{
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
    return this.http.post(this.fetchEnrolledCoursesUrl, user, httpHeaderOptions);
  }
  public fetchNotEnrolledCourses(user: User): any{
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
    return this.http.post(this.fetchNotEnrolledCoursesUrl, user, httpHeaderOptions);
  }
  public dropCourse(enrollObject: EnrollObject): any{
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
    return this.http.post(this.dropCourseUrl, enrollObject, httpHeaderOptions);
  }
  public swap(swapObject: SwapObject): any{
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
    return this.http.post(this.swapUrl, swapObject, httpHeaderOptions);
  }
}
