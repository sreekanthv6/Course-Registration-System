import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import { UserService } from './services/user.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Http, HttpModule } from '@angular/http';
import { UserRoutingModule } from './user-routing.module';
import { SignupComponent } from './components/signup/signup.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { NgxSpinnerModule } from 'ngx-spinner';
//import { MatCardModule } from '@angular/material';

@NgModule({
  declarations: [LoginComponent, SignupComponent, ForgotPasswordComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    UserRoutingModule,
    NgxSpinnerModule
    
  ],
  providers: [
    UserService
  ]
  // exports: [LoginComponent,RegisterComponent]
})
export class UserModule { }
