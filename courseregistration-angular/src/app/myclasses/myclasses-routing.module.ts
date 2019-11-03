import { NgModule } from '@angular/core';
import { Routes, RouterModule } from 'bin/node_modules/@angular/router/router';
import { ClassScheduleComponent } from './components/class-schedule/class-schedule.component';

const routes: Routes = [
  { path: 'schedule', component: ClassScheduleComponent}
  ];
@NgModule({
 
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class MyclassesRoutingModule { }