import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Prodotto } from 'src/app/entity/prodotto.interface';
import { Scaffale } from 'src/app/entity/scaffale.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { ProdottoService } from 'src/app/services/prodotto.service';

@Component({
    selector: 'app-inserisci-prodotto',
    templateUrl: './inserisci-prodotto.component.html',
    styleUrls: ['./inserisci-prodotto.component.scss']
})
export class InserisciProdottoComponent implements OnInit {

    sceltaQtaPeso: number = 0;

    scaf: Scaffale = {
        idScaffale: 0,
        nome: '',
        posizione: 0,
        numeroMaxProd: 0,
        pesoMaxProd: 0,
        idReparto: 0
    }

    listaScaffali: Scaffale[] = [];

    newProd: Prodotto = {
        idProdotto: null,
        nome: '',
        marca: '',
        descrizione: null,
        ingredienti: '',
        tipoProdotto: '',
        categoriaProdotto: '',
        dataScadenza: new Date(9999, 12, 31),
        qtaDisponibile: null,
        pesoDisponibile: null,
        prezzoAcquistoUnitario: 0,
        prezzoVenditaUnitario: 0,
        percentualeOfferta: null,
        scaffale: null
    }

    constructor(private prodServ: ProdottoService, private authServ: AuthJwtService) { }

    ngOnInit(): void {
        this.authServ.isAuthenticated();
        this.visualizzaListaScaffali();
    }

    insert(form: NgForm) {
        if (form.value.nome !== null) {
            this.newProd.nome = form.value.nome;
            this.newProd.marca = form.value.marca;
            this.newProd.descrizione = form.value.descrizione;
            this.newProd.ingredienti = form.value.ingredienti;
            this.newProd.tipoProdotto = form.value.tipoProdotto;
            this.newProd.categoriaProdotto = form.value.categoriaProdotto;
            this.newProd.dataScadenza = form.value.dataScadenza;
            this.newProd.qtaDisponibile = form.value.qtaDisponibile;
            this.newProd.pesoDisponibile = form.value.pesoDisponibile;
            this.newProd.prezzoAcquistoUnitario = form.value.prezzoAcquistoUnitario;
            this.newProd.prezzoVenditaUnitario = form.value.prezzoVenditaUnitario;
            this.newProd.percentualeOfferta = form.value.percentualeOfferta;
            // this.prodServ.getScaffaleById(form.value.idScaffale)
            this.prodServ.getScaffaleById(form.value.idScaffale).subscribe((risp) => {
                console.log(risp);
                this.newProd.scaffale = risp;
                console.log(this.newProd.scaffale);
                this.prodServ.insertProdotto(this.newProd);
            });
            console.log(form.value.idScaffale);
            console.log(this.newProd);
        }
    }

    getScaffale(id: number) {
        this.prodServ.getScaffaleById(id).subscribe((risp) => {
            console.log(risp);
            this.scaf = risp;
            console.log(this.scaf);
        });
    }

    visualizzaListaScaffali() {
        this.prodServ.getAllScaffali().subscribe((risp) => {
            console.log(risp);
            this.listaScaffali = risp;
        })
    }

    tornaIndietro() {
        this.prodServ.indietro();
    }

}