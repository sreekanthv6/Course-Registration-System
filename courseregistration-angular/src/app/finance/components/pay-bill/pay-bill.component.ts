import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { FinanceService } from '../../services/finance.service';
import { Router } from '@angular/router';
import { User } from 'src/app/user/model/user';
import { Payment } from '../../model/payment';

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
  submitted: Boolean=false;
  constructor(private formBuilder: FormBuilder, private financeService: FinanceService, private router: Router) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('user'));
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
    this.submitted=true;
this.payment.paymentAmount=this.paymentForm.value.paymentAmount;
this.payment.id=this.user.id;
    this.financeService.postPayment(this.payment).subscribe(resp => {
      this.paid = <Number>resp.json();
      if(this.paid==0)
      alert("Your payment is successful");
      else if(this.paid==1)
      alert("You entered amount which is higher than your due");
      else
      alert("Error occured. Please contact finance office");
    });

  }
}


