import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MyclassesService } from '../../services/myclasses.service';
import { Course } from 'src/app/registration/model/course';
import { User } from 'src/app/user/model/user';
import { RegistrationService } from 'src/app/registration/services/registration.service';
import { UserService } from 'src/app/user/services/user.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-class-schedule',
  templateUrl: './class-schedule.component.html',
  styleUrls: ['./class-schedule.component.scss']
})
export class ClassScheduleComponent implements OnInit {
enrolledcourses: Array<Course>;
user: User;
isEmpty: Boolean = false;
  constructor(private router: Router,private userService: UserService, private spinner: NgxSpinnerService, private myClassesService: MyclassesService, private registrationService: RegistrationService) { }

  ngOnInit() {
    this.spinner.show();
    this.user = this.userService.getSession('user');
    this.registrationService.fetchEnrolledCourses(this.user).subscribe(resp => {
      let flag: Course[]=resp.json();
      this.spinner.hide();
      if(flag.length==0){
        this.isEmpty=true;
        // alert("No Grades to Display!!")
      }
      else{
        this.enrolledcourses=flag;
      }
      
    });
  }

}
