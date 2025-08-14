import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {jwtDecode} from 'jwt-decode';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  backendHost: string = "http://localhost:8585";
  isAuthenticated : boolean = false;
  roles : any;
  user : any;
  accessToken! : any;

  constructor(private http:HttpClient, private router : Router) {
  }

  public login(username : string, password : string){
    let options = {
      headers : new HttpHeaders().set("Content-Type","application/x-www-form-urlencoded")
    }
    let params = new HttpParams().set("userName", username).set("password", password);
    return this.http.post(this.backendHost+"/auth/login", params, options)
  }

  loadProfile(data : any) {
    this.isAuthenticated = true;
    this.accessToken = data['access_token'];
    let decodedJwt:any = jwtDecode(this.accessToken);
    this.user = decodedJwt.sub;
    this.roles = decodedJwt.scope;
    window.localStorage.setItem("jwt-token",this.accessToken);
  }

  logout() {
    this.isAuthenticated = false;
    this.accessToken = undefined;
    this.user = undefined;
    this.roles = undefined;
    window.localStorage.removeItem("jwt-token");
    this.router.navigateByUrl("/login");
  }

  loadJwtTokenFromLocalStorage() {
    let token = window.localStorage.getItem("jwt-token");
    if(token){
      this.loadProfile({"access_token": token});
      this.router.navigateByUrl("/admin/customers")
    }
  }
}
