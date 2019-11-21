import { Injectable } from '@angular/core';
import { Course } from 'src/app/registration/model/course';
import { Http, Headers } from '@angular/http';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  addCourseUrl = environment.api + '/RegistrationController/addCourse';
  deleteCourseUrl = environment.api + '/RegistrationController/deleteCourse';
  fetchExistingCoursesUrl = environment.api + '/RegistrationController/fetchExistingCourses';
  constructor(private http: Http,
    private router: Router) { }
  addCourse(course: Course): any {
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
    console.log(JSON.stringify(course));
    return this.http.post(this.addCourseUrl, course, httpHeaderOptions);
  }
  fetchExistingCourses(): any {
    return this.http.get(this.fetchExistingCoursesUrl);
  }
  deleteCourse(courseId: String): any {
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
    return this.http.post(this.deleteCourseUrl, courseId, httpHeaderOptions);
  }
}
