import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Ordinazione } from '../entity/ordinazione.interface';
import { Transazione } from '../entity/transazione.interface';
import { catchError, throwError } from 'rxjs';
import { Cassa } from '../entity/cassa.interface';
import { DettaglioTransazione } from '../entity/dettaglio-transazione.interface';

@Injectable({
  providedIn: 'root'
})
export class TransazioneService {

    baseUrlTransazione: string = 'https://alimentaribe.osc-fr1.scalingo.io/api/alimentari/transazioni';
    baseUrlCassa: string = 'https://alimentaribe.osc-fr1.scalingo.io/api/alimentari/casse';
    baseUrlDettaglioTransazione: string = 'https://alimentaribe.osc-fr1.scalingo.io/api/alimentari/dettaglioTransazioni';

    dipInTurno = localStorage.getItem("dipendenteCorrente");
    dipInTurnoParse = this.dipInTurno ? JSON.parse(this.dipInTurno) : '';
    accesso: HttpHeaders = new HttpHeaders({
        'Authorization': 'Bearer '+this.dipInTurnoParse.accessToken
    })

  constructor(private http: HttpClient, private router: Router) { }

    insertTransazione(ord: Ordinazione) {
        return this.http.post<Transazione>(this.baseUrlTransazione, ord, {headers: this.accesso}).subscribe(() => {
            this.indietro();
        })
    }

    pagaTransazione(t: Transazione) {
        return this.http.put<Transazione>(this.baseUrlTransazione + '/gestione/' + t.idTransazione, t, {headers: this.accesso}).subscribe(() => {
            this.indietro();
        })
    }

    eliminaTransazione(t: Transazione) {
        return this.http.delete<Boolean>(this.baseUrlTransazione + '/gestione/' + t.idTransazione, {headers: this.accesso}).subscribe(() => {
            this.indietro();
        })
    }

    getAllTransazioni() {
        return this.http.get<Transazione[]>(this.baseUrlTransazione, {headers: this.accesso}).pipe(
            catchError((err) => {
                return throwError(this.getMessaggioErrore(err.stato));
            })
        );
    }

    getTransazioneById(id: number) {
        return this.http.get<Transazione>(this.baseUrlTransazione + '/' + id, {headers: this.accesso}).pipe(
            catchError((err) => {
                return throwError(this.getMessaggioErrore(err.stato));
            })
        );
    }

    getDettagliTransazioniByIdTransazione(id: number) {
        return this.http.get<DettaglioTransazione[]>(this.baseUrlDettaglioTransazione + '/lista/' + id, {headers: this.accesso}).pipe(
            catchError((err) => {
                return throwError(this.getMessaggioErrore(err.stato));
            })
        );
    }

    getAllCasse() {
        return this.http.get<Cassa[]>(this.baseUrlCassa, {headers: this.accesso}).pipe(
            catchError((err) => {
                return throwError(this.getMessaggioErrore(err.stato));
            })
        );
    }

    getCassaById(id: number) {
        return this.http.get<Cassa>(this.baseUrlCassa + '/' + id, {headers: this.accesso}).pipe(
            catchError((err) => {
                return throwError(this.getMessaggioErrore(err.stato));
            })
        );
    }

    getMessaggioErrore(stato: number) {
        let messaggio: string = '';
        if (199 < stato && stato < 299) {
            messaggio = 'TUTTO OK';
        } else if (stato < 399) {
            messaggio = 'PROBLEMI DI COMUNICAZIONE DATI';
        } else {
            messaggio = 'PROBLEMI GENERICI';
        }
    }

    indietro() {
        window.location.href = 'transazioni';
    }

}
