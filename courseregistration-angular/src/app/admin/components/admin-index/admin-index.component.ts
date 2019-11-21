import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user/model/user';
import { UserService } from 'src/app/user/services/user.service';

@Component({
  selector: 'app-admin-index',
  templateUrl: './admin-index.component.html',
  styleUrls: ['./admin-index.component.scss']
})
export class AdminIndexComponent implements OnInit {

  user: User;
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.user = this.userService.getSession('user');
  }

}
