import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Dipendente } from 'src/app/entity/dipendente.interface';
import { Fornitore } from 'src/app/entity/fornitore.interface';
import { Ordinazione } from 'src/app/entity/ordinazione.interface';
import { ProdottoDto } from 'src/app/entity/prodotto-dto.interface';
import { Prodotto } from 'src/app/entity/prodotto.interface';
import { Scarico } from 'src/app/entity/scarico.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { DipendenteService } from 'src/app/services/dipendente.service';
import { ProdottoService } from 'src/app/services/prodotto.service';
import { ScaricoService } from 'src/app/services/scarico.service';

@Component({
    selector: 'app-inserisci-scarico',
    templateUrl: './inserisci-scarico.component.html',
    styleUrls: ['./inserisci-scarico.component.scss']
})
export class InserisciScaricoComponent implements OnInit {

    quantitaProdotto: number = 1;
    ruolo: string = '';
    idFornitore: number = 0;
    prodottoPresenteInLista: boolean = false;

    listaFornitori: Fornitore[] = [];
    listaProdotti: Prodotto[] = [];
    listaProdottiDto: ProdottoDto[] = [];

    newScarico: Ordinazione = {
        dataPrenotazioneOrdine: null,
        dataEmissioneTransazione: null,
        idFornitore: 0,
        idDipendente: 0,
        idCassa: 0,
        listaProdotti: this.listaProdottiDto
    }

    fornitore: Fornitore = {
        idFornitore: 0,
        nomeDittaFornitore: '',
        indirizzo: '',
        citta: '',
        dataInizioRapporto: new Date(9999, 12, 31),
        dataFineRapporto: new Date(9999, 12, 31),
        email: '',
        telefono: ''
    }

    emailCorrente: string = '';
    nomeProdotto: string = '';

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

    constructor(private scarServ: ScaricoService,
        private authServ: AuthJwtService,
        private prodServ: ProdottoService,
        private dipServ: DipendenteService,
        private router: Router) { }

    ngOnInit(): void {
        this.authServ.isAuthenticated();
        this.emailCorrente = this.authServ.getEmailCorrente();
        if (this.emailCorrente != null) {
            this.dipServ.getDipendenteByEmail(this.emailCorrente).subscribe((ris) => {
                this.dipendente = ris;
                this.ruolo = this.dipendente.mansioni[0].tipoMansione;
                console.log(this.ruolo);
                if (!(this.ruolo.includes('DIRETTORE')) && !(this.ruolo.includes('MAGAZZINIERE'))) {
                    console.log(this.ruolo);
                    this.router.navigate(['/forbidden']);
                } else {
                    this.visualizzaListaFornitori();
                    this.visualizzaListaProdotti();
                }
            })
        }
    }

    insert() {
        if (this.listaProdottiDto.length > 0 && this.idFornitore > 0 && this.dipendente.idDipendente > 0) {
            this.newScarico.idFornitore = this.idFornitore;
            this.newScarico.idDipendente = this.dipendente.idDipendente;
            this.newScarico.dataPrenotazioneOrdine = new Date();
            console.log(this.newScarico);
            this.scarServ.insertScarico(this.newScarico);
        }
    }

    scegliFornitore(form: NgForm) {
        console.log(form.value.idFornitore);
        this.idFornitore = form.value.idFornitore;
        this.fornitore.idFornitore = form.value.idFornitore;
        this.fornitore.nomeDittaFornitore = form.value.nomeDittaFornitore;
    }

    aggiungiProdottoDto(idProdotto: number, qta: number) {
        // console.log(form.value.idProdotto);
        // console.log(form.value.quantitaProdotto);
        this.prodottoPresenteInLista = false;
        let prodottoDto: ProdottoDto = {
            idProdotto: 0,
            quantita: 0
        }
        // prodottoDto.idProdotto = form.value.idProdotto;
        // prodottoDto.quantita = form.value.quantitaProdotto;
        if (this.listaProdottiDto.length > 0) {
            for (let p of this.listaProdottiDto) {
                if (p.idProdotto == idProdotto) {
                    this.prodottoPresenteInLista = true;
                    p.quantita += qta;
                }
            }
        }
        if (!this.prodottoPresenteInLista) {
            prodottoDto.idProdotto = idProdotto;
            prodottoDto.quantita = qta;
            this.listaProdottiDto.push(prodottoDto);
        }
        // this.listaProdottiDto.push(prodottoDto);
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

    visualizzaListaFornitori() {
        this.scarServ.getAllFornitori().subscribe((risp) => {
            console.log(risp);
            this.listaFornitori = risp;
        })
    }

    tornaIndietro() {
        this.scarServ.indietro();
    }

}
