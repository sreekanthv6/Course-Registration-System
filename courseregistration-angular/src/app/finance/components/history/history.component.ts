import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FinanceService } from '../../services/finance.service';
import { User } from 'src/app/user/model/user';
import { Payment } from '../../model/payment';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.scss']
})
export class HistoryComponent implements OnInit {
user: User;
payments: Array<Payment>;
  constructor(private router: Router, private financeService: FinanceService) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('user'));
    this.financeService.pastPayments(this.user).subscribe(resp => {
      let flag: Payment[]=resp.json();
      this.payments=flag;
    });
  }

}
