import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Scarico } from '../entity/scarico.interface';
import { catchError, throwError } from 'rxjs';
import { Fornitore } from '../entity/fornitore.interface';
import { Ordinazione } from '../entity/ordinazione.interface';
import { DettaglioScarico } from '../entity/dettaglio-scarico.interface';

@Injectable({
  providedIn: 'root'
})
export class ScaricoService {

    baseUrlScarico: string = 'https://alimentaribe.osc-fr1.scalingo.io/api/alimentari/scarichi';
    baseUrlFornitore: string = 'https://alimentaribe.osc-fr1.scalingo.io/api/alimentari/fornitori';
    baseUrlDettaglioScarico: string = 'https://alimentaribe.osc-fr1.scalingo.io/api/alimentari/dettaglioScarichi'

    dipInTurno = localStorage.getItem("dipendenteCorrente");
    dipInTurnoParse = this.dipInTurno ? JSON.parse(this.dipInTurno) : '';
    accesso: HttpHeaders = new HttpHeaders({
        'Authorization': 'Bearer '+this.dipInTurnoParse.accessToken
    })

  constructor(private http: HttpClient, private router: Router) { }

    insertScarico(ord: Ordinazione) {
        console.log(ord);
        return this.http.post<Scarico>(this.baseUrlScarico, ord, {headers: this.accesso}).subscribe(() => {
            this.indietro();
        })
    }

    registraScarico(s: Scarico) {
        console.log(s);
        return this.http.put<Scarico>(this.baseUrlScarico + '/gestione/' + s.idScarico, s, {headers: this.accesso}).subscribe(() => {
            this.indietro();
        })
    }

    eliminaScarico(s: Scarico) {
        console.log(s);
        return this.http.delete<Boolean>(this.baseUrlScarico + '/gestione/' + s.idScarico, {headers: this.accesso}).subscribe(() => {
            this.indietro();
        })
    }

    getAllScarichi() {
        return this.http.get<Scarico[]>(this.baseUrlScarico, {headers: this.accesso}).pipe(
            catchError((err) => {
                return throwError(this.getMessaggioErrore(err.stato));
            })
        );
    }

    getScaricoById(id: number) {
        return this.http.get<Scarico>(this.baseUrlScarico + "/" + id, {headers: this.accesso}).pipe(
            catchError((err) => {
                return throwError(this.getMessaggioErrore(err.stato));
            })
        );
    }

    getDettagliScarichiByIdScarico(id: number) {
        return this.http.get<DettaglioScarico[]>(this.baseUrlDettaglioScarico + '/lista/' + id, {headers: this.accesso}).pipe(
            catchError((err) => {
                return throwError(this.getMessaggioErrore(err.stato));
            })
        );
    }

    getAllFornitori() {
        return this.http.get<Fornitore[]>(this.baseUrlFornitore, {headers: this.accesso}).pipe(
            catchError((err) => {
                return throwError(this.getMessaggioErrore(err.stato));
            })
        );
    }

    getFornitoreById(id: number) {
        return this.http.get<Fornitore>(this.baseUrlFornitore + '/' + id, {headers: this.accesso}).pipe(
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
        window.location.href = '#/scarichi';
        // this.router.navigate(['/scarichi']);
    }

}
