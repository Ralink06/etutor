import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user-service/user.service";
import {LoggedUser} from "../../model/user/user";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  loading: boolean = false;
  loggedUser: LoggedUser = null;

  constructor(private userService: UserService) {

    userService.retriveUser()
      .subscribe(user => {
        this.loggedUser = user;
      });
  }

  ngOnInit(): void {
    this.userService.userLogged
      .subscribe(user => this.loggedUser = user);
  }

  logout() {
    this.loggedUser = null;
    this.userService.logout();
  }
}
