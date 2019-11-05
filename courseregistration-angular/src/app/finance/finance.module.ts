import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PayBillComponent } from './components/pay-bill/pay-bill.component';
import { HistoryComponent } from './components/history/history.component';
import { HttpModule } from '@angular/http';
import { FinanceRoutingModule } from './finance-routing.module';
import { FinanceService } from './services/finance.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [PayBillComponent, HistoryComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    FinanceRoutingModule,

  ],
  providers: [
    FinanceService
  ]
})
export class FinanceModule { }
