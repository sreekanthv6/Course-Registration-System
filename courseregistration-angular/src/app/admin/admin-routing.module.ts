import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { AdminIndexComponent } from './components/admin-index/admin-index.component';
import { DeleteCourseComponent } from './components/delete-course/delete-course.component';
import { AddCourseComponent } from './components/add-course/add-course.component';

const routes: Routes = [
  { path: 'addcourse', component: AddCourseComponent},
  { path: 'deletecourse', component: DeleteCourseComponent},
  { path: 'admin-index', component: AdminIndexComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
