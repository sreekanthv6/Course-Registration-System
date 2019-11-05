import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MyclassesService } from './services/myclasses.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MyclassesRoutingModule } from './myclasses-routing.module';
import { GradesComponent } from './components/grades/grades.component';
import { MandateCoursesComponent } from './components/mandate-courses/mandate-courses.component';
import { ClassScheduleComponent } from './components/class-schedule/class-schedule.component';

@NgModule({
  declarations: [GradesComponent, ClassScheduleComponent, MandateCoursesComponent],
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
