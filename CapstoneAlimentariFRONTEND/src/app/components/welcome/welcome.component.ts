import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Dipendente } from 'src/app/entity/dipendente.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { DipendenteService } from 'src/app/services/dipendente.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.scss']
})
export class WelcomeComponent implements OnInit {

    emailCorrente: string = '';
    ruolo: string = '';
    dipendente: Dipendente = {
        idDipendente: 0,
        nome: '',
        cognome: '',
        eta: 0,
        codFis: '',
        telefono: '',
        indirizzo: '',
        citta: '',
        email: '',
        password: '',
        mansioni: []
    };

  constructor(private router: Router, private authServ: AuthJwtService, private dipServ: DipendenteService) { }

  ngOnInit(): void {
    this.authServ.isAuthenticated();
    this.emailCorrente = this.authServ.getEmailCorrente();
    if (this.emailCorrente != null) {
        this.dipServ.getDipendenteByEmail(this.emailCorrente).subscribe((ris) => {
            this.dipendente = ris;
            this.ruolo = this.dipendente.mansioni[0].tipoMansione;
        })
    }
  }
}
