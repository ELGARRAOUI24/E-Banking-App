import { Component } from '@angular/core';
import {Navbar} from "../navbar/navbar";
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-admin',
  imports: [
    Navbar,
    RouterOutlet
  ],
  templateUrl: './admin.html',
  styleUrl: './admin.css'
})
export class Admin {

}
