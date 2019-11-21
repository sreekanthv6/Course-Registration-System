import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';
import { Department } from 'bin/src/app/user/model/department';
import { UserService } from 'src/app/user/services/user.service';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.scss']
})
export class AddCourseComponent implements OnInit {
  addCourseForm: FormGroup;
  submitted = false;
  departments: Array<Department>;
  added: Number;
  constructor(private formBuilder: FormBuilder, private userService: UserService, private adminService: AdminService, private router: Router) { }
  
  ngOnInit() {
    this.addCourseForm = this.formBuilder.group({ courseId: ['', Validators.required], courseName: ['', Validators.required], deptId: ['', Validators.required], professor: ['', Validators.required], degree: ['', Validators.required], isMandatory: ['', Validators.required], amount: ['', Validators.required], courseMaxStrength: ['', Validators.required], courseTimings: ['', Validators.required], days: ['', Validators.required], startDate: ['', Validators.required], endDate: ['', Validators.required] }, );
    this.fetchDepartments();
  }
  get f() { return this.addCourseForm.controls; }
  onSubmit() {
    this.submitted=true;
    if (this.addCourseForm.invalid)
      return;
    this.adminService.addCourse(this.addCourseForm.value).subscribe(resp => {
      this.added = resp.json();        
      if(this.added==1)
      alert("Course added successfully");
      else
      alert("Error occured");
    });
  }
  fetchDepartments() {
    this.userService.fetchAllDepartments().subscribe(resp => {
      this.departments = <Department[]>resp.json();
    });
  }
}
