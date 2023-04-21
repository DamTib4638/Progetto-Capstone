import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { DipendenteService } from 'src/app/services/dipendente.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

    email: string = '';
    mansioni = [];

  constructor(private authServ: AuthJwtService, private route: Router, private dipServ: DipendenteService) { }

  ngOnInit(): void {
    this.authServ.isAuthenticated();
    const dipLog = JSON.parse(localStorage.getItem("dipendenteCorrente")!);
    this.email = dipLog.email;
    this.dipServ.getDipendenteByEmail(this.email).subscribe((dip) => {
        // this.mansioni = dip.mansioni;
        // fai controllo sul fatto che deve essere il direttore a poter restare nella pagina di registrazione
    })

  }

  registra = (): void => {

  }

}
