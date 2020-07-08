import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {CreateUser, LoggedUser, UserLogin} from "../../model/user/user";
import {BehaviorSubject, Observable} from "rxjs";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private tokenKey = 'token';
  private authorizationKey = 'Authorization';

  public currentUserSubject = new BehaviorSubject<LoggedUser>(null);
  public isAuthenticationEnded = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient) {
    const token = localStorage.getItem(this.tokenKey);
    if (!token) {
      this.currentUserSubject.next(null);
      this.isAuthenticationEnded.next(true);
      return;
    }
    if (!this.currentUserSubject.getValue()) {
      this.getUser(token).subscribe(user => {
        this.currentUserSubject.next(user);
        this.isAuthenticationEnded.next(true);
      })
    }
  }

  public register(input: CreateUser): Observable<HttpResponse<any>> {
    return this.http.post<HttpResponse<any>>('/api/user', input);
  }

  public login(input: UserLogin): any {
    return this.http.post<any>('/api/login', input, {observe: 'response' as 'body'}).pipe(
      map((res: Response) => {
        this.getUser(res.headers.get(this.authorizationKey))
        .subscribe(user => {
          localStorage.setItem(this.tokenKey, res.headers.get(this.authorizationKey))
          this.currentUserSubject.next(user);
          this.isAuthenticationEnded.next(true);
        })
      })
    );
  }

  public logout() {
    this.currentUserSubject.next(null);
    localStorage.removeItem(this.tokenKey);
  }

  private getUser(token: string): Observable<LoggedUser> {
    let headers = new HttpHeaders({
      'Authorization': token,
    });
    return this.http.get<LoggedUser>(`/api/user/me`, {headers});
  }
}
