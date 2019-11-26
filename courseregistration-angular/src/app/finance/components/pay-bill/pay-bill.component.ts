import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { FinanceService } from '../../services/finance.service';
import { Router } from '@angular/router';
import { User } from 'src/app/user/model/user';
import { Payment } from '../../model/payment';
import { UserService } from 'src/app/user/services/user.service';

@Component({
  selector: 'app-pay-bill',
  templateUrl: './pay-bill.component.html',
  styleUrls: ['./pay-bill.component.scss']
})
export class PayBillComponent implements OnInit {
  paymentForm: FormGroup;
  due: Number;
  paid: Number;
  user: User;
  payment: Payment=new Payment();
  constructor(private formBuilder: FormBuilder, private financeService: FinanceService, private router: Router, private userService: UserService) { }

  ngOnInit() {
    this.user = this.userService.getSession('user');
    this.paymentForm = this.formBuilder.group({ paymentAmount: ['', Validators.required]});
    this.viewDues();
  }
  get f() { return this.paymentForm.controls; }
  viewDues() {
    this.financeService.viewDues(this.user).subscribe(resp => {
      this.due = <Number>resp.json();
    });

  }
  onsubmit(){
this.payment.paymentAmount=this.paymentForm.value.paymentAmount;
this.payment.id=this.user.id;
    this.financeService.postPayment(this.payment).subscribe(resp => {
      this.paid = <Number>resp.json();
      if(this.paid==0)
      alert("Your payment is successful");
      else if(this.paid==1)
      alert("You entered amount which is higher than your due");
      else if(this.paid==2)
      alert("Payment can not be 0 or less");
      else
      alert("Error occured. Please contact finance office");
    });

  }
}


