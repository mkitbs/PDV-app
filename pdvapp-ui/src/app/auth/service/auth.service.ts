import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthLoginInfo } from '../forms/login-info';
import { SignUpInfo } from '../forms/register-info';
import { JWTAuth } from '../response/jwt-auth';
import { environment } from 'src/environments/environment';


const authUrl = environment.authURL;

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = authUrl + 'signin';
  private signupUrl = authUrl + 'signup';
  private emailCheck = authUrl + 'checkEmail/';
  private getLoggedUrl = authUrl + 'getLogged';
  private validEmailUrl = authUrl + 'validEmail/';
  private confirmUserUrl = authUrl + 'confirm/';
  private logoutUrl = authUrl + 'signout/';
  private updUser = authUrl + 'updateUser';
  private checkTokenUrl = authUrl + 'check/';
  private testUrl = authUrl + 'secured/';

  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: AuthLoginInfo): Observable<JWTAuth> {
    return this.http.post<JWTAuth>(this.loginUrl, credentials);
  }

  test(): Observable<any> {
    return this.http.get(this.testUrl);
  }

  signUp(info): Observable<any> {
    return this.http.post<string>(this.signupUrl, info);
  }

  checkEmail(email: String) :Observable<any> {
    return this.http.get(this.emailCheck+email);
  }

  getLogged(): Observable<any> {
    return this.http.get(this.getLoggedUrl);
  }
  
  validEmail(email: String) :Observable<any> {
    return this.http.get(this.validEmailUrl+email);
  }

  checkToken(token: String) {
    return this.http.get(this.checkTokenUrl+token, {responseType: 'text'});
  }

  confirmUser(token: String) :Observable<any> {
    return this.http.get(this.confirmUserUrl+token);
  }

  logout() :Observable<any> {
    return this.http.get(this.logoutUrl);
  }

  updateUser(user) : Observable<any> {
    return this.http.post(this.updUser, user);
  }

  //izmestiti
  getPoreskiPodaci(bukrs, type): Observable<any>{
    return this.http.get(environment.pdvURL + "poreskiPodaci/" + type + "/" + bukrs);
  }

}