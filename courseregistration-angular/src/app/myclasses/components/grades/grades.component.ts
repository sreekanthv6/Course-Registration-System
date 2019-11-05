import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user/model/user';
import { MyclassesService } from '../../services/myclasses.service';
import { Grade } from '../../model/grade';

@Component({
  selector: 'app-grades',
  templateUrl: './grades.component.html',
  styleUrls: ['./grades.component.scss']
})
export class GradesComponent implements OnInit {
user: User;
gradeObjects: Array<Grade>;
  constructor(private myClassesService: MyclassesService) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('user'));
    this.myClassesService.viewGrades(this.user).subscribe(resp => {
      let flag: Grade[]=resp.json();
      this.gradeObjects=flag;  
    });
  }

}
