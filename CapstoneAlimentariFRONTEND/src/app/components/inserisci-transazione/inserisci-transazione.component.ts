import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Cassa } from 'src/app/entity/cassa.interface';
import { Dipendente } from 'src/app/entity/dipendente.interface';
import { Ordinazione } from 'src/app/entity/ordinazione.interface';
import { ProdottoDto } from 'src/app/entity/prodotto-dto.interface';
import { Prodotto } from 'src/app/entity/prodotto.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { DipendenteService } from 'src/app/services/dipendente.service';
import { ProdottoService } from 'src/app/services/prodotto.service';
import { TransazioneService } from 'src/app/services/transazione.service';

@Component({
    selector: 'app-inserisci-transazione',
    templateUrl: './inserisci-transazione.component.html',
    styleUrls: ['./inserisci-transazione.component.scss']
})
export class InserisciTransazioneComponent implements OnInit {

    quantitaProdotto: number = 0;
    ruolo: string = '';
    idCassa: number = 0;
    emailCorrente: string = '';
    nomeProdotto: string = '';

    listaCasse: Cassa[] = [];
    listaProdotti: Prodotto[] = [];
    listaProdottiDto: ProdottoDto[] = [];

    newTransazione: Ordinazione = {
        dataPrenotazioneOrdine: null,
        dataEmissioneTransazione: null,
        idFornitore: 0,
        idDipendente: 0,
        idCassa: 0,
        listaProdotti: this.listaProdottiDto
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

    constructor(private tranServ: TransazioneService,
        private authServ: AuthJwtService,
        private prodServ: ProdottoService,
        private dipServ: DipendenteService) { }

    ngOnInit(): void {
        this.authServ.isAuthenticated();
        this.emailCorrente = this.authServ.getEmailCorrente();
        if(this.emailCorrente != null) {
            this.dipServ.getDipendenteByEmail(this.emailCorrente).subscribe((ris) => {
                this.dipendente = ris;
                this.ruolo = this.dipendente.mansioni[0].tipoMansione;
            })
        }
        this.visualizzaListaCasse();
        this.visualizzaListaProdotti();
    }

    insert() {
        if (this.listaProdottiDto.length > 0 && this.idCassa > 0 && this.dipendente.idDipendente > 0) {
            this.newTransazione.idCassa = this.idCassa;
            this.newTransazione.idDipendente = this.dipendente.idDipendente;
            this.newTransazione.dataEmissioneTransazione = new Date();
            this.tranServ.insertTransazione(this.newTransazione);
        }
    }

    scegliCassa(form: NgForm) {
        this.idCassa = form.value.idCassa;
        this.cassa.idCassa = form.value.idCassa;
        this.cassa.numeroCassa = form.value.numeroCassa;
    }

    aggiungiProdottoDto(form: NgForm) {
        console.log(form.value.idProdotto);
        console.log(form.value.quantitaProdotto);
        let prodottoDto: ProdottoDto = {
            idProdotto: 0,
            quantita: 0
        }
        prodottoDto.idProdotto = form.value.idProdotto;
        prodottoDto.quantita = form.value.quantitaProdotto;
        this.listaProdottiDto.push(prodottoDto);
        console.log(this.listaProdottiDto);
    }

    eliminaProdottoDto(id: number) {
        console.log(id);
        let indice: number = 0;
        for (let pDto of this.listaProdottiDto) {
            if (id == pDto.idProdotto) {
                indice = this.listaProdottiDto.indexOf(pDto);
                this.listaProdottiDto.splice(indice, 1);
            }
        }
    }

    visualizzaListaProdotti() {
        this.prodServ.getAllProdotti().subscribe((risp) => {
            console.log(risp);
            this.listaProdotti = risp;
        })
    }

    visualizzaListaCasse() {
        this.tranServ.getAllCasse().subscribe((risp) => {
            this.listaCasse = risp;
        })
    }

    tornaIndietro() {
        this.tranServ.indietro();
    }

}
