import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Department } from '../../../user/model/department';
import { Course } from '../../model/course';
import { UserService } from '../../../user/services/user.service';
import { RegistrationService } from '../../services/registration.service';
import { Router } from '@angular/router';
import { User } from 'bin/src/app/user/model/user';
import { SelectCriteria } from '../../model/select-criteria';
import { NgxSpinnerService } from 'ngx-spinner';
@Component({
  selector: 'app-select-criteria',
  templateUrl: './select-criteria.component.html',
  styleUrls: ['./select-criteria.component.scss']
})
export class SelectCriteriaComponent implements OnInit {
  selectCriteriaForm: FormGroup;
  submitted = false;
  department: Department;
  departments: Array<Department>;
  form: Boolean = true;
  displayCourses = false;
  courses: Array<Course>;
  user: User;
  selectCriteria: SelectCriteria = new SelectCriteria();
  constructor(private formBuilder: FormBuilder, private spinner: NgxSpinnerService, private userService: UserService, private registrationService: RegistrationService, private router: Router) { }

  ngOnInit() {
    this.selectCriteriaForm = this.formBuilder.group({ degree: ['', Validators.required], deptId: ['', Validators.required] });
    this.fetchDepartments();
    this.user=this.userService.getSession('user');
   // sessionStorage.removeItem('cart');
  }
  get f() { return this.selectCriteriaForm.controls; }
  onSubmit() {
    this.spinner.show();
    this.submitted = true;
    this.selectCriteria.degree=this.selectCriteriaForm.value.degree;
    this.selectCriteria.userId=this.user.id;
    this.selectCriteria.deptId=this.selectCriteriaForm.value.deptId;
    console.log(this.selectCriteria.degree+" "+this.selectCriteria.userId+" "+this.selectCriteria.deptId);
    if (this.selectCriteriaForm.invalid)
      return;
    this.registrationService.fetchAvailableCourses(this.selectCriteria).subscribe(resp => {
      this.spinner.hide();
      this.form = false;
      this.displayCourses = true;
      this.courses = <Course[]>resp.json();
    });
  }
  addtocart(courseId: string) {
    if (courseId) {
      console.log("course id is " + courseId);
      this.registrationService.find(courseId).subscribe(resp => {
        let course: Course = <Course>resp.json();
        if (sessionStorage.getItem('cart') === null) {
          console.log("i am here " + sessionStorage.getItem('cart'));
          let cart: Course[] = [];
          if (cart.push(course)) {
            course.isAddedtoCart = true;
            alert("Course added to cart");
          }
          else
            alert("error");
            sessionStorage.setItem('cart', JSON.stringify(cart));
        }
        else {
          let cart: Course[] = JSON.parse(sessionStorage.getItem('cart'));
          let index: number = -1;
          for (var i = 0; i < cart.length; i++) {
            let course: Course = cart[i];
            if (course.courseId == courseId) {
              index = i;
              break;
            }
          }
          if (index == -1) {
            console.log("got this list " + JSON.stringify(cart));
            if (cart.push(course)) {
              course.isAddedtoCart = true;
              alert("course added to cart");
              console.log("CART IS " + JSON.stringify(cart));
            }
            else
              alert("error");
              sessionStorage.setItem('cart', JSON.stringify(cart));
          }
          else
            alert("course already added to cart");
        }
      });
    }
  }
  fetchDepartments() {
    this.userService.fetchAllDepartments().subscribe(resp => {
      this.departments = <Department[]>resp.json();
    });
  }
}
