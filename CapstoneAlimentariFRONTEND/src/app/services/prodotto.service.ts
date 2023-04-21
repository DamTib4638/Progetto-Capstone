import { Injectable } from '@angular/core';
import { Prodotto } from '../entity/prodotto.interface';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { throwError, catchError } from 'rxjs';
import { Router } from '@angular/router';
import { Scaffale } from '../entity/scaffale.interface';

@Injectable({
    providedIn: 'root',
})
export class ProdottoService {

    baseUrlProdotti: string = "http://localhost:8080/api/alimentari/prodotti";
    baseUrlScaffali: string = "http://localhost:8080/api/alimentari/scaffali";
    dipInTurno = localStorage.getItem("dipendenteCorrente");
    dipInTurnoParse = this.dipInTurno ? JSON.parse(this.dipInTurno) : '';
    accesso: HttpHeaders = new HttpHeaders({
        'Authorization': 'Bearer '+this.dipInTurnoParse.accessToken
    })

    constructor(private http: HttpClient, private router: Router) { }

    getAllProdotti() {
        console.log(this.dipInTurnoParse.accessToken);
        return this.http.get<Prodotto[]>(this.baseUrlProdotti, {headers: this.accesso}).pipe(
            catchError((err) => {
                return throwError(this.getMessaggioErrore(err.stato));
            })
        );
    }

    getAllScaffali() {
        return this.http.get<Scaffale[]>(this.baseUrlScaffali).pipe(
            catchError((err) => {
                return throwError(this.getMessaggioErrore(err.stato));
            })
        );
    }

    getScaffaleById(id: number) {
        return this.http.get<Scaffale>(this.baseUrlScaffali + "/" + id).pipe(
            catchError((err) => {
                return throwError(this.getMessaggioErrore(err.stato));
            })
        );
    }

    insertProdotto(p: Prodotto) {
        return this.http.post<Prodotto>(this.baseUrlProdotti, p, {headers: this.accesso}).subscribe(() => {
            this.indietro();
        })
    }

    editProdotto(p: Prodotto) {
        return this.http.put<Prodotto>(this.baseUrlProdotti + '/' + p.idProdotto, p).subscribe(() => {
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
        this.router.navigate(['/prodotti']);
    }

}
