import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user-service/user.service";
import {LoggedUser} from "../../model/user/user";
import {BehaviorSubject, Observable} from "rxjs";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  loggedUser: BehaviorSubject<LoggedUser> = this.userService.currentUserSubject;
  isAuthenticatedEnded: BehaviorSubject<boolean> = this.userService.isAuthenticationEnded;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
  }

  logout() {
    // this.loggedUser = null;
    this.userService.logout();
  }
}
