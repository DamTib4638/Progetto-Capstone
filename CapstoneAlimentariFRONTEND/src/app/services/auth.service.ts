import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

    autentica = (email: string, password: string) : boolean => {
        var responso = (email === 'direttore.direttore@gmail.com' && password === 'direttore') ? true : false;
        if (responso) {
            sessionStorage.setItem("Dipendente", email);
        }
        return responso;
    }

    dipendente = (): string => (sessionStorage.getItem("Dipendente") === 'direttore.direttore@gmail.com') ? "Damiano" : "Dipendente Sconosciuto";

    dipLogged = (): boolean => (sessionStorage.getItem("Dipendente")) ? true : false;

    clearDipendente = (): void => (sessionStorage.removeItem("Dipendente"));

    clearAll = (): void => (sessionStorage.clear());

}
