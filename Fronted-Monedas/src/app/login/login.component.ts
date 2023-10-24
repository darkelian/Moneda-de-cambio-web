import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';
  isLoggedIn: boolean = false;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.isLoggedIn().subscribe((loggedIn: boolean) => {
      this.isLoggedIn = loggedIn;
    });
  }

  onLogin() {
    this.authService.login(this.username, this.password).subscribe(response => {
      if (response.success) {
        alert('Login successful!');
      } else {
        alert('Login failed!');
      }
    });
  }

  onLogout() {
    this.authService.logout();
  }
}
