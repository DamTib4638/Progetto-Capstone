import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject, catchError, map, throwError } from 'rxjs';
import { DipendenteLoggato } from '../entity/dipendenteLoggato.interface';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Dipendente } from '../entity/dipendente.interface';
import { LoginDto } from '../entity/login-dto.interface';

@Injectable({
    providedIn: 'root'
})
export class AuthJwtService {

    // il suo User è il mio DipendenteLoggato. Il suo UserSignUp è il mio Dipendente
    private dipendenteCorrenteSubject: BehaviorSubject<DipendenteLoggato | null>;
    public dipendenteCorrente: Observable<DipendenteLoggato | null>;

    private authStatusSource = new Subject<boolean>();
    public authStatus$ = this.authStatusSource.asObservable();

    private httpOptions: HttpHeaders = new HttpHeaders({
        'Authorization': 'Bearer' + sessionStorage.getItem("token")
    });

    private baseUrl: string = "https://alimentaribe.osc-fr1.scalingo.io/api/auth/alimentari";
    private baseUrlDip: string = "https://alimentaribe.osc-fr1.scalingo.io/api/alimentari/dipendenti";

    isLoggedIn: boolean = false;

    constructor(private http: HttpClient, private router: Router) {
        const storedDip = sessionStorage.getItem("dipendenteCorrente");
        this.dipendenteCorrenteSubject = new BehaviorSubject<DipendenteLoggato | null>(
            storedDip ? JSON.parse(storedDip) : null
        );
        this.dipendenteCorrente = this.dipendenteCorrenteSubject.asObservable();
    }

    private authSubject = new BehaviorSubject<any>(null);

    dipendenteLoggato$ = this.authSubject.asObservable();
    isLoggedIn$ = this.dipendenteLoggato$.pipe(map((dip) => !!dip));

    public get dipendenteCorrenteValue(): DipendenteLoggato | null {
        return this.dipendenteCorrenteSubject.value;
    }

    updateAuthStatus(status: boolean): void {
        this.authStatusSource.next(status);
    }

    login(data: LoginDto): Observable<DipendenteLoggato> {
        return this.http.post<DipendenteLoggato>(this.baseUrl + '/login', data).pipe(
            map((dip) => {
                sessionStorage.setItem("dipendenteCorrente", JSON.stringify(dip));
                this.dipendenteCorrenteSubject.next(dip);
                this.updateAuthStatus(true);
                this.isDipLoggedIn();
                return dip;
            })
        );
    }

    creaDipendente (dip: Dipendente) {
        return this.http.post(this.baseUrl + "/register", dip, {headers: this.httpOptions, responseType:'text'});
    }

    getEmailCorrente() {
        let dipInTurno = sessionStorage.getItem("dipendenteCorrente");
        let dipInTurnoParse = dipInTurno ? JSON.parse(dipInTurno) : null;
        if (dipInTurnoParse != null) {
            return dipInTurnoParse.email;
        }
    }

    getToken() {
        const token = JSON.parse(sessionStorage.getItem("dipendenteCorrente")!);
        return token.accessToken;
    }

    logout(): void {
        sessionStorage.removeItem("dipendenteCorrente");
        this.dipendenteCorrenteSubject.next(null);
        this.updateAuthStatus(false);
        this.isLoggedIn = false;
    }

    isAuthenticated() {
        const storedDip = sessionStorage.getItem("dipendenteCorrente");
        if (!storedDip) {
            this.router.navigate(['/login']);
        }
    }

    isDipLoggedIn() {
        let dip = sessionStorage.getItem("dipendenteCorrente")
        if (dip != null) {
            return true;
        }
        return false;
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

}
