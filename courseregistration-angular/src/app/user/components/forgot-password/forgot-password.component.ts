import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { PasswordValidation } from '../../password.validation';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {
resetPasswordForm: FormGroup;
submitted = false;
passwordReset: Number;
  constructor(private formBuilder: FormBuilder, private spinner: NgxSpinnerService,private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.resetPasswordForm = this.formBuilder.group({ id: ['', Validators.required], email: ['', Validators.required], password: ['', [Validators.required, Validators.minLength(6)]], confirmPassword: ['', [Validators.required]] }, {validator: PasswordValidation.MatchPassword});
  }
  get f() { return this.resetPasswordForm.controls; }
  onSubmit() {
    this.spinner.show();
    this.submitted = true;
    if (this.resetPasswordForm.invalid)
      return;
    this.userService.resetPassword(this.resetPasswordForm.value).subscribe(resp => {
      let flag = resp.json(); this.passwordReset = flag;
      this.spinner.hide();
      if (this.passwordReset == 1) {
        this.userService.sendSubmitMessage("Password reset");
        alert("Your password has been reset successfully. Please login");
        this.router.navigate(['login']);
      }
      else if(this.passwordReset==2){
      this.userService.sendSubmitMessage("Invalid email");
        alert("Please enter the email address with which you have registered");
      }
      else if(this.passwordReset==3){
        this.userService.sendSubmitMessage("Invalid user ID");
          alert("You did not sign up. Please make sure you have an account.");
        }
        else{
          this.userService.sendSubmitMessage("error occured");
          alert("Unexpected error");
        }
    });
    }
}
