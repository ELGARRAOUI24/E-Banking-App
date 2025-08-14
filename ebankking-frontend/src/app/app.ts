import {Component, OnInit} from '@angular/core';
import {Navbar} from './navbar/navbar';
import {RouterOutlet} from '@angular/router';
import {AuthService} from './services/auth';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  protected title = 'ebankking-frontend';

  constructor(private authService : AuthService) {
  }

  ngOnInit() {
    this.authService.loadJwtTokenFromLocalStorage();
  }
}
