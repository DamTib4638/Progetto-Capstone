import { Injectable } from '@angular/core';
import { Dipendente } from '../entity/dipendente.interface';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { Contratto } from '../entity/contratto.interface';
import { DipendenteDto } from '../entity/dipendente-dto.interface';

@Injectable({
  providedIn: 'root'
})
export class DipendenteService {

    baseUrl: string = "https://alimentaribe.osc-fr1.scalingo.io/api/alimentari/dipendenti";
    registerUrl: string = 'https://alimentaribe.osc-fr1.scalingo.io/api/auth/alimentari/register';
    baseUrlContratti: string = 'https://alimentaribe.osc-fr1.scalingo.io/api/alimentari/contratti';

    dipInTurno = localStorage.getItem("dipendenteCorrente");
    dipInTurnoParse = this.dipInTurno ? JSON.parse(this.dipInTurno) : '';
    accesso: HttpHeaders = new HttpHeaders({
        'Authorization': 'Bearer '+this.dipInTurnoParse.accessToken
    })

  constructor(private http: HttpClient, private router: Router) { }

    getDipendenteByEmail(email: string) {
        return this.http.get<Dipendente>(this.baseUrl + "/email/" + email).pipe(
            catchError((err) => {
                return throwError(this.getMessaggioErrore(err.stato));
            })
        );
    }

    getDipendenteById(id: number) {
        return this.http.get<Dipendente>(this.baseUrl + "/id/" + id, {headers: this.accesso}).pipe(
            catchError((err) => {
                return throwError(this.getMessaggioErrore(err.stato));
            })
        );
    }

    getAllDipendenti() {
        return this.http.get<Dipendente[]>(this.baseUrl, {headers: this.accesso}).pipe(
            catchError((err) => {
                return throwError(this.getMessaggioErrore(err.stato));
            })
        );
    }

    getAllContratti() {
        return this.http.get<Contratto[]>(this.baseUrlContratti, {headers: this.accesso}).pipe(
            catchError((err) => {
                return throwError(this.getMessaggioErrore(err.stato));
            })
        );
    }

    registerDipendente(d: DipendenteDto) {
        return this.http.post<DipendenteDto>(this.registerUrl, d, {headers: this.accesso}).subscribe(() => {
            this.indietro();
        })
    }

    editDipendente(d: Dipendente) {
        return this.http.put<Dipendente>(this.baseUrl + '/' + d.idDipendente, d, {headers: this.accesso}).subscribe(() => {
            this.indietro();
        })
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
        window.location.href = 'dipendenti';
        // this.router.navigate(['/welcome']);
    }

}
