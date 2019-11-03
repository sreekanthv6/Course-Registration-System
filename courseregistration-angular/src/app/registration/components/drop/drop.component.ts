import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationService } from '../../services/registration.service';
import { Course } from '../../model/course';
import { User } from 'src/app/user/model/user';
import { EnrollObject } from '../../model/enroll-object';

@Component({
  selector: 'app-drop',
  templateUrl: './drop.component.html',
  styleUrls: ['./drop.component.scss']
})
export class DropComponent implements OnInit {
  enrolledcourses: Array<Course>;
  user: User;
  enrollObject: EnrollObject= new EnrollObject();
  constructor(private registrationService: RegistrationService, private router: Router) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('user'));
    this.registrationService.fetchEnrolledCourses(this.user).subscribe(resp => {
      let flag: Course[]=resp.json();
      this.enrolledcourses=flag;
      console.log(JSON.stringify(this.enrolledcourses));
    });
  }
  onsubmit(courseId: string) {
    //var courseId: string = document.getElementById("drop").nodeValue;
    this.enrollObject.userId=this.user.id;
    this.enrollObject.courseId=courseId;
    this.registrationService.dropCourse(this.enrollObject).subscribe(resp => {
      if(resp.json()==true)
      alert("Successfully dropped");
      else
      alert("error");
    });
  }

}
