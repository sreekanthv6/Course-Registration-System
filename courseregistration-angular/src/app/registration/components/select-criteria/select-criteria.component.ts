import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Department } from '../../../user/model/department';
import { Course } from '../../model/course';
import { UserService } from '../../../user/services/user.service';
import { RegistrationService } from '../../services/registration.service';
import { Router } from '@angular/router';
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
  key: string;
  constructor(private formBuilder: FormBuilder, private userService: UserService, private registrationService: RegistrationService, private router: Router) { }

  ngOnInit() {
    this.selectCriteriaForm = this.formBuilder.group({ degree: ['', Validators.required], deptId: ['', Validators.required] });
    this.fetchDepartments();
    
    localStorage.removeItem(this.key);
  }
  get f() { return this.selectCriteriaForm.controls; }
  onSubmit() {
    this.submitted = true;
    if (this.selectCriteriaForm.invalid)
      return;
    this.registrationService.getCourses(this.selectCriteriaForm.value).subscribe(resp => {
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
        if (localStorage.getItem(this.key) === null) {
          console.log("i am here " + localStorage.getItem(this.key));
          let cart: Course[] = [];
          if (cart.push(course)) {
            course.isAddedtoCart = true;
          }
          else
            alert("error");
          localStorage.setItem(this.key, JSON.stringify(cart));
        }
        else {
          let cart: Course[] = JSON.parse(localStorage.getItem(this.key));
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
              console.log("CART IS " + JSON.stringify(cart));
            }
            else
              alert("error");
            localStorage.setItem(this.key, JSON.stringify(cart));
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
