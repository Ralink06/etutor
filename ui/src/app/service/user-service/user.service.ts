import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {CreateUser, UserLogin} from "../../model/user/user";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  public register(input: CreateUser): Observable<HttpResponse<any>> {
    return this.http.post<HttpResponse<any>>('/api/user', input);
  }

  public login(input: UserLogin): any {
    return this.http.post<any>('/api/login', input, {observe: 'response' as 'body'}).pipe(
      map((res: Response) => localStorage.setItem('token', res.headers.get('Authorization')))
    );
  }
}
