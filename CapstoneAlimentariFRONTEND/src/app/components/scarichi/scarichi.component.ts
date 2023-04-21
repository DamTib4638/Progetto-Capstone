import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Dipendente } from 'src/app/entity/dipendente.interface';
import { Scarico } from 'src/app/entity/scarico.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { DipendenteService } from 'src/app/services/dipendente.service';
import { ScaricoService } from 'src/app/services/scarico.service';

@Component({
  selector: 'app-scarichi',
  templateUrl: './scarichi.component.html',
  styleUrls: ['./scarichi.component.scss']
})
export class ScarichiComponent implements OnInit {

    listaScarichi: Scarico[] = [];

    confermaCanc: boolean = false;
    confermaReg: boolean = false;
    codiceVerificaCanc: number = 0;
    codiceVerificaReg: number = 0;
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
    };

    scarico: Scarico = {
        idScarico: 0,
        dataPrenScar: null,
        dataRegScar: null,
        fornitore: null,
        dipendente: null
    }

  constructor(public authServ: AuthJwtService,
                private dipServ: DipendenteService,
                private router: Router,
                private scarServ: ScaricoService) { }

  ngOnInit(): void {
    this.authServ.isAuthenticated();
    this.emailCorrente = this.authServ.getEmailCorrente();
    if (this.emailCorrente != null) {
        this.dipServ.getDipendenteByEmail(this.emailCorrente).subscribe((ris) => {
            this.dipendente = ris;
            this.ruolo = this.dipendente.mansioni[0].tipoMansione;
        })
    }
    this.visualizzaListaScarichi();
  }

  visualizzaListaScarichi() {
    this.scarServ.getAllScarichi().subscribe((response) => {
        console.log(response);
        this.listaScarichi = response;
    })
  }

  nuovoScarico() {
    this.router.navigate(['/inserisci_scarico']);
  }

  cancellaScarico(id: number) {
    this.confermaCanc = true;
    this.codiceVerificaCanc = id;
  }

  registraScarico(id: number) {
    this.confermaReg = true;
    this.codiceVerificaReg = id;
  }

  confermaCancellazione(id: number) {
    if (id>0) {
        this.scarServ.getScaricoById(id).subscribe((risp) => {
            this.scarico = risp;
            this.scarServ.eliminaScarico(this.scarico);
        })
    }
  }

  confermaRegistrazione(id: number) {
    if (id>0) {
        this.scarServ.getScaricoById(id).subscribe((risp) => {
            this.scarico = risp;
            this.scarServ.registraScarico(this.scarico);
        })
    }
  }

  annullaCancellazione() {
    this.confermaCanc = false;
    this.codiceVerificaCanc = 0;
  }

  annullaRegistrazione() {
    this.confermaReg = false;
    this.codiceVerificaReg = 0;
  }

}
