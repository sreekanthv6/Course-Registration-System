import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FinanceService } from '../../services/finance.service';
import { User } from 'src/app/user/model/user';
import { Payment } from '../../model/payment';
import { UserService } from 'src/app/user/services/user.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.scss']
})
export class HistoryComponent implements OnInit {
user: User;
payments: Array<Payment>;
isEmpty: Boolean = false;
  constructor(private router: Router, private spinner: NgxSpinnerService, private financeService: FinanceService, private userService: UserService) { }

  ngOnInit() {
    this.spinner.show();
    this.user = this.userService.getSession('user');
    this.financeService.pastPayments(this.user).subscribe(resp => {
      let flag: Payment[]=resp.json();
      this.spinner.hide();
      console.log(flag);
      if(flag.length==0)
      {
        this.isEmpty=true;
      }
      else{
        // console.log(this.payments);
        this.payments=flag;
      }
      
    });
  }

}
