import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.scss']
})
export class AddCourseComponent implements OnInit {
  addCourseForm: FormGroup;
  submitted = false;
  constructor(private formBuilder: FormBuilder, private adminService: AdminService, private router: Router) { }
  
  ngOnInit() {
    this.addCourseForm = this.formBuilder.group({ courseId: ['', Validators.required], courseName: ['', Validators.required] });
  }
  get f() { return this.addCourseForm.controls; }
  onSubmit() {
  }
}
