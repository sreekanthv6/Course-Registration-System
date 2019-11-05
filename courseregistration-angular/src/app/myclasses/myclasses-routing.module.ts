import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClassScheduleComponent } from './components/class-schedule/class-schedule.component';
import { GradesComponent } from './components/grades/grades.component';
import { MandateCoursesComponent } from './components/mandate-courses/mandate-courses.component';

const routes: Routes = [
  { path: 'schedule', component: ClassScheduleComponent},
  { path: 'grades', component: GradesComponent},
  { path: 'mandatory', component: MandateCoursesComponent}
  ];
@NgModule({
 
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class MyclassesRoutingModule { }