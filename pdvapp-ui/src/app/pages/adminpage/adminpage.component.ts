import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { saveAs as importedSaveAs } from 'file-saver';
import { FormControl } from '@angular/forms';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adminpage',
  templateUrl: './adminpage.component.html',
  styleUrls: ['./adminpage.component.css'],
})
export class AdminpageComponent implements OnInit {
  year: FormControl = new FormControl('');
  type: FormControl = new FormControl('');
  user;

  constructor(private httpC: HttpClient, private router: Router) {}
  msg = false;
  poreskiHide = false;
  onlyPDV;
  msgText = 'Molimo Vas unesite sve parametre';
  ngOnInit(): void {
    console.log(localStorage['user']);
    if (
      localStorage['user'] != 'olivera' &&
      localStorage['user'] != 'abijelic' &&
      localStorage['user'] != 'andrija'
    ) {
      this.router.navigate(['unauthorized']);
      //this.router.navigate(['admin']);
      return;
    }
    this.httpC.get(environment.authURL + 'getLogged').subscribe(
      (data) => {
        //logged
        this.user = data;

        if (
          this.user.email != 'olivera' &&
          this.user.email != 'abijelic' &&
          this.user.email != 'andrija'
        ) {
          this.router.navigate(['unauthorized']);
          //this.router.navigate(['admin']);
          return;
        }
      },
      (error) => {
        console.log(error.error.message);
        this.router.navigate(['unauthorized']);
        return;
      }
    );
  }

  download() {
    if (
      this.type.value == 'PORESKI_GUBICI' ||
      this.type.value == 'KAPITALNI_PORESKI_GUBICI' ||
      this.type.value == 'PORESKI_KREDITI'
    ) {
      this.msg = false;
      if (this.type.value == 'PORESKI_GUBICI') {
        this.generateFilePoreskiPodaci(this.type.value).subscribe((data) => {
          var blob = new Blob([data], { type: 'blob' });
          const a = document.createElement('a');
          document.body.appendChild(a);

          const url = window.URL.createObjectURL(blob);
          a.href = url;
          a.download = 'Izvestaj-PG.xls';
          a.click();
          window.URL.revokeObjectURL(url);
        });
      } else if (this.type.value == 'KAPITALNI_PORESKI_GUBICI') {
        this.generateFilePoreskiPodaci(this.type.value).subscribe((data) => {
          var blob = new Blob([data], { type: 'blob' });
          const a = document.createElement('a');
          document.body.appendChild(a);

          const url = window.URL.createObjectURL(blob);
          a.href = url;
          a.download = 'Izvestaj-KPG.xls';
          a.click();
          window.URL.revokeObjectURL(url);
        });
      } else if (this.type.value == 'PORESKI_KREDITI') {
        this.generateFilePoreskiPodaci(this.type.value).subscribe((data) => {
          var blob = new Blob([data], { type: 'blob' });
          const a = document.createElement('a');
          document.body.appendChild(a);

          const url = window.URL.createObjectURL(blob);
          a.href = url;
          a.download = 'Izvestaj-PK.xls';
          a.click();
          window.URL.revokeObjectURL(url);
        });
      }
    } else {
      if (this.type.value == '' || this.year.value == '') {
        this.msg = true;
        return;
      }
      this.msg = false;
      if (this.type.value == 'PDV') {
        this.generateFile().subscribe((data) => {
          var blob = new Blob([data], { type: 'blob' });
          const a = document.createElement('a');
          document.body.appendChild(a);

          const url = window.URL.createObjectURL(blob);
          a.href = url;
          a.download = 'Izvestaj-' + this.year.value + '.xls';
          a.click();
          window.URL.revokeObjectURL(url);
        });
      } else {
        this.generateFileDrugo().subscribe((data) => {
          var blob = new Blob([data], { type: 'blob' });
          const a = document.createElement('a');
          document.body.appendChild(a);

          const url = window.URL.createObjectURL(blob);
          a.href = url;
          a.download = 'Izvestaj-' + this.year.value + '.xls';
          a.click();
          window.URL.revokeObjectURL(url);
        });
      }
    }
  }

  onChangeGodina(value) {
    if (value != '2019' && value != '2020' && value != '2021') {
      this.poreskiHide = true;
    } else {
      this.poreskiHide = false;
    }
    if (value == '2022') {
      this.onlyPDV = true;
    } else {
      this.onlyPDV = false;
    }
  }

  generateFile(): Observable<any> {
    return this.httpC.get(
      environment.pdvURL + 'generateExcel/' + this.year.value,
      { responseType: 'blob' }
    );
  }

  generateFileDrugo(): Observable<any> {
    return this.httpC.get(
      environment.pdvURL + 'generateExcelTroskovi/' + this.year.value,
      { responseType: 'blob' }
    );
  }

  generateFilePoreskiPodaci(type): Observable<any> {
    return this.httpC.get(
      environment.pdvURL +
        'generatePoreskiGubici/' +
        type +
        '/' +
        this.year.value,
      { responseType: 'blob' }
    );
  }
}
