import { Injectable, EventEmitter } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';
import { Http, RequestOptionsArgs, Headers } from '@angular/http';
import { SelectCriteria } from '../model/select-criteria';
import {Subject} from 'rxjs';
import { EnrollObject } from '../model/enroll-object';
import { User } from 'src/app/user/model/user';

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
  dropCourseUrl=environment.api+'/RegistrationController/dropCourse';
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
}
