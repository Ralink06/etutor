import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {CreateUser, LoggedUser, UserLogin} from "../../model/user/user";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private tokenKey = 'token';
  private authorizationKey = 'Authorization';
  private currentUser: Observable<LoggedUser> = null;

  userLogged: EventEmitter<LoggedUser> = new EventEmitter<LoggedUser>();

  constructor(private http: HttpClient) {
  }

  public retriveUser(): Observable<LoggedUser> {
    const token = localStorage.getItem(this.tokenKey);
    if (!token) {
      this.userLogged.emit(null);
      return new Observable<LoggedUser>();
    }
    if (!this.currentUser) {
      return this.getUser(token);
    }
    return this.currentUser;
  }

  public register(input: CreateUser): Observable<HttpResponse<any>> {
    return this.http.post<HttpResponse<any>>('/api/user', input);
  }

  public login(input: UserLogin): any {
    return this.http.post<any>('/api/login', input, {observe: 'response' as 'body'}).pipe(
      map((res: Response) => {
        this.currentUser = this.getUser(res.headers.get(this.authorizationKey));
        this.currentUser
          .subscribe(user => {
            localStorage.setItem(this.tokenKey, res.headers.get(this.authorizationKey))
            this.userLogged.emit(user);
          })
      })
    );
  }

  public logout() {
    this.userLogged.emit(null);
    this.currentUser = null;
    localStorage.removeItem(this.tokenKey);
  }

  private getUser(token: string): Observable<LoggedUser> {
    let headers = new HttpHeaders({
      'Authorization': token,
    });
    return this.http.get<LoggedUser>(`/api/user/me`, {headers});
  }
}
