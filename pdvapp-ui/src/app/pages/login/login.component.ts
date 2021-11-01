import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/service/auth.service';
import { TokenStorageService } from 'src/app/auth/token-storage/token-storage.service';
import { AuthLoginInfo } from 'src/app/auth/forms/login-info';
import { JWTAuth } from 'src/app/auth/response/jwt-auth';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private auth : AuthService, private tokenStorage: TokenStorageService,
    private httpClient : HttpClient,
    private router: Router) { }

    form: any = {};
    isLoggedIn = false;
    isLoginFailed = false;
    isEndpointOK = false;
    errorMessage = '';
    validEmail = false;
    loginInfo: AuthLoginInfo;
    roles = [];

  ngOnInit() {
  }

  login() {

    this.loginInfo = new AuthLoginInfo(
      this.form.username,
      this.form.password);
      this.auth.attemptAuth(this.loginInfo).subscribe(
        data => {
          console.table(data);
          
            this.tokenStorage.saveToken(data.accessToken);
            localStorage["user"] = this.form.username;
            if(this.form.username == "olivera" || this.form.username == "andrija") {
              this.isLoginFailed = false;
              this.isLoggedIn = true;
              this.router.navigate(['admin']);  
              return;
            }
            this.isLoginFailed = false;
            this.isLoggedIn = true;
            this.router.navigate(['home']);       
          
        },
        error => {
          this.errorMessage = "Pogrešno korisničko ime i/ili lozinka."
          this.isLoginFailed = true;
        }
      );
    }

}