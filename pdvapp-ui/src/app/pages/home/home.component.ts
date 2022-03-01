import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormControl } from '@angular/forms';
import { environment } from 'src/environments/environment';
import { windowWhen } from 'rxjs/operators';
import { CurrencyPipe } from '@angular/common';
import { PoreskiPodaci } from 'src/app/auth/forms/PoreskiPodaci';
import { AuthService } from 'src/app/auth/service/auth.service';

export interface IHash {
  [details: string]: number;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(
    private httpC: HttpClient,
    private currencyPipe: CurrencyPipe,
    private service: AuthService
  ) {}

  poreskiPodaci: PoreskiPodaci[] = [];
  data;
  comps;
  poreskiHide = true;
  onlyPDV;
  type: FormControl = new FormControl('');
  country: FormControl = new FormControl('');
  year: FormControl = new FormControl('');
  pdv1januar: FormControl = new FormControl('');
  pdv1februar: FormControl = new FormControl('');
  pdv1mart: FormControl = new FormControl('');
  pdv1april: FormControl = new FormControl('');
  pdv1maj: FormControl = new FormControl('');
  pdv1jun: FormControl = new FormControl('');
  pdv1jul: FormControl = new FormControl('');
  pdv1avgust: FormControl = new FormControl('');
  pdv1septembar: FormControl = new FormControl('');
  pdv1oktobar: FormControl = new FormControl('');
  pdv1novembar: FormControl = new FormControl('');
  pdv1decembar: FormControl = new FormControl('');

  pdv2januar: FormControl = new FormControl('');
  pdv2februar: FormControl = new FormControl('');
  pdv2mart: FormControl = new FormControl('');
  pdv2april: FormControl = new FormControl('');
  pdv2maj: FormControl = new FormControl('');
  pdv2jun: FormControl = new FormControl('');
  pdv2jul: FormControl = new FormControl('');
  pdv2avgust: FormControl = new FormControl('');
  pdv2septembar: FormControl = new FormControl('');
  pdv2oktobar: FormControl = new FormControl('');
  pdv2novembar: FormControl = new FormControl('');
  pdv2decembar: FormControl = new FormControl('');

  pdv3januar: FormControl = new FormControl('');
  pdv3februar: FormControl = new FormControl('');
  pdv3mart: FormControl = new FormControl('');
  pdv3april: FormControl = new FormControl('');
  pdv3maj: FormControl = new FormControl('');
  pdv3jun: FormControl = new FormControl('');
  pdv3jul: FormControl = new FormControl('');
  pdv3avgust: FormControl = new FormControl('');
  pdv3septembar: FormControl = new FormControl('');
  pdv3oktobar: FormControl = new FormControl('');
  pdv3novembar: FormControl = new FormControl('');
  pdv3decembar: FormControl = new FormControl('');

  pg1: FormControl = new FormControl('');
  pg2: FormControl = new FormControl('');
  pg3: FormControl = new FormControl('');
  pg4: FormControl = new FormControl('');
  pg5: FormControl = new FormControl('');
  pg6: FormControl = new FormControl('');
  pg7: FormControl = new FormControl('');
  pg8: FormControl = new FormControl('');

  kpg1: FormControl = new FormControl('');
  kpg2: FormControl = new FormControl('');
  kpg3: FormControl = new FormControl('');
  kpg4: FormControl = new FormControl('');
  kpg5: FormControl = new FormControl('');
  kpg6: FormControl = new FormControl('');
  kpg7: FormControl = new FormControl('');
  kpg8: FormControl = new FormControl('');

  pk1: FormControl = new FormControl('');
  pk2: FormControl = new FormControl('');
  pk3: FormControl = new FormControl('');
  pk4: FormControl = new FormControl('');
  pk5: FormControl = new FormControl('');

  p1: FormControl = new FormControl('');
  p2: FormControl = new FormControl('');
  p3: FormControl = new FormControl('');
  p4: FormControl = new FormControl('');
  p5: FormControl = new FormControl('');
  p6: FormControl = new FormControl('');
  p7: FormControl = new FormControl('');
  p8: FormControl = new FormControl('');
  p9: FormControl = new FormControl('');
  p10: FormControl = new FormControl('');
  p11: FormControl = new FormControl('');
  p12: FormControl = new FormControl('');
  p13: FormControl = new FormControl('');
  p14: FormControl = new FormControl('');
  p15: FormControl = new FormControl('');
  p16: FormControl = new FormControl('');
  p17: FormControl = new FormControl('');
  p18: FormControl = new FormControl('');
  p19: FormControl = new FormControl('');
  p20: FormControl = new FormControl('');
  p21: FormControl = new FormControl('');
  p22: FormControl = new FormControl('');
  p23: FormControl = new FormControl('');
  p24: FormControl = new FormControl('');
  p25: FormControl = new FormControl('');
  p26: FormControl = new FormControl('');
  p27: FormControl = new FormControl('');
  p28: FormControl = new FormControl('');
  p29: FormControl = new FormControl('');
  p30: FormControl = new FormControl('');
  p31: FormControl = new FormControl('');
  p32: FormControl = new FormControl('');
  p33: FormControl = new FormControl('');
  p34: FormControl = new FormControl('');
  p35: FormControl = new FormControl('');
  p36: FormControl = new FormControl('');
  p37: FormControl = new FormControl('');
  p38: FormControl = new FormControl('');
  p39: FormControl = new FormControl('');
  p40: FormControl = new FormControl('');
  p41: FormControl = new FormControl('');
  p42: FormControl = new FormControl('');
  p43: FormControl = new FormControl('');
  p44: FormControl = new FormControl('');
  p45: FormControl = new FormControl('');
  p46: FormControl = new FormControl('');
  p47: FormControl = new FormControl('');
  p48: FormControl = new FormControl('');
  p49: FormControl = new FormControl('');
  p50: FormControl = new FormControl('');
  p51: FormControl = new FormControl('');
  p52: FormControl = new FormControl('');
  p53: FormControl = new FormControl('');
  p54: FormControl = new FormControl('');
  p55: FormControl = new FormControl('');
  p56: FormControl = new FormControl('');
  p57: FormControl = new FormControl('');
  p58: FormControl = new FormControl('');
  p59: FormControl = new FormControl('');
  p60: FormControl = new FormControl('');
  p61: FormControl = new FormControl('');
  p62: FormControl = new FormControl('');
  p63: FormControl = new FormControl('');
  p64: FormControl = new FormControl('');
  p65: FormControl = new FormControl('');
  p66: FormControl = new FormControl('');
  p67: FormControl = new FormControl('');
  p68: FormControl = new FormControl('');
  p69: FormControl = new FormControl('');
  odabranaGodina = false;
  listFormControl: any = [
    this.p1,
    this.p2,
    this.p3,
    this.p4,
    this.p5,
    this.p6,
    this.p7,
    this.p8,
    this.p9,
    this.p10,
    this.p11,
    this.p12,
    this.p13,
    this.p14,
    this.p15,
    this.p16,
    this.p17,
    this.p18,
    this.p19,
    this.p20,
    this.p21,
    this.p22,
    this.p23,
    this.p24,
    this.p25,
    this.p26,
    this.p27,
    this.p28,
    this.p29,
    this.p30,
    this.p31,
    this.p32,
    this.p33,
    this.p34,
    this.p35,
    this.p36,
    this.p37,
    this.p38,
    this.p39,
    this.p40,
    this.p41,
    this.p42,
    this.p43,
    this.p44,
    this.p45,
    this.p46,
    this.p47,
    this.p48,
    this.p49,
    this.p50,
    this.p51,
    this.p52,
    this.p53,
    this.p54,
    this.p55,
    this.p56,
    this.p57,
    this.p58,
    this.p59,
    this.p60,
    this.p61,
    this.p62,
    this.p63,
    this.p64,
    this.p65,
    this.p66,
    this.p67,
    this.p68,
    this.p69,
  ];
  tempdata;

  bukrs;

  id1;
  id2;
  showTable = false;
  showTable2 = false;
  showTablePoreskiGubici = false;
  showTableKapitalniPoreskiGubici = false;
  showTablePoreskiKrediti = false;
  msg = false;
  msgText = 'Molimo Vas unesite sve parametre';
  dobavio = false;
  dobavioMessage = '';
  dobavioDatum = '';
  months = [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
    'August',
    'September',
    'October',
    'November',
    'December',
  ];
  ngOnInit(): void {
    this.httpC.get(environment.pdvURL + 'companies').subscribe((data) => {
      this.comps = data;
      this.comps.unshift({
        companyName: 'Odaberite kompaniju iz padajuće liste',
        companyCode: '404',
      });
    });
  }

  processMyValue(value): void {
    let numberVal = parseInt(value.value).toLocaleString();
    numberVal = numberVal.split(',').join('.');
    value.setValue(numberVal);
  }
  temp = [];
  sumAll2() {
    this.temp = this.listFormControl;
    this.listFormControl.forEach((element) => {
      //this.processMyValue(element);
      var v = +element.value.split('.').join('') || 0;
      element.setValue(v);
    });
    /*
    var sabirak = this.listFormControl[0].value - this.listFormControl[1].value - this.listFormControl[3].value + this.listFormControl[4].value;
    var sabirak2 = 0;
    for(let x = 5; x <= 16; x++) {
       sabirak2 += this.listFormControl[x].value;
    }                  //18 
    var sabirak3 = 0 - this.listFormControl[17].value - this.listFormControl[18].value + this.listFormControl[19].value - this.listFormControl[20].value + this.listFormControl[21].value;
    var sabirak4 = 0;
    for(let y = 22; y <= 25; y++) {
       sabirak4 += this.listFormControl[y].value;
    }
    */

    /*              +6+7+..17
    Добит  (1-2-4+5 до 17-18-19+20-21+22 до 26-27+28+29                                                                                                                                                                                                                                                                    -30+31+32-33+34-35-36+37 до 44-45 до 50+57+58)>0 (или негативан износ са редног броја 60)
     */

    /*
    var sabirak5 = 0;
    for(let a = 36; a <= 43; a++) {
      sabirak5 += this.listFormControl[a].value;
    }
    var sabirak6 = 0;
    for(let b = 45; b <= 49; b++) {
      sabirak6 += this.listFormControl[b].value;
    }                                                                                    //+26-27+28+29-30+31+32
   var vla59 = sabirak + sabirak2 + sabirak3 + sabirak4 - this.listFormControl[26].value + this.listFormControl[27].value + this.listFormControl[28].value - this.listFormControl[29].value + this.listFormControl[30].value + this.listFormControl[31].value - this.listFormControl[32].value + this.listFormControl[33].value - this.listFormControl[34].value - this.listFormControl[35].value + sabirak5 - this.listFormControl[44].value - sabirak6 + this.listFormControl[56].value + this.listFormControl[57].value;
  */

    /*
Губитак (3+2+4-5 до 17+18+19-20+21-22 до 26+27-28-29+30-31-32+33-34+35+36-37 до 44+45 до 50-57-58)>0 (или негативан износ са редног броја 59)
   */

    /*
  var sab1 = this.listFormControl[2].value + this.listFormControl[1].value + this.listFormControl[3].value;
  var sab2 = 0;
  for(let u = 4; u <= 16; u++) {
    sab2 -= this.listFormControl[u].value;
  }
  var sab3 = 0 + this.listFormControl[17].value + this.listFormControl[18].value - this.listFormControl[19].value + this.listFormControl[20].value;
  var sab4 = 0;
  for(let q = 21; q <= 25; q++) {
    sab4 -= this.listFormControl[q].value;
  }
  var sab6 = 0;
  for(let q = 36; q <= 43; q++) {
    sab6 -= this.listFormControl[q].value;
  }
  var sab7 = 0;
  for(let q = 44; q <= 49; q++) {
    sab7 += this.listFormControl[q].value;
  }
  //Губитак (3+2+4-5 до 17+18+19-20+21-22                                                                                                                                                                                                                                                   до 26+27-28-29+30-31-32+33-34+35+36-37 до 44+45 до 50-57-58)>0 (или негативан износ са редног броја 59)
   var vla60 = sab1 + sab2 + sab3 + sab4 + this.listFormControl[26].value - this.listFormControl[27].value - this.listFormControl[28].value + this.listFormControl[29].value - this.listFormControl[30].value - this.listFormControl[31].value+this.listFormControl[32].value-this.listFormControl[33].value+this.listFormControl[34].value+this.listFormControl[35].value + sab6 + sab7 - this.listFormControl[56].value - this.listFormControl[57].value;
   
  */

    /*
59 = 1      60 = 2
59 = -1     60 = -2
*/

    /* brojevi za niz, ne redove
Dobit (0-1-3+4 do 16-17-18+19-20+21 do 25-26+27+28-29+30+31-32+33-34-35+36 do 43-44 do 49+56+57)>0 
(ili negativan iznos sa rednog broja 60) 0.00
*/

    var vla59 =
      this.listFormControl[0].value -
      this.listFormControl[1].value -
      this.listFormControl[3].value +
      this.listFormControl[4].value +
      this.listFormControl[5].value +
      this.listFormControl[6].value +
      this.listFormControl[7].value +
      this.listFormControl[8].value +
      this.listFormControl[9].value +
      this.listFormControl[10].value +
      this.listFormControl[11].value +
      this.listFormControl[12].value +
      this.listFormControl[13].value +
      this.listFormControl[14].value +
      this.listFormControl[15].value +
      this.listFormControl[16].value -
      this.listFormControl[17].value -
      this.listFormControl[18].value +
      this.listFormControl[19].value -
      this.listFormControl[20].value +
      this.listFormControl[21].value +
      this.listFormControl[22].value +
      this.listFormControl[23].value +
      this.listFormControl[24].value +
      this.listFormControl[25].value -
      this.listFormControl[26].value +
      this.listFormControl[27].value +
      this.listFormControl[28].value -
      this.listFormControl[29].value +
      this.listFormControl[30].value +
      this.listFormControl[31].value -
      this.listFormControl[32].value +
      this.listFormControl[33].value -
      this.listFormControl[34].value -
      this.listFormControl[35].value +
      this.listFormControl[36].value +
      this.listFormControl[37].value +
      this.listFormControl[38].value +
      this.listFormControl[39].value +
      this.listFormControl[40].value +
      this.listFormControl[41].value +
      this.listFormControl[42].value +
      this.listFormControl[43].value -
      this.listFormControl[44].value -
      this.listFormControl[45].value -
      this.listFormControl[46].value -
      this.listFormControl[47].value -
      this.listFormControl[48].value -
      this.listFormControl[49].value +
      this.listFormControl[56].value +
      this.listFormControl[57].value;

    /* brojevi za niz, ne redove
  Gubitak (2+1+3-4 do 16+17+18-19+20-21 do 25+26-27-28+29-30-31+32-33+34+35-36 do 43+44 do 49-56-57)>0 
  (ili negativan iznos sa rednog broja 59)
 */

    var vla60 =
      this.listFormControl[2].value +
      this.listFormControl[1].value +
      this.listFormControl[3].value -
      this.listFormControl[4].value -
      this.listFormControl[5].value -
      this.listFormControl[6].value -
      this.listFormControl[7].value -
      this.listFormControl[8].value -
      this.listFormControl[9].value -
      this.listFormControl[10].value -
      this.listFormControl[11].value -
      this.listFormControl[12].value -
      this.listFormControl[13].value -
      this.listFormControl[14].value -
      this.listFormControl[15].value -
      this.listFormControl[16].value +
      this.listFormControl[17].value +
      this.listFormControl[18].value -
      this.listFormControl[19].value +
      this.listFormControl[20].value -
      this.listFormControl[21].value -
      this.listFormControl[22].value -
      this.listFormControl[23].value -
      this.listFormControl[24].value -
      this.listFormControl[25].value +
      this.listFormControl[26].value -
      this.listFormControl[27].value -
      this.listFormControl[28].value +
      this.listFormControl[29].value -
      this.listFormControl[30].value -
      this.listFormControl[31].value +
      this.listFormControl[32].value -
      this.listFormControl[33].value +
      this.listFormControl[34].value +
      this.listFormControl[35].value -
      this.listFormControl[36].value -
      this.listFormControl[37].value -
      this.listFormControl[38].value -
      this.listFormControl[39].value -
      this.listFormControl[40].value -
      this.listFormControl[41].value -
      this.listFormControl[42].value -
      this.listFormControl[43].value +
      this.listFormControl[44].value +
      this.listFormControl[45].value +
      this.listFormControl[46].value +
      this.listFormControl[47].value +
      this.listFormControl[48].value +
      this.listFormControl[49].value -
      this.listFormControl[56].value -
      this.listFormControl[57].value;

    if (vla59 > 0) {
      this.p59.setValue(vla59);
    }

    /*else {
     if(vla60 < 0) {
       this.p59.setValue(vla60);
     } 
   }
   */

    if (vla60 > 0) {
      this.p60.setValue(vla60);
    }

    if (vla60 > 0 && vla59 > 0) {
      console.log('Error in values.');
    }

    /*else {
     if(vla59 < 0) {
       this.p60.setValue(vla59);
     }
   }
   */

    var v62 = this.listFormControl[58].value - this.listFormControl[60].value;
    if (v62 > 0) {
      this.p62.setValue(v62);
      //  this.processMyValue(this.p62);
    } else {
      this.p62.setValue(0);
      //  this.processMyValue(this.p62);
    }

    var p65 = this.listFormControl[62].value - this.listFormControl[63].value;
    if (p65 > 0) {
      this.p65.setValue(p65);
      //  this.processMyValue(this.p65);
    } else {
      this.p65.setValue(0);
      //  this.processMyValue(this.p65);
    }

    //66.	Капитални губици (64-63)>0

    var p66 = this.listFormControl[63].value - this.listFormControl[62].value;
    if (p66 > 0) {
      this.p66.setValue(p66);
      //   this.processMyValue(this.p66);
    } else {
      this.p66.setValue(0);
      //  this.processMyValue(this.p66);
    }

    //this.p67.setValue(this.p65.value);
    // this.processMyValue(this.p65);
    //68.	Остатак капиталног добитка (65-67)>0

    var p68 = this.listFormControl[64].value - this.listFormControl[66].value;
    if (p68 > 0) {
      this.p68.setValue(p68);
      //this.processMyValue(this.p68);
    } else {
      this.p68.setValue(0);
      //  this.processMyValue(this.p68);
    }
    //Пореска основица - опрезива добит (62+68)>0

    var p69 = this.listFormControl[61].value + this.listFormControl[67].value;
    if (p69 > 0) {
      this.p69.setValue(p69);
      // this.processMyValue(this.p69);
    } else {
      this.p69.setValue(0);
      // this.processMyValue(this.p69);
    }
    this.listFormControl.forEach((element) => {
      this.processMyValue(element);
    });
  }

  save2() {
    var list2send = [];
    //this.sumAll2();

    for (let i = 0; i < 69; i++) {
      console.log(i);
      list2send.push({
        inid: i + 1,
        value: +this.listFormControl[i].value.split('.').join('') || 0,
        year: this.year.value,
        bukrs: this.country.value,
        lastChangedBy: localStorage['user'] || 'unknown',
      });
    }

    this.httpC
      .post(environment.pdvURL + 'new/drugo', list2send)
      .subscribe((data) => {
        alert('Uspešna izmena.');
        window.location.reload();
      });
  }

  getExistingData(bukrs, type) {
    this.service.getPoreskiPodaci(bukrs, type).subscribe((data) => {
      this.poreskiPodaci = data;
      console.log(this.poreskiPodaci);
      this.poreskiPodaci.forEach((element) => {
        if (element.id.type == 'PORESKI_GUBICI') {
          if (element.id.yearsPeriod == '2014-2019') {
            this.pg1.setValue(element.value);
            this.processMyValue(this.pg1);
          } else if (element.id.yearsPeriod == '2015-2020') {
            this.pg2.setValue(element.value);
            this.processMyValue(this.pg2);
          } else if (element.id.yearsPeriod == '2016-2021') {
            this.pg3.setValue(element.value);
            this.processMyValue(this.pg3);
          } else if (element.id.yearsPeriod == '2017-2022') {
            this.pg4.setValue(element.value);
            this.processMyValue(this.pg4);
          } else if (element.id.yearsPeriod == '2018-2023') {
            this.pg5.setValue(element.value);
            this.processMyValue(this.pg5);
          } else if (element.id.yearsPeriod == '2019-2024') {
            this.pg6.setValue(element.value);
            this.processMyValue(this.pg6);
          } else if (element.id.yearsPeriod == '2020-2025') {
            this.pg7.setValue(element.value);
            this.processMyValue(this.pg7);
          } else if (element.id.yearsPeriod == '2021-2026') {
            this.pg8.setValue(element.value);
            this.processMyValue(this.pg8);
          }
        } else if (element.id.type == 'KAPITALNI_PORESKI_GUBICI') {
          if (element.id.yearsPeriod == '2014-2019') {
            this.kpg1.setValue(element.value);
            this.processMyValue(this.kpg1);
          } else if (element.id.yearsPeriod == '2015-2020') {
            this.kpg2.setValue(element.value);
            this.processMyValue(this.kpg2);
          } else if (element.id.yearsPeriod == '2016-2021') {
            this.kpg3.setValue(element.value);
            this.processMyValue(this.kpg3);
          } else if (element.id.yearsPeriod == '2017-2022') {
            this.kpg4.setValue(element.value);
            this.processMyValue(this.kpg4);
          } else if (element.id.yearsPeriod == '2018-2023') {
            this.kpg5.setValue(element.value);
            this.processMyValue(this.kpg5);
          } else if (element.id.yearsPeriod == '2019-2024') {
            this.kpg6.setValue(element.value);
            this.processMyValue(this.kpg6);
          } else if (element.id.yearsPeriod == '2020-2025') {
            this.kpg7.setValue(element.value);
            this.processMyValue(this.kpg7);
          } else if (element.id.yearsPeriod == '2021-2026') {
            this.kpg8.setValue(element.value);
            this.processMyValue(this.kpg8);
          }
        } else if (element.id.type == 'PORESKI_KREDITI') {
          if (element.id.yearsPeriod == '2009-2019') {
            this.pk1.setValue(element.value);
            this.processMyValue(this.pk1);
          } else if (element.id.yearsPeriod == '2010-2020') {
            this.pk2.setValue(element.value);
            this.processMyValue(this.pk2);
          } else if (element.id.yearsPeriod == '2011-2021') {
            this.pk3.setValue(element.value);
            this.processMyValue(this.pk3);
          } else if (element.id.yearsPeriod == '2012-2022') {
            this.pk4.setValue(element.value);
            this.processMyValue(this.pk4);
          } else if (element.id.yearsPeriod == '2013-2023') {
            this.pk5.setValue(element.value);
            this.processMyValue(this.pk5);
          }
        }
      });
    });
  }

  getData() {
    if (
      this.country.value != '' &&
      (this.type.value == 'PORESKI_GUBICI' ||
        this.type.value == 'KAPITALNI_PORESKI_GUBICI' ||
        this.type.value == 'PORESKI_KREDITI')
    ) {
      console.log('IF');
      this.msg = false;
      if (this.type.value == 'PORESKI_GUBICI') {
        console.log('porski gubici');
        this.showTablePoreskiGubici = true;
        this.showTable = false;
        this.showTable2 = false;
        this.showTableKapitalniPoreskiGubici = false;
        this.showTablePoreskiKrediti = false;
        this.getExistingData(this.country.value, this.type.value);
      } else if (this.type.value == 'KAPITALNI_PORESKI_GUBICI') {
        console.log('kapitalni');
        this.showTablePoreskiGubici = false;
        this.showTable = false;
        this.showTable2 = false;
        this.showTableKapitalniPoreskiGubici = true;
        this.showTablePoreskiKrediti = false;
        this.getExistingData(this.country.value, this.type.value);
      } else if (this.type.value == 'PORESKI_KREDITI') {
        console.log('PORESKI_KREDITI');
        this.showTablePoreskiGubici = false;
        this.showTable = false;
        this.showTable2 = false;
        this.showTableKapitalniPoreskiGubici = false;
        this.showTablePoreskiKrediti = true;
        this.getExistingData(this.country.value, this.type.value);
      } else if (this.type.value == 'PDV' || this.type.value == 'drugo') {
        this.msg = true;
        this.showTablePoreskiGubici = false;
        this.showTable = false;
        this.showTable2 = false;
        this.showTableKapitalniPoreskiGubici = false;
        this.showTablePoreskiKrediti = false;
      }
    } else {
      console.log('ELSE');
      if (
        this.country.value == '' ||
        this.year.value == '' ||
        this.type.value == ''
      ) {
        this.msg = true;
        return;
      }
      this.msg = false;

      if (this.type.value == 'PDV') {
        this.showTable = true;
        this.showTable2 = false;
        this.showTablePoreskiGubici = false;
        this.showTableKapitalniPoreskiGubici = false;
        this.showTablePoreskiKrediti = false;
        for (let j = 0; j < this.listFormControl.length; j++) {
          this.listFormControl[j] = new FormControl('');
        }
        this.filterChanged(this.country.value);
      } else {
        this.showTable2 = false;
        this.dobavio = false;
        this.showTable = false;
        this.showTablePoreskiGubici = false;
        this.showTableKapitalniPoreskiGubici = false;
        this.showTablePoreskiKrediti = false;
        this.httpC
          .get(
            environment.pdvURL +
              'fields/' +
              this.country.value +
              '/' +
              this.year.value
          )
          .subscribe((data) => {
            this.tempdata = data;
            if (this.tempdata.length > 0) {
              this.dobavio = true;
              this.dobavioMessage = this.tempdata[0].lastChangedBy;
              this.dobavioDatum = this.tempdata[0].lastChangedOn;
              console.log(data);
            }

            this.showTable2 = true;

            for (let j = 0; j < this.listFormControl.length; j++) {
              this.listFormControl[j].setValue('');
            }
            if (this.tempdata.length == 0) {
              this.msg = true;
              this.msgText = 'Nema podataka u ovom periodu.';
              for (let j = 0; j < this.listFormControl.length; j++) {
                this.listFormControl[j].setValue('');
              }
              return;
            }

            var temparr = [];
            for (let j = 0; j < this.tempdata.length; j++) {
              temparr[j + 1] = this.tempdata[j];
            }
            this.fillData(temparr);
          });
      }
    }
  }

