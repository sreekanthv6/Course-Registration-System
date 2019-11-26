import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators} from '@angular/forms';
import { User } from '../../model/user';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  user: User;
  submitted = false;
  constructor(private formBuilder: FormBuilder, private spinner: NgxSpinnerService, private userService: UserService, private router: Router) { }
  
  ngOnInit() {
    this.loginForm = this.formBuilder.group({ id: ['', Validators.required], password: ['', [Validators.required, Validators.minLength(6)]] });
  }
  get f() { return this.loginForm.controls; }
  onSubmit() {
    this.spinner.show();
    this.submitted=true;
    if (this.loginForm.invalid)
      return;
      console.log(JSON.stringify(this.loginForm.value));
    this.userService.login(this.loginForm.value).subscribe(resp => {
      this.user = resp.json();
      this.spinner.hide();
      if (this.user.userExists == false) {
        this.userService.sendSubmitMessage("Error::"+this.user.id + " not found");
        alert("Invalid user");
      }
      else {
        if (this.user.validUser == false) {
          this.userService.sendSubmitMessage("Error::invalid password");
          alert("Incorrect password. Please re-enter the password");
        }
        else {
          this.userService.sendSubmitMessage("Success::"+this.user.id + "logged in Successfully");
          this.userService.setSession('user',this.user);
          if(this.user.role==1)
          this.router.navigate(['admin-index']);
          else
          this.router.navigate(['student-index']);
        }
      }
    });
  }
   goRegister(){
     this.router.navigate(['signup']);
   }
}
