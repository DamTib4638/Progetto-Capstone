import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Dipendente } from 'src/app/entity/dipendente.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { DipendenteService } from 'src/app/services/dipendente.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

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

  constructor(private authServ: AuthJwtService, private router: Router, private dipServ: DipendenteService) { }

  ngOnInit(): void {
    this.authServ.isAuthenticated();
    this.emailCorrente = this.authServ.getEmailCorrente();
    console.log(this.emailCorrente);
    if (this.emailCorrente != null) {
        this.dipServ.getDipendenteByEmail(this.emailCorrente).subscribe((ris) => {
            this.dipendente = ris;
            console.log(this.dipendente);
            this.ruolo = this.dipendente.mansioni[0].tipoMansione;
            console.log(this.ruolo);
            if (!(this.ruolo.includes('DIRETTORE'))) {
                console.log(this.ruolo);
                this.router.navigate(['/forbidden']);
            }
        })
    }

  }

  registra = (): void => {

  }

}
