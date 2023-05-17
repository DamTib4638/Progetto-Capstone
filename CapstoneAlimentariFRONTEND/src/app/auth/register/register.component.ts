import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Contratto } from 'src/app/entity/contratto.interface';
import { DipendenteDto } from 'src/app/entity/dipendente-dto.interface';
import { Dipendente } from 'src/app/entity/dipendente.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { DipendenteService } from 'src/app/services/dipendente.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

    emailCorrente: string = '';
    ruolo: string = '';

    listaContratti: Contratto[] = [];

    dipendente: Dipendente = {
        idDipendente: null,
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

    newDip: DipendenteDto = {
        nome: '',
        cognome: '',
        eta: 0,
        codFis: '',
        telefono: '',
        indirizzo: '',
        citta: '',
        email: '',
        password: '',
        mansioni: [],
        contratto: '',
        dataInizioContratto: null
    }

  constructor(private authServ: AuthJwtService, private router: Router, private dipServ: DipendenteService) { }

  ngOnInit(): void {
    this.authServ.isAuthenticated();
    this.emailCorrente = this.authServ.getEmailCorrente();
    if (this.emailCorrente != null) {
        this.dipServ.getDipendenteByEmail(this.emailCorrente).subscribe((ris) => {
            this.dipendente = ris;
            this.ruolo = this.dipendente.mansioni[0].tipoMansione;
            if (!(this.ruolo.includes('DIRETTORE'))) {
                this.router.navigate(['/forbidden']);
            }
        })
    }

  }

  visualizzaListaContratti() {
    this.dipServ.getAllContratti().subscribe((risp) => {
        this.listaContratti = risp;
    })
  }

  insert(form: NgForm) {
    if(form.valid) {
        this.newDip.nome = form.value.nome;
        this.newDip.cognome = form.value.cognome;
        this.newDip.eta = form.value.eta;
        this.newDip.codFis = form.value.codFis;
        this.newDip.telefono = form.value.telefono;
        this.newDip.indirizzo = form.value.indirizzo;
        this.newDip.citta = form.value.citta;
        this.newDip.email = form.value.email;
        this.newDip.password = form.value.password;
        this.newDip.mansioni.push(form.value.mansioni);
        this.newDip.contratto = form.value.contratto;
        this.newDip.dataInizioContratto = form.value.dataInizioContratto;
        this.dipServ.registerDipendente(this.newDip);
    }
  }

  tornaIndietro() {
    this.dipServ.indietro();
  }

}
