import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { PayBillComponent } from './components/pay-bill/pay-bill.component';
import { HistoryComponent } from './components/history/history.component';

const routes: Routes = [
  { path: 'pay', component: PayBillComponent},
  { path: 'history', component: HistoryComponent}
  ];
@NgModule({
 
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FinanceRoutingModule { }
