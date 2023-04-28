import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cassa } from 'src/app/entity/cassa.interface';
import { DettaglioTransazione } from 'src/app/entity/dettaglio-transazione.interface';
import { Dipendente } from 'src/app/entity/dipendente.interface';
import { Prodotto } from 'src/app/entity/prodotto.interface';
import { Transazione } from 'src/app/entity/transazione.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { DipendenteService } from 'src/app/services/dipendente.service';
import { TransazioneService } from 'src/app/services/transazione.service';

@Component({
    selector: 'app-dettaglio-transazione',
    templateUrl: './dettaglio-transazione.component.html',
    styleUrls: ['./dettaglio-transazione.component.scss']
})
export class DettaglioTransazioneComponent implements OnInit {

    emailCorrente: string = '';
    ruolo: string = '';

    listaTransazioni: Transazione[] = [];

    transazione: Transazione = {
        idTransazione: 0,
        dataEmissioneTransazione: null,
        dataPagamentoTransazione: null,
        cassa: null,
        dipendente: null
    }

    cassa: Cassa = {
        idCassa: 0,
        numeroCassa: 0,
        fondoCassa: 0,
        cassaFunzionante: false
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

    listaProdottiVenduti: Prodotto[] = [];
    listaDettagliTransazione: DettaglioTransazione[] = [];

    constructor(private ar: ActivatedRoute, private authServ: AuthJwtService, private tranServ: TransazioneService, private dipServ: DipendenteService, private router: Router) { }

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
                if (!(this.ruolo.includes('DIRETTORE')) && !(this.ruolo.includes('CASSIERE'))) {
                    console.log(this.ruolo);
                    this.router.navigate(['/forbidden']);
                } else {
                    let id: number = this.ar.snapshot.params['id'];
                    console.log(id);
                    this.tranServ.getAllTransazioni().subscribe((risp) => {
                        this.listaTransazioni = risp;
                        for (let tr of this.listaTransazioni) {
                            if (Number(tr.idTransazione) == id) {
                                this.transazione = tr;
                                this.tranServ.getCassaById(this.transazione.cassa.idCassa).subscribe((risp) => {
                                    this.cassa = risp;
                                })
                                this.tranServ.getDettagliTransazioniByIdTransazione(this.transazione.idTransazione).subscribe((risp) => {
                                    this.listaDettagliTransazione = risp;
                                })
                            }
                        }
                    })
                }
            })
        }
    }

    tornaIndietro() {
        this.tranServ.indietro();
    }

}
