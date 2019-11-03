import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Course } from 'src/app/registration/model/course';
import { Department } from 'src/app/user/model/department';
import { RegistrationService } from 'src/app/registration/services/registration.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/user/services/user.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
  searchForm: FormGroup;
  courses: Array<Course>;
  departments: Array<Department>;
  form: Boolean = true;
  displayCourses: Boolean = false;
  constructor(private formBuilder: FormBuilder, private userService: UserService, private registrationService: RegistrationService, private router: Router) { }

  ngOnInit() {
    this.searchForm = this.formBuilder.group({ degree: ['', Validators.required], deptId: ['', Validators.required] });
    this.fetchDepartments();
  }
  fetchDepartments() {
    this.userService.fetchAllDepartments().subscribe(resp => {
      this.departments = <Department[]>resp.json();
    });
  }
  onsubmit(){
    this.registrationService.getCourses(this.searchForm.value).subscribe(resp => {
      this.form=false;
      this.displayCourses=true;
      this.courses = <Course[]>resp.json();
    });
  }
}

