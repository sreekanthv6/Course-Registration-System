import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddCourseComponent } from './components/add-course/add-course.component';
import { AdminIndexComponent } from './components/admin-index/admin-index.component';
import { DeleteCourseComponent } from './components/delete-course/delete-course.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminService } from './services/admin.service';

@NgModule({
  declarations: [AddCourseComponent, AdminIndexComponent, DeleteCourseComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    AdminRoutingModule
    
  ],
  providers: [
    AdminService
  ]
})
export class AdminModule { }
