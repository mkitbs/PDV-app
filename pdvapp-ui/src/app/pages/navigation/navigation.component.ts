import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
})
export class NavigationComponent implements OnInit {
  constructor(
    private router: Router,
    private toastr: ToastrService,
    private httpC: HttpClient
  ) {}
  abijelic = false;
  ngOnInit(): void {
    if (localStorage['user'] == null || localStorage['user'] == undefined) {
      localStorage.clear();
      this.toastr.error('Molimo Vas da izvšite prijavu.');
      this.router.navigate(['login']);
      return;
    }
    if (
      localStorage['user'] == 'abijelic' ||
      localStorage['user'] == 'olivera' ||
      localStorage['user'] == 'andrija'
    ) {
      this.abijelic = true;
    } else {
      this.abijelic = false;
    }
  }

  pocetna() {
    if (window.location.hash == '#/home') {
      window.location.reload();
      return;
    }
    this.router.navigate(['home']);
  }
  izvestavanje() {
    this.router.navigate(['admin']);
  }
  logout() {
    localStorage.clear();
    this.httpC.get(environment.authURL + 'signout').subscribe((data) => {
      this.router.navigate(['login']);
    });
  }
}
