import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Course } from '../../model/course';
import { User } from 'src/app/user/model/user';
import { UserService } from 'src/app/user/services/user.service';
import { SwapObject } from '../../model/swap-object';
import { RegistrationService } from '../../services/registration.service';

@Component({
  selector: 'app-swap',
  templateUrl: './swap.component.html',
  styleUrls: ['./swap.component.scss']
})
export class SwapComponent implements OnInit {
  swapForm: FormGroup;
  submitted = false;
  enrolledCourses: Array<Course>;
  notEnrolledCourses: Array<Course>;
  user: User;
  swapObject: SwapObject= new SwapObject();
  swapped: Number;
  constructor(private formBuilder: FormBuilder, private userService: UserService, private registrationService: RegistrationService, private router: Router) { }

  ngOnInit() {
    this.swapForm = this.formBuilder.group({ enrolledCourse: ['', Validators.required], swapCourse: ['', Validators.required] });
    this.user=this.userService.getSession('user');
    this.registrationService.fetchEnrolledCourses(this.user).subscribe(resp => {
      let flag: Course[]=resp.json();
      this.enrolledCourses=flag;
    });
    this.registrationService.fetchNotEnrolledCourses(this.user).subscribe(resp => {
      let flag: Course[]=resp.json();
      this.notEnrolledCourses=flag;
    });
   // sessionStorage.removeItem('cart');
  }
  get f() { return this.swapForm.controls; }
  onSubmit() {
    this.submitted = true;
    this.swapObject.newCourseId=this.swapForm.value.swapCourse;
    this.swapObject.userId=this.user.id;
    this.swapObject.oldCourseId=this.swapForm.value.enrolledCourse;
    console.log(this.swapObject.newCourseId+" "+this.swapObject.userId+" "+this.swapObject.oldCourseId)
    if (this.swapForm.invalid)
      return;
    this.registrationService.swap(this.swapObject).subscribe(resp => {
      this.swapped=resp.json(); 
      if (this.swapped == 1) {
        
        alert("Swapped Successfully");
      }
      else if (this.swapped == 4)
        alert("Please make sure you complete course prerequisite for new course");

      else if (this.swapped == 2)
        alert("Class Strength is full. Please contact your department");
        else if (this.swapped == 3)
        alert("you are already enrolled to this course");
      else
        alert("Enrollment failed, Please Contact your Department");
    
    });
  }

}
