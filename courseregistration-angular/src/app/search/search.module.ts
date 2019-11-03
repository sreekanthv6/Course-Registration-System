import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SearchComponent } from './components/search/search.component';
import { HttpModule } from '@angular/http';
import { RegistrationRoutingModule } from '../registration/registration-routing.module';
import { SearchService } from './services/search.service';
import { SearchRoutingModule } from './search-routing.module';

@NgModule({
  declarations: [SearchComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    SearchRoutingModule,
  ],
  providers: [
    SearchService
  ]
})
export class SearchModule { }
