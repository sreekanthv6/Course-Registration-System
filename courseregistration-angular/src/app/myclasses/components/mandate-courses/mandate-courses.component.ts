import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MyclassesService } from '../../services/myclasses.service';
import { Course } from 'src/app/registration/model/course';
import { User } from 'src/app/user/model/user';
import { Payment } from 'src/app/finance/model/payment';
import { UserService } from 'src/app/user/services/user.service';

@Component({
  selector: 'app-mandate-courses',
  templateUrl: './mandate-courses.component.html',
  styleUrls: ['./mandate-courses.component.scss']
})
export class MandateCoursesComponent implements OnInit {

  user: User;
donecourses: Array<Course>;
notenrolledcourses:Array<Course>;
  constructor(private router: Router, private myclassesService: MyclassesService,private userService: UserService,) { }

  ngOnInit() {
    this.user = this.userService.getSession('user');
    this.myclassesService.mandatoryCoursesDone(this.user).subscribe(resp => {
      let flag: Course[]=resp.json();
      this.donecourses=flag;
    });
    this.myclassesService.mandatoryCoursesNotDone(this.user).subscribe(resp => {
      let flag: Course[]=resp.json();
      this.notenrolledcourses=flag;
    });

  }


}
