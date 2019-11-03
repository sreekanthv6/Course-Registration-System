import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { User } from '../../model/user';
import { Department } from '../../model/department';
import { Router } from '@angular/router';
import { PasswordValidation } from '../../password.validation';
import { DropdownValidation } from '../../dropdown.required.validation';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  signupForm: FormGroup;
  user: User;
  userSignedUp: String;
  department: Department;
  submitted = false;
  loading = false;
  departments: Array<Department>;

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.signupForm = this.formBuilder.group({ id: ['', Validators.required], firstName: ['', Validators.required], lastName: ['', Validators.required], email: ['', Validators.required], deptId: '', mobile: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]], password: ['', [Validators.required, Validators.minLength(6)]], confirmPassword: ['', [Validators.required]] }, { validator:[PasswordValidation.MatchPassword,DropdownValidation.DeptRequired]});
    this.fetchDepartments();
  }
  get f() { return this.signupForm.controls;}
  onSubmit() {
    this.submitted=true;
    if (this.signupForm.invalid)
      return;
    this.userService.signup(this.signupForm.value).subscribe(resp => {
      let flag = resp.json(); 
      this.userSignedUp = flag;

      if (this.userSignedUp == "signed up") {
        this.userService.sendSubmitMessage("Your account has been created Successfully");
        alert("Your account has been created Successfully. Please login");
        this.router.navigate(['login']);
      }
      else if(this.userSignedUp == "Unexpected Error"){
        this.userService.sendSubmitMessage("Error occured");
        alert("Unexpected error");
      }
      else if(this.userSignedUp == "Already signed up"){
        this.userService.sendSubmitMessage("Already signed up");
        alert("You already have an account");
      }
      else if(this.userSignedUp == "Invalid user") {
        this.userService.sendSubmitMessage("Invalid user");
        alert("Please enter your user ID provided by the university or contact university help");
      }
    });
  }
  fetchDepartments() {
    this.userService.fetchAllDepartments().subscribe(resp => {
      this.departments = <Department[]>resp.json();
    });
  }
}
