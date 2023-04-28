import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DettaglioScarico } from 'src/app/entity/dettaglio-scarico.interface';
import { Dipendente } from 'src/app/entity/dipendente.interface';
import { Fornitore } from 'src/app/entity/fornitore.interface';
import { Prodotto } from 'src/app/entity/prodotto.interface';
import { Scarico } from 'src/app/entity/scarico.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { DipendenteService } from 'src/app/services/dipendente.service';
import { ScaricoService } from 'src/app/services/scarico.service';

@Component({
    selector: 'app-dettaglio-scarico',
    templateUrl: './dettaglio-scarico.component.html',
    styleUrls: ['./dettaglio-scarico.component.scss']
})
export class DettaglioScaricoComponent implements OnInit {

    emailCorrente: string = '';
    ruolo: string = '';

    listaScarichi: Scarico[] = [];

    scarico: Scarico = {
        idScarico: 0,
        dataPrenScar: null,
        dataRegScar: null,
        fornitore: null,
        dipendente: null
    }

    fornitore: Fornitore = {
        idFornitore: 0,
        nomeDittaFornitore: '',
        indirizzo: '',
        citta: '',
        dataInizioRapporto: null,
        dataFineRapporto: null,
        email: '',
        telefono: ''
    }

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
    }

    prodotto: Prodotto = {
        idProdotto: null,
        nome: '',
        marca: '',
        descrizione: null,
        ingredienti: '',
        tipoProdotto: '',
        categoriaProdotto: '',
        dataScadenza: null,
        qtaDisponibile: null,
        pesoDisponibile: null,
        prezzoAcquistoUnitario: 0,
        prezzoVenditaUnitario: 0,
        percentualeOfferta: null,
        scaffale: null
    }

    listaProdottiOrdinati: Prodotto[] = [];
    listaDettagliScarico: DettaglioScarico[] = [];

    constructor(private ar: ActivatedRoute, private scarServ: ScaricoService, private authServ: AuthJwtService, private dipServ: DipendenteService, private router: Router) { }

    ngOnInit(): void {
        this.authServ.isAuthenticated();
        this.emailCorrente = this.authServ.getEmailCorrente();
        console.log(this.emailCorrente);
        if (this.emailCorrente != null) {
            this.dipServ.getDipendenteByEmail(this.emailCorrente).subscribe((ris) => {
                this.dipendente = ris;
                console.log(this.dipendente);
                this.ruolo = this.dipendente.mansioni[0].tipoMansione;
                console.log(this.ruolo);
                if (!(this.ruolo.includes('DIRETTORE')) && !(this.ruolo.includes('MAGAZZINIERE'))) {
                    console.log(this.ruolo);
                    this.router.navigate(['/forbidden']);
                } else {
                    let id: number = this.ar.snapshot.params['id'];
                    this.scarServ.getAllScarichi().subscribe((risp) => {
                        this.listaScarichi = risp;
                        console.log(this.listaScarichi);
                        for (let sc of this.listaScarichi) {
                            if (Number(sc.idScarico) == id) {
                                this.scarico = sc;
                                console.log(this.scarico.fornitore.idFornitore);
                                this.scarServ.getFornitoreById(this.scarico.fornitore.idFornitore).subscribe((risp) => {
                                    console.log(risp);
                                    this.fornitore = risp;
                                })
                                console.log(this.scarico.idScarico);
                                this.scarServ.getDettagliScarichiByIdScarico(this.scarico.idScarico).subscribe((risp) => {
                                    console.log(risp);
                                    this.listaDettagliScarico = risp;

                                })
                            }
                        }
                    });
                }
            })
        }

    }

    tornaIndietro() {
        this.scarServ.indietro();
    }

}
