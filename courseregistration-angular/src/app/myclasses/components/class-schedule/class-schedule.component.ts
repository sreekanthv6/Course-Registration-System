import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MyclassesService } from '../../services/myclasses.service';
import { Course } from 'src/app/registration/model/course';
import { User } from 'src/app/user/model/user';
import { RegistrationService } from 'src/app/registration/services/registration.service';

@Component({
  selector: 'app-class-schedule',
  templateUrl: './class-schedule.component.html',
  styleUrls: ['./class-schedule.component.scss']
})
export class ClassScheduleComponent implements OnInit {
enrolledcourses: Array<Course>;
user: User;
  constructor(private router: Router, private myClassesService: MyclassesService, private registrationService: RegistrationService) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('user'));
    this.registrationService.fetchEnrolledCourses(this.user).subscribe(resp => {
      let flag: Course[]=resp.json();
      this.enrolledcourses=flag;
    });
  }

}
