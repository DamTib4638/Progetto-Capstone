import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Dipendente } from 'src/app/entity/dipendente.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { DipendenteService } from 'src/app/services/dipendente.service';

@Component({
  selector: 'app-dipendenti',
  templateUrl: './dipendenti.component.html',
  styleUrls: ['./dipendenti.component.scss']
})
export class DipendentiComponent implements OnInit {

    listaDipendenti: Dipendente[] = [];

    confermaCanc: boolean = false;
    codiceVerificaCanc: number = 0;
    ruolo: string = '';
    emailCorrente: string = '';

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
    }

  constructor(public authServ: AuthJwtService,
                private dipServ: DipendenteService,
                private router: Router) { }

  ngOnInit(): void {
    this.authServ.isAuthenticated();
    this.emailCorrente = this.authServ.getEmailCorrente();
    if (this.emailCorrente != null) {
        this.dipServ.getDipendenteByEmail(this.emailCorrente).subscribe((ris) => {
            this.dipendente = ris;
            this.ruolo = this.dipendente.mansioni[0].tipoMansione;
            console.log(this.ruolo);
                if (!(this.ruolo.includes('DIRETTORE'))) {
                    console.log(this.ruolo);
                    this.router.navigate(['/forbidden']);
                } else {
                    this.visualizzaListaDipendenti();
                }
        })
    }
  }

  visualizzaListaDipendenti() {
    this.dipServ.getAllDipendenti().subscribe((response) => {
        console.log(response);
        this.listaDipendenti = response;
    })
  }

  eliminaDipendente(id: number) {
    this.confermaCanc = true;
    this.codiceVerificaCanc = id;
  }

  confermaEliminazione(id: number) {
    if (id>0) {
        this.dipServ.getDipendenteById(id).subscribe((risp) => {
            this.dipendente = risp;
            // this.dipServ.eliminaDipendente(this.dipendente);
        })
    }
  }

  annullaEliminazione() {
    this.confermaCanc = false;
    this.codiceVerificaCanc = 0;
  }

  nuovoDipendente() {
    this.router.navigate(['/register']);
  }

}
