import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user/model/user';
import { MyclassesService } from '../../services/myclasses.service';
import { Grade } from '../../model/grade';
import { UserService } from 'src/app/user/services/user.service';

@Component({
  selector: 'app-grades',
  templateUrl: './grades.component.html',
  styleUrls: ['./grades.component.scss']
})
export class GradesComponent implements OnInit {
user: User;
gradeObjects: Array<Grade>;
  constructor(private myClassesService: MyclassesService,private userService: UserService,) { }

  ngOnInit() {
    
    this.user = this.userService.getSession('user');
    this.myClassesService.viewGrades(this.user).subscribe(resp => {
      let flag: Grade[]=resp.json();
      this.gradeObjects=flag;  
    });
  }

}
