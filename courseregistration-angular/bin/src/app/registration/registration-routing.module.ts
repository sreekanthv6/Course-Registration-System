import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { AddComponent } from './components/add/add.component';
import { DropComponent } from './components/drop/drop.component';
import { SwapComponent } from './components/swap/swap.component';
import { CatalogComponent } from './components/catalog/catalog.component';
import { SelectCriteriaComponent } from './components/select-criteria/select-criteria.component';
import { StudentIndexComponent } from './components/student-index/student-index.component';
import { CartComponent } from './components/cart/cart.component';

const routes: Routes = [
  { path: 'add', component: AddComponent},
  { path: 'drop', component: DropComponent},
  { path: 'swap', component: SwapComponent},
  { path: 'catalog', component: CatalogComponent},
  { path: 'select-criteria', component: SelectCriteriaComponent},
  { path: 'student-index', component: StudentIndexComponent},
  {path:  'cart', component: CartComponent}
  ];
@NgModule({
 
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegistrationRoutingModule { }
