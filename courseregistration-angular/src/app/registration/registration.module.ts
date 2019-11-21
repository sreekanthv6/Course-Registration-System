import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Http, HttpModule } from '@angular/http';
import { RegistrationRoutingModule } from './registration-routing.module';
import { AddComponent } from './components/add/add.component';
import { DropComponent } from './components/drop/drop.component';
import { SwapComponent } from './components/swap/swap.component';
import { CatalogComponent } from './components/catalog/catalog.component';
import { StudentIndexComponent } from './components/student-index/student-index.component';
import { SelectCriteriaComponent } from './components/select-criteria/select-criteria.component';
import { CartComponent } from './components/cart/cart.component';
import { RegistrationService } from 'src/app/registration/services/registration.service';


@NgModule({
  declarations: [AddComponent, DropComponent, SwapComponent, CatalogComponent, StudentIndexComponent, SelectCriteriaComponent, CartComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    RegistrationRoutingModule
  ],
  providers: [
    RegistrationService,
  ]
})
export class RegistrationModule { }
