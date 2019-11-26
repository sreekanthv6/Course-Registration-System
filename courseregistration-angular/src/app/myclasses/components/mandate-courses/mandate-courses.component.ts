import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MyclassesService } from '../../services/myclasses.service';
import { Course } from 'src/app/registration/model/course';
import { User } from 'src/app/user/model/user';
import { Payment } from 'src/app/finance/model/payment';
import { UserService } from 'src/app/user/services/user.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-mandate-courses',
  templateUrl: './mandate-courses.component.html',
  styleUrls: ['./mandate-courses.component.scss']
})
export class MandateCoursesComponent implements OnInit {

  user: User;
donecourses: Array<Course>;
notenrolledcourses:Array<Course>;
isEmpty: Boolean = false;
isEmpty1: Boolean = false;
  constructor(private router: Router, private spinner: NgxSpinnerService, private myclassesService: MyclassesService,private userService: UserService,) { }

  ngOnInit() {
    this.spinner.show();
    this.user = this.userService.getSession('user');
    this.myclassesService.mandatoryCoursesDone(this.user).subscribe(resp => {
      let flag: Course[]=resp.json();
      
      if(flag.length==0){
        this.isEmpty=true;
        // alert("No Grades to Display!!")
      }
      else{
        this.donecourses=flag;
      }
    });
    this.myclassesService.mandatoryCoursesNotDone(this.user).subscribe(resp => {
      let flag: Course[]=resp.json();
      this.spinner.hide();
      if(flag.length==0){
        this.isEmpty=true;
        // alert("No Grades to Display!!")
      }
      else{
        this.notenrolledcourses=flag;
      }
      
    });

  }


}
