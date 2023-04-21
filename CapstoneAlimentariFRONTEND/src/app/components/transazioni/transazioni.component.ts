import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Dipendente } from 'src/app/entity/dipendente.interface';
import { Transazione } from 'src/app/entity/transazione.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { DipendenteService } from 'src/app/services/dipendente.service';
import { TransazioneService } from 'src/app/services/transazione.service';

@Component({
  selector: 'app-transazioni',
  templateUrl: './transazioni.component.html',
  styleUrls: ['./transazioni.component.scss']
})
export class TransazioniComponent implements OnInit {

    listaTransazioni: Transazione[] = [];

    confermaCanc: boolean = false;
    codiceVerificaCanc: number = 0;
    confermaPagam: boolean = false;
    codiceVerificaPagam: number = 0;
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

    transazione: Transazione = {
        idTransazione: 0,
        dataEmissioneTransazione: null,
        dataPagamentoTransazione: null,
        cassa: null,
        dipendente: null
    }

  constructor(public authServ: AuthJwtService,
                private dipServ: DipendenteService,
                private router: Router,
                private tranServ: TransazioneService) { }

  ngOnInit(): void {
    this.authServ.isAuthenticated();
    this.emailCorrente = this.authServ.getEmailCorrente();
    if(this.emailCorrente != null) {
        this.dipServ.getDipendenteByEmail(this.emailCorrente).subscribe((ris) => {
            this.dipendente = ris;
            this.ruolo = this.dipendente.mansioni[0].tipoMansione;
        })
    }
    this.visualizzaListaTransazioni();
  }

  visualizzaListaTransazioni() {
    this.tranServ.getAllTransazioni().subscribe((response) => {
        this.listaTransazioni = response;
    })
  }

  nuovaTransazione() {
    this.router.navigate(['/inserisci_transazione'])
  }

  cancellaTransazione(id: number) {
    this.confermaCanc = true;
    this.codiceVerificaCanc = id;
  }

  pagaTransazione(id: number) {
    this.confermaPagam = true;
    this.codiceVerificaPagam = id;
  }

  confermaCancellazione(id: number) {
    if (id>0) {
        this.tranServ.getTransazioneById(id).subscribe((risp) => {
            this.transazione = risp;
            this.tranServ.eliminaTransazione(this.transazione);
        })
    }
  }

  confermaPagamento(id: number) {
    if (id>0) {
        this.tranServ.getTransazioneById(id).subscribe((risp) => {
            this.transazione = risp;
            this.tranServ.pagaTransazione(this.transazione);
        })
    }
  }

  annullaCancellazione() {
    this.confermaCanc = false;
    this.codiceVerificaCanc = 0;
  }

  annullaPagamento() {
    this.confermaPagam = false;
    this.codiceVerificaPagam = 0;
  }

}
