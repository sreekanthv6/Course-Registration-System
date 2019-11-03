import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MyclassesService } from './services/myclasses.service';
import { ReactiveFormsModule, FormsModule } from 'bin/node_modules/@angular/forms/forms';
import { HttpModule } from 'bin/node_modules/@angular/http/http';
import { MyclassesRoutingModule } from './myclasses-routing.module';
import { GradesComponent } from './components/grades/grades.component';

@NgModule({
  declarations: [GradesComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    MyclassesRoutingModule
  ],
  providers: [
  MyclassesService
  ]
})
export class MyclassesModule { }