  fillData(form) {
    /*
    let numberVal = parseInt(value.value).toLocaleString();
    numberVal = numberVal.split(',').join('.');
    value.setValue(numberVal);
    */
    this.p1.setValue(form[1].value);
    this.processMyValue(this.p1);
    this.p2.setValue(form[2].value);
    this.processMyValue(this.p2);
    this.p3.setValue(form[3].value);
    this.processMyValue(this.p3);
    this.p4.setValue(form[4].value);
    this.processMyValue(this.p4);
    this.p5.setValue(form[5].value);
    this.processMyValue(this.p5);
    this.p6.setValue(form[6].value);
    this.processMyValue(this.p6);
    this.p7.setValue(form[7].value);
    this.processMyValue(this.p7);
    this.p8.setValue(form[8].value);
    this.processMyValue(this.p8);
    this.p9.setValue(form[9].value);
    this.processMyValue(this.p9);
    this.p10.setValue(form[10].value);
    this.processMyValue(this.p10);
    this.p11.setValue(form[11].value);
    this.processMyValue(this.p11);
    this.p12.setValue(form[12].value);
    this.processMyValue(this.p12);
    this.p13.setValue(form[13].value);
    this.processMyValue(this.p13);
    this.p14.setValue(form[14].value);
    this.processMyValue(this.p14);
    this.p15.setValue(form[15].value);
    this.processMyValue(this.p15);
    this.p16.setValue(form[16].value);
    this.processMyValue(this.p16);
    this.p17.setValue(form[17].value);
    this.processMyValue(this.p17);
    this.p18.setValue(form[18].value);
    this.processMyValue(this.p18);
    this.p19.setValue(form[19].value);
    this.processMyValue(this.p19);
    this.p20.setValue(form[20].value);
    this.processMyValue(this.p20);
    this.p21.setValue(form[21].value);
    this.processMyValue(this.p21);
    this.p22.setValue(form[22].value);
    this.processMyValue(this.p22);
    this.p23.setValue(form[23].value);
    this.processMyValue(this.p23);
    this.p24.setValue(form[24].value);
    this.processMyValue(this.p24);
    this.p25.setValue(form[25].value);
    this.processMyValue(this.p25);
    this.p26.setValue(form[26].value);
    this.processMyValue(this.p26);
    this.p27.setValue(form[27].value);
    this.processMyValue(this.p27);
    this.p28.setValue(form[28].value);
    this.processMyValue(this.p28);
    this.p29.setValue(form[29].value);
    this.processMyValue(this.p29);
    this.p30.setValue(form[30].value);
    this.processMyValue(this.p30);
    this.p31.setValue(form[31].value);
    this.processMyValue(this.p31);
    this.p32.setValue(form[32].value);
    this.processMyValue(this.p32);
    this.p33.setValue(form[33].value);
    this.processMyValue(this.p33);
    this.p34.setValue(form[34].value);
    this.processMyValue(this.p34);
    this.p35.setValue(form[35].value);
    this.processMyValue(this.p35);
    this.p36.setValue(form[36].value);
    this.processMyValue(this.p36);
    this.p37.setValue(form[37].value);
    this.processMyValue(this.p37);
    this.p38.setValue(form[38].value);
    this.processMyValue(this.p38);
    this.p39.setValue(form[39].value);
    this.processMyValue(this.p39);
    this.p40.setValue(form[40].value);
    this.processMyValue(this.p40);
    this.p41.setValue(form[41].value);
    this.processMyValue(this.p41);
    this.p42.setValue(form[42].value);
    this.processMyValue(this.p42);
    this.p43.setValue(form[43].value);
    this.processMyValue(this.p43);
    this.p44.setValue(form[44].value);
    this.processMyValue(this.p44);
    this.p45.setValue(form[45].value);
    this.processMyValue(this.p45);
    this.p46.setValue(form[46].value);
    this.processMyValue(this.p46);
    this.p47.setValue(form[47].value);
    this.processMyValue(this.p47);
    this.p48.setValue(form[48].value);
    this.processMyValue(this.p48);
    this.p49.setValue(form[49].value);
    this.processMyValue(this.p49);
    this.p50.setValue(form[50].value);
    this.processMyValue(this.p50);
    this.p51.setValue(form[51].value);
    this.processMyValue(this.p51);
    this.p52.setValue(form[52].value);
    this.processMyValue(this.p52);
    this.p53.setValue(form[53].value);
    this.processMyValue(this.p53);
    this.p54.setValue(form[54].value);
    this.processMyValue(this.p54);
    this.p55.setValue(form[55].value);
    this.processMyValue(this.p55);
    this.p56.setValue(form[56].value);
    this.processMyValue(this.p56);
    this.p57.setValue(form[57].value);
    this.processMyValue(this.p57);
    this.p58.setValue(form[58].value);
    this.processMyValue(this.p58);
    this.p59.setValue(form[59].value);
    this.processMyValue(this.p59);
    this.p60.setValue(form[60].value);
    this.processMyValue(this.p60);
    this.p61.setValue(form[61].value);
    this.processMyValue(this.p61);
    this.p62.setValue(form[62].value);
    this.processMyValue(this.p62);
    this.p63.setValue(form[63].value);
    this.processMyValue(this.p63);
    this.p64.setValue(form[64].value);
    this.processMyValue(this.p64);
    this.p65.setValue(form[65].value);
    this.processMyValue(this.p65);
    this.p66.setValue(form[66].value);
    this.processMyValue(this.p66);
    this.p67.setValue(form[67].value);
    this.processMyValue(this.p67);
    this.p68.setValue(form[68].value);
    this.processMyValue(this.p68);
    this.p69.setValue(form[69].value);
    this.processMyValue(this.p69);
  }
  onChangeGodina(value) {
    if (value != '2022') {
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

  sumAll() {
    /*
    let numberVal = parseInt(value.value).toLocaleString();
    numberVal = numberVal.split(',').join('.');
    value.setValue(numberVal);
    */
    this.pdv3januar.setValue(
      parseInt(
        (
          +this.pdv1januar.value.split('.').join('') -
          +this.pdv2januar.value.split('.').join('')
        )
          .toString()
          .split(',')
          .join('.')
      ).toLocaleString()
    );
    this.pdv3februar.setValue(
      parseInt(
        (
          +this.pdv1februar.value.split('.').join('') -
          +this.pdv2februar.value.split('.').join('')
        )
          .toString()
          .split(',')
          .join('.')
      ).toLocaleString()
    );
    this.pdv3mart.setValue(
      parseInt(
        (
          +this.pdv1mart.value.split('.').join('') -
          +this.pdv2mart.value.split('.').join('')
        )
          .toString()
          .split(',')
          .join('.')
      ).toLocaleString()
    );
    this.pdv3april.setValue(
      parseInt(
        (
          +this.pdv1april.value.split('.').join('') -
          +this.pdv2april.value.split('.').join('')
        )
          .toString()
          .split(',')
          .join('.')
      ).toLocaleString()
    );
    this.pdv3maj.setValue(
      parseInt(
        (
          +this.pdv1maj.value.split('.').join('') -
          +this.pdv2maj.value.split('.').join('')
        )
          .toString()
          .split(',')
          .join('.')
      ).toLocaleString()
    );
    this.pdv3jun.setValue(
      parseInt(
        (
          +this.pdv1jun.value.split('.').join('') -
          +this.pdv2jun.value.split('.').join('')
        )
          .toString()
          .split(',')
          .join('.')
      ).toLocaleString()
    );
    this.pdv3jul.setValue(
      parseInt(
        (
          +this.pdv1jul.value.split('.').join('') -
          +this.pdv2jul.value.split('.').join('')
        )
          .toString()
          .split(',')
          .join('.')
      ).toLocaleString()
    );
    this.pdv3avgust.setValue(
      parseInt(
        (
          +this.pdv1avgust.value.split('.').join('') -
          +this.pdv2avgust.value.split('.').join('')
        )
          .toString()
          .split(',')
          .join('.')
      ).toLocaleString()
    );
    this.pdv3septembar.setValue(
      parseInt(
        (
          +this.pdv1septembar.value.split('.').join('') -
          +this.pdv2septembar.value.split('.').join('')
        )
          .toString()
          .split(',')
          .join('.')
      ).toLocaleString()
    );
    this.pdv3oktobar.setValue(
      parseInt(
        (
          +this.pdv1oktobar.value.split('.').join('') -
          +this.pdv2oktobar.value.split('.').join('')
        )
          .toString()
          .split(',')
          .join('.')
      ).toLocaleString()
    );
    this.pdv3novembar.setValue(
      parseInt(
        (
          +this.pdv1novembar.value.split('.').join('') -
          +this.pdv2novembar.value.split('.').join('')
        )
          .toString()
          .split(',')
          .join('.')
      ).toLocaleString()
    );
    this.pdv3decembar.setValue(
      parseInt(
        (
          +this.pdv1decembar.value.split('.').join('') -
          +this.pdv2decembar.value.split('.').join('')
        )
          .toString()
          .split(',')
          .join('.')
      ).toLocaleString()
    );
  }

  filterChanged(val) {
    this.dobavio = false;
    if (val != '404') {
      localStorage.setItem('bukrs', val);

      this.httpC
        .get(environment.pdvURL + 'getAll/' + val + '/' + this.year.value)
        .subscribe((data) => {
          this.data = data;
          if (this.data.length > 0) {
            this.dobavioMessage = this.data[0].lastChangedBy;
            this.dobavioDatum = this.data[0].lastChangedOn;
            this.dobavio = true;
          }

          if (this.data.length == 0) {
            this.pdv1januar.setValue('');
            this.pdv2januar.setValue('');
            this.pdv3januar.setValue('');

            this.pdv1februar.setValue('');
            this.pdv2februar.setValue('');
            this.pdv3februar.setValue('');

            this.pdv1mart.setValue('');
            this.pdv2mart.setValue('');
            this.pdv3mart.setValue('');

            this.pdv1april.setValue('');
            this.pdv2april.setValue('');
            this.pdv3april.setValue('');

            this.pdv1maj.setValue('');
            this.pdv2maj.setValue('');
            this.pdv3maj.setValue('');

            this.pdv1jun.setValue('');
            this.pdv2jun.setValue('');
            this.pdv3jun.setValue('');

            this.pdv1jul.setValue('');
            this.pdv2jul.setValue('');
            this.pdv3jul.setValue('');

            this.pdv1avgust.setValue('');
            this.pdv2avgust.setValue('');
            this.pdv3avgust.setValue('');

            this.pdv1septembar.setValue('');
            this.pdv2septembar.setValue('');
            this.pdv3septembar.setValue('');

            this.pdv1oktobar.setValue('');
            this.pdv2oktobar.setValue('');
            this.pdv3oktobar.setValue('');

            this.pdv1novembar.setValue('');
            this.pdv2novembar.setValue('');
            this.pdv3novembar.setValue('');

            this.pdv1decembar.setValue('');
            this.pdv2decembar.setValue('');
            this.pdv3decembar.setValue('');
            this.msg = true;
            this.msgText = 'Nema vrednosti za odabrane parametre';
            return;
          }
          this.msg = false;
          this.id1 = this.data[0].id;
          for (var key in this.data[0].listOfValues1) {
            if (this.data[0].listOfValues1.hasOwnProperty(key)) {
              if (key == 'January' && this.data[0].name == 'PDV1') {
                this.pdv1januar.setValue(this.data[0].listOfValues1[key]);
                this.processMyValue(this.pdv1januar);
              }
              if (key == 'February' && this.data[0].name == 'PDV1') {
                this.pdv1februar.setValue(this.data[0].listOfValues1[key]);
                this.processMyValue(this.pdv1februar);
              }
              if (key == 'March' && this.data[0].name == 'PDV1') {
                this.pdv1mart.setValue(this.data[0].listOfValues1[key]);
                this.processMyValue(this.pdv1mart);
              }
              if (key == 'April' && this.data[0].name == 'PDV1') {
                this.pdv1april.setValue(this.data[0].listOfValues1[key]);
                this.processMyValue(this.pdv1april);
              }
              if (key == 'May' && this.data[0].name == 'PDV1') {
                this.pdv1maj.setValue(this.data[0].listOfValues1[key]);
                this.processMyValue(this.pdv1maj);
              }
              if (key == 'June' && this.data[0].name == 'PDV1') {
                this.pdv1jun.setValue(this.data[0].listOfValues1[key]);
                this.processMyValue(this.pdv1jun);
              }
              if (key == 'July' && this.data[0].name == 'PDV1') {
                this.pdv1jul.setValue(this.data[0].listOfValues1[key]);
                this.processMyValue(this.pdv1jul);
              }
              if (key == 'August' && this.data[0].name == 'PDV1') {
                this.pdv1avgust.setValue(this.data[0].listOfValues1[key]);
                this.processMyValue(this.pdv1avgust);
              }
              if (key == 'September' && this.data[0].name == 'PDV1') {
                this.pdv1septembar.setValue(this.data[0].listOfValues1[key]);
                this.processMyValue(this.pdv1septembar);
              }
              if (key == 'October' && this.data[0].name == 'PDV1') {
                this.pdv1oktobar.setValue(this.data[0].listOfValues1[key]);
                this.processMyValue(this.pdv1oktobar);
              }
              if (key == 'November' && this.data[0].name == 'PDV1') {
                this.pdv1novembar.setValue(this.data[0].listOfValues1[key]);
                this.processMyValue(this.pdv1novembar);
              }
              if (key == 'December' && this.data[0].name == 'PDV1') {
                this.pdv1decembar.setValue(this.data[0].listOfValues1[key]);
                this.processMyValue(this.pdv1decembar);
              }
            }
          }
          this.id2 = this.data[1].id;
          for (var key in this.data[1].listOfValues2) {
            if (this.data[1].listOfValues2.hasOwnProperty(key)) {
              //PDV2

              if (key == 'January' && this.data[1].name == 'PDV2') {
                this.pdv2januar.setValue(this.data[1].listOfValues2[key]);
                this.processMyValue(this.pdv2januar);
              }
              if (key == 'February' && this.data[1].name == 'PDV2') {
                this.pdv2februar.setValue(this.data[1].listOfValues2[key]);
                this.processMyValue(this.pdv2februar);
              }
              if (key == 'March' && this.data[1].name == 'PDV2') {
                this.pdv2mart.setValue(this.data[1].listOfValues2[key]);
                this.processMyValue(this.pdv2mart);
              }
              if (key == 'April' && this.data[1].name == 'PDV2') {
                this.pdv2april.setValue(this.data[1].listOfValues2[key]);
                this.processMyValue(this.pdv2april);
              }
              if (key == 'May' && this.data[1].name == 'PDV2') {
                this.pdv2maj.setValue(this.data[1].listOfValues2[key]);
                this.processMyValue(this.pdv2maj);
              }
              if (key == 'June' && this.data[1].name == 'PDV2') {
                this.pdv2jun.setValue(this.data[1].listOfValues2[key]);
                this.processMyValue(this.pdv2jun);
              }
              if (key == 'July' && this.data[1].name == 'PDV2') {
                this.pdv2jul.setValue(this.data[1].listOfValues2[key]);
                this.processMyValue(this.pdv2jul);
              }
              if (key == 'August' && this.data[1].name == 'PDV2') {
                this.pdv2avgust.setValue(this.data[1].listOfValues2[key]);
                this.processMyValue(this.pdv2avgust);
              }
              if (key == 'September' && this.data[1].name == 'PDV2') {
                this.pdv2septembar.setValue(this.data[1].listOfValues2[key]);
                this.processMyValue(this.pdv2septembar);
              }
              if (key == 'October' && this.data[1].name == 'PDV2') {
                this.pdv2oktobar.setValue(this.data[1].listOfValues2[key]);
                this.processMyValue(this.pdv2oktobar);
              }
              if (key == 'November' && this.data[1].name == 'PDV2') {
                this.pdv2novembar.setValue(this.data[1].listOfValues2[key]);
                this.processMyValue(this.pdv2novembar);
              }
              if (key == 'December' && this.data[1].name == 'PDV2') {
                this.pdv2decembar.setValue(this.data[1].listOfValues2[key]);
                this.processMyValue(this.pdv2decembar);
              }
            }
          }
          this.sumAll();
        });
    }
  }

  save() {
    var lista1;
    var lista2;
    let myhash1: IHash = {};
    myhash1['January'] = +this.pdv1januar.value.split('.').join('') || 0;
    myhash1['February'] = +this.pdv1februar.value.split('.').join('') || 0;
    myhash1['March'] = +this.pdv1mart.value.split('.').join('') || 0;
    myhash1['April'] = +this.pdv1april.value.split('.').join('') || 0;
    myhash1['May'] = +this.pdv1maj.value.split('.').join('') || 0;
    myhash1['June'] = +this.pdv1jun.value.split('.').join('') || 0;
    myhash1['July'] = +this.pdv1jul.value.split('.').join('') || 0;
    myhash1['August'] = +this.pdv1avgust.value.split('.').join('') || 0;
    myhash1['September'] = +this.pdv1septembar.value.split('.').join('') || 0;
    myhash1['October'] = +this.pdv1oktobar.value.split('.').join('') || 0;
    myhash1['November'] = +this.pdv1novembar.value.split('.').join('') || 0;
    myhash1['December'] = +this.pdv1decembar.value.split('.').join('') || 0;

    let myhash2: IHash = {};
    myhash2['January'] = +this.pdv2januar.value.split('.').join('') || 0;
    myhash2['February'] = +this.pdv2februar.value.split('.').join('') || 0;
    myhash2['March'] = +this.pdv2mart.value.split('.').join('') || 0;
    myhash2['April'] = +this.pdv2april.value.split('.').join('') || 0;
    myhash2['May'] = +this.pdv2maj.value.split('.').join('') || 0;
    myhash2['June'] = +this.pdv2jun.value.split('.').join('') || 0;
    myhash2['July'] = +this.pdv2jul.value.split('.').join('') || 0;
    myhash2['August'] = +this.pdv2avgust.value.split('.').join('') || 0;
    myhash2['September'] = +this.pdv2septembar.value.split('.').join('') || 0;
    myhash2['October'] = +this.pdv2oktobar.value.split('.').join('') || 0;
    myhash2['November'] = +this.pdv2novembar.value.split('.').join('') || 0;
    myhash2['December'] = +this.pdv2decembar.value.split('.').join('') || 0;

    var obj2send1: any = {
      name: 'PDV1',
      id: this.id1 || 0,
      bukrs: this.bukrs,
      lastChangedBy: localStorage['user'] || 'unknown',
      listOfValues1: myhash1,
      listOfValues2: null,
      year: this.year.value,
    };

    var obj2send2: any = {
      name: 'PDV2',
      id: this.id2 || 0,
      bukrs: this.bukrs,
      lastChangedBy: localStorage['user'] || 'unknown',
      listOfValues1: null,
      listOfValues2: myhash2,
      year: this.year.value,
    };

    var list2send = [obj2send1, obj2send2];

    this.httpC
      .post(environment.pdvURL + 'new/', list2send)
      .subscribe((data) => {
        alert('Uspešna izmena');
        window.location.reload();
      });
  }

  getBukrs(id) {
    this.bukrs = id;
    console.log(this.bukrs);
  }

  saveDataForPG() {
    var objcet2send: any = [
      {
        value: this.pg1.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2014-2019',
          companyName: this.bukrs,
          type: 'PORESKI_GUBICI',
        },
      },
      {
        value: this.pg2.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2015-2020',
          companyName: this.bukrs,
          type: 'PORESKI_GUBICI',
        },
      },
      {
        value: this.pg3.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2016-2021',
          companyName: this.bukrs,
          type: 'PORESKI_GUBICI',
        },
      },
      {
        value: this.pg4.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2017-2022',
          companyName: this.bukrs,
          type: 'PORESKI_GUBICI',
        },
      },
      {
        value: this.pg5.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2018-2023',
          companyName: this.bukrs,
          type: 'PORESKI_GUBICI',
        },
      },
      {
        value: this.pg6.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2019-2024',
          companyName: this.bukrs,
          type: 'PORESKI_GUBICI',
        },
      },
      {
        value: this.pg7.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2020-2025',
          companyName: this.bukrs,
          type: 'PORESKI_GUBICI',
        },
      },
      {
        value: this.pg8.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2021-2026',
          companyName: this.bukrs,
          type: 'PORESKI_GUBICI',
        },
      },
    ];
    console.log(objcet2send);

    this.httpC
      .post(environment.pdvURL + 'new/poreskiPodaci', objcet2send)
      .subscribe((data) => {
        alert('Uspešno snimljeno.');
        window.location.reload();
      });
  }

  saveDataForKPG() {
    var objcet2send: any = [
      {
        value: this.kpg1.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2014-2019',
          companyName: this.bukrs,
          type: 'KAPITALNI_PORESKI_GUBICI',
        },
      },
      {
        value: this.kpg2.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2015-2020',
          companyName: this.bukrs,
          type: 'KAPITALNI_PORESKI_GUBICI',
        },
      },
      {
        value: this.kpg3.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2016-2021',
          companyName: this.bukrs,
          type: 'KAPITALNI_PORESKI_GUBICI',
        },
      },
      {
        value: this.kpg4.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2017-2022',
          companyName: this.bukrs,
          type: 'KAPITALNI_PORESKI_GUBICI',
        },
      },
      {
        value: this.kpg5.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2018-2023',
          companyName: this.bukrs,
          type: 'KAPITALNI_PORESKI_GUBICI',
        },
      },
      {
        value: this.kpg6.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2019-2024',
          companyName: this.bukrs,
          type: 'KAPITALNI_PORESKI_GUBICI',
        },
      },
      {
        value: this.kpg7.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2020-2025',
          companyName: this.bukrs,
          type: 'KAPITALNI_PORESKI_GUBICI',
        },
      },
      {
        value: this.kpg8.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2021-2026',
          companyName: this.bukrs,
          type: 'KAPITALNI_PORESKI_GUBICI',
        },
      },
    ];

    this.httpC
      .post(environment.pdvURL + 'new/poreskiPodaci', objcet2send)
      .subscribe((data) => {
        alert('Uspešno snimljeno.');
        window.location.reload();
      });
  }

  saveDataForPK() {
    var objcet2send: any = [
      {
        value: this.pk1.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2009-2019',
          companyName: this.bukrs,
          type: 'PORESKI_KREDITI',
        },
      },
      {
        value: this.pk2.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2010-2020',
          companyName: this.bukrs,
          type: 'PORESKI_KREDITI',
        },
      },
      {
        value: this.pk3.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2011-2021',
          companyName: this.bukrs,
          type: 'PORESKI_KREDITI',
        },
      },
      {
        value: this.pk4.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2012-2022',
          companyName: this.bukrs,
          type: 'PORESKI_KREDITI',
        },
      },
      {
        value: this.pk5.value.split('.').join('') || 0,
        lastChangedBy: localStorage['user'] || 'unknown',
        id: {
          yearsPeriod: '2013-2023',
          companyName: this.bukrs,
          type: 'PORESKI_KREDITI',
        },
      },
    ];

    this.httpC
      .post(environment.pdvURL + 'new/poreskiPodaci', objcet2send)
      .subscribe((data) => {
        alert('Uspešno snimljeno.');
        window.location.reload();
      });
  }
}
