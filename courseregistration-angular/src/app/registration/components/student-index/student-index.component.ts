import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/user/services/user.service';
import { User } from 'src/app/user/model/user';

@Component({
  selector: 'app-student-index',
  templateUrl: './student-index.component.html',
  styleUrls: ['./student-index.component.scss']
})
export class StudentIndexComponent implements OnInit {
  user: User;
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.user = this.userService.getSession('user');
  }

}
