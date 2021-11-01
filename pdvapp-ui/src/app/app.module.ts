import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { Routes, RouterModule } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptor } from './auth/auth-interceptor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { NavigationComponent } from './pages/navigation/navigation.component';
import { ToastrModule } from 'ngx-toastr';
import { AdminpageComponent } from './pages/adminpage/adminpage.component';
import { RegisterComponent } from './pages/register/register.component';
import { UnauthorizedComponent} from './pages/unauthorized/unauthorized.component';
import { CurrencyPipe } from '@angular/common';


const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'admin', component: AdminpageComponent },
  { path: 'unauthorized', component: UnauthorizedComponent},
  { path: '', component: LoginComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    NavigationComponent,
    AdminpageComponent,
    RegisterComponent,
    UnauthorizedComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
  
    NgbModule,
    RouterModule.forRoot(appRoutes, { useHash: true }),
    ToastrModule.forRoot({
      timeOut: 2000,
      positionClass: 'toast-top-center',
      preventDuplicates: true,
    }),
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true,
  }, CurrencyPipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
