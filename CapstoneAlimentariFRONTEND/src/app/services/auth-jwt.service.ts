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
        'Authorization': 'Bearer' + localStorage.getItem("token")
    });

    private baseUrl: string = "https://alimentaribe.osc-fr1.scalingo.io/api/auth/alimentari";
    private baseUrlDip: string = "https://alimentaribe.osc-fr1.scalingo.io/api/alimentari/dipendenti";

    isLoggedIn: boolean = false;

    constructor(private http: HttpClient, private router: Router) {
        const storedDip = localStorage.getItem("dipendenteCorrente");
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
                localStorage.setItem("dipendenteCorrente", JSON.stringify(dip));
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
        let dipInTurno = localStorage.getItem("dipendenteCorrente");
        let dipInTurnoParse = dipInTurno ? JSON.parse(dipInTurno) : null;
        if (dipInTurnoParse != null) {
            return dipInTurnoParse.email;
        }
    }

    getToken() {
        const token = JSON.parse(localStorage.getItem("dipendenteCorrente")!);
        return token.accessToken;
    }

    logout(): void {
        localStorage.removeItem("dipendenteCorrente");
        this.dipendenteCorrenteSubject.next(null);
        this.updateAuthStatus(false);
        this.isLoggedIn = false;
    }

    isAuthenticated() {
        const storedDip = localStorage.getItem("dipendenteCorrente");
        console.log(storedDip);
        if (!storedDip) {
            this.router.navigate(['/login']);
        }
    }

    isDipLoggedIn() {
        let dip = localStorage.getItem("dipendenteCorrente")
        // console.log(dip);
        if (dip != null) {
            return true;
        }
        return false;
    }

    // getDipendenteByEmail() {
    //     if (localStorage.getItem("dipendenteCorrente") != null) {
    //         let dipInTurno = localStorage.getItem("dipendenteCorrente");
    //         let dipInTurnoParse = dipInTurno ? JSON.parse(dipInTurno) : '';
    //         return this.http.get<Dipendente>(this.baseUrlDip + '/' +dipInTurnoParse.email).pipe(
    //             catchError((err) => {
    //                 return throwError(this.getMessaggioErrore(err.stato));
    //             })
    //         );
    //         // if (dipInTurnoParse.email.includes('direttore')) {
    //         //     return 'Damiano Tiberi'
    //         // } else if (dipInTurnoParse.email.includes('cassiere')) {
    //         //     return 'Stefano Gavioli'
    //         // } else if (dipInTurnoParse.email.includes('banco')) {
    //         //     return 'Manuel Ferrucci'
    //         // } else if (dipInTurnoParse.email.includes('scaffale')) {
    //         //     return 'Francesco Pastore'
    //         // } else {
    //         //     return 'Stefano Violi'
    //         // }
    //     }
    //     let dipendente: Dipendente = {
    //         nome: '',
    //         cognome: '',
    //         eta: 0,
    //         codFis: '',
    //         telefono: '',
    //         indirizzo: '',
    //         citta: '',
    //         email: '',
    //         token: '',
    //         mansioni: []
    //     }
    //     return dipendente;
    // }

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
