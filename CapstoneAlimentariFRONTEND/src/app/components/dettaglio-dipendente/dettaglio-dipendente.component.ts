import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Dipendente } from 'src/app/entity/dipendente.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { DipendenteService } from 'src/app/services/dipendente.service';

@Component({
    selector: 'app-dettaglio-dipendente',
    templateUrl: './dettaglio-dipendente.component.html',
    styleUrls: ['./dettaglio-dipendente.component.scss']
})
export class DettaglioDipendenteComponent implements OnInit {

    listaDipendenti: Dipendente[] = [];

    emailCorrente: string = '';
    ruolo: string = '';
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

    constructor(private ar: ActivatedRoute, private authServ: AuthJwtService, private dipServ: DipendenteService, private router: Router) { }

    ngOnInit(): void {
        this.authServ.isAuthenticated();
        this.emailCorrente = this.authServ.getEmailCorrente();
        if (this.emailCorrente != null) {
            this.dipServ.getDipendenteByEmail(this.emailCorrente).subscribe((ris) => {
                this.dipendente = ris;
                this.ruolo = this.dipendente.mansioni[0].tipoMansione;
                if (!(this.ruolo.includes('DIRETTORE'))) {
                    this.router.navigate(['/forbidden']);
                } else {
                    let id: number = this.ar.snapshot.params['id'];
                    this.dipServ.getAllDipendenti().subscribe((risp) => {
                        this.listaDipendenti = risp;
                        for (let dip of this.listaDipendenti) {
                            if (Number(dip.idDipendente) == id) {
                                this.dipendente = dip;
                                this.ruolo = this.dipendente.mansioni[0].tipoMansione;
                            }
                        }
                    });
                }
            })
        }
    }

    tornaIndietro() {
        this.dipServ.indietro();
    }

}
