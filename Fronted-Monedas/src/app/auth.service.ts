import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    const url = 'http://localhost:8090/auth/login';  // Asegúrate de que esta URL sea correcta
    const body = { username, password };
  
    return this.http.post(url, body).pipe(
      tap((response: any) => {
        if (response && response.success) {
          this.loggedIn.next(true);
        } else {
          this.loggedIn.next(false);
        }
      })
    );
  }

  logout() {
    this.loggedIn.next(false);
  }

  isLoggedIn() {
    return this.loggedIn.asObservable();
  }
}
