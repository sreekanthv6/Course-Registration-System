import { Component, OnInit } from '@angular/core';
import { RegistrationService } from '../../services/registration.service';
import { UserService } from '../../../user/services/user.service';
import { Course } from '../../model/course';
import { User } from 'src/app/user/model/user';
import { EnrollObject } from 'src/app/registration/model/enroll-object';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  courses: Course[] = [];
  key: string;
  cartEmpty: Boolean = false;
  user: User;
  enrolled: Number;
  enrollObject: EnrollObject = new EnrollObject();
  constructor(private userService: UserService, private registrationService: RegistrationService) { }
  ngOnInit() {

    this.user = this.userService.getSession('user');
    this.loadCart();
  }

  loadCart(): void {
    this.courses = [];
    let cart: Course[] = JSON.parse(localStorage.getItem(this.key));
    if (cart == null || cart.length == 0) {
      this.cartEmpty = true;
    }
    for (var i = 0; i < cart.length; i++) {
      let course: Course = cart[i];
      this.courses.push(course);
      console.log(this.courses);
    }
  }

  removefromcart(courseId: string): void {

    let cart: Course[] = JSON.parse(localStorage.getItem(this.key));
    //let index: number = -1;
    for (var i = 0; i < cart.length; i++) {
      let course: Course = cart[i];
      if (course.courseId == courseId) {
        cart.splice(i, 1);
        break;
      }
    }
    localStorage.setItem(this.key, JSON.stringify(cart));
    this.loadCart();
  }
  enroll(courseId: String) {
    this.enrollObject.userId = this.user.id;
    this.enrollObject.courseId = courseId;
    console.log(JSON.stringify(this.enrollObject));
    this.registrationService.enroll(this.enrollObject).subscribe(resp => {
      let flag = resp.json(); 
      console.log(flag);
      this.enrolled = flag;
      console.log(this.enrolled);
      if (this.enrolled == 1) {
        
        alert("Enrolled Successfully");
      }
      else if (this.enrolled == 4)
        alert("Please make sure you complete course prerequisite");

      else if (this.enrolled == 2)
        alert("Class Strength is full. Please contact your department");
        else if (this.enrolled == 3)
        alert("you are already enrolled to this course");
      else
        alert("Enrollment failed, Please Contact your Department");
    
});
}
}
