import { Injectable } from '@angular/core';
import { Dipendente } from '../entity/dipendente.interface';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DipendenteService {

    baseUrl: string = "http://localhost:8080/api/alimentari/dipendenti";

  constructor(private http: HttpClient, private router: Router) { }

    getDipendenteByEmail(email: string) {
        return this.http.get<Dipendente>(this.baseUrl + "/email/" + email).pipe(
            catchError((err) => {
                return throwError(this.getMessaggioErrore(err.stato));
            })
        );
    }

    getDipendenteById(id: number) {
        return this.http.get<Dipendente>(this.baseUrl + "/id/" + id).pipe(
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
        this.router.navigate(['/welcome']);
    }

}
