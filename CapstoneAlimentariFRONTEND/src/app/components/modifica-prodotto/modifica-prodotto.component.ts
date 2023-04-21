import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Prodotto } from 'src/app/entity/prodotto.interface';
import { Scaffale } from 'src/app/entity/scaffale.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { ProdottoService } from 'src/app/services/prodotto.service';

@Component({
    selector: 'app-modifica-prodotto',
    templateUrl: './modifica-prodotto.component.html',
    styleUrls: ['./modifica-prodotto.component.scss']
})
export class ModificaProdottoComponent implements OnInit {

    sceltaQtaPeso: number = 0;

    scaf: Scaffale = {
        idScaffale: 0,
        nome: '',
        posizione: 0,
        numeroMaxProd: 0,
        pesoMaxProd: 0,
        idReparto: 0
    }

    listaProdotti: Prodotto[] = [];
    listaScaffali: Scaffale[] = [];

    prod: Prodotto = {
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

    constructor(private prodServ: ProdottoService, private ar: ActivatedRoute, private authServ: AuthJwtService) { }

    ngOnInit(): void {
        this.authServ.isAuthenticated();
        this.visualizzaListaScaffali();
        let id: number = this.ar.snapshot.params['id'];
        this.prodServ.getAllProdotti().subscribe((risp) => {
            this.listaProdotti = risp;
            console.log(this.listaProdotti);
            for (let pr of this.listaProdotti) {
                if (Number(pr.idProdotto) == id) {
                    this.prod = pr;
                    console.log(this.prod.scaffale);
                    this.scaf = this.prod.scaffale;
                }
            }
        });

    }

    // modifica(form: NgForm) {

    // }

    modifica(form: NgForm) {
        console.log(this.ar.snapshot.params['id']);
        if (form.value.nome !== null) {
            this.prod.nome = form.value.nome;
            this.prod.marca = form.value.marca;
            this.prod.descrizione = form.value.descrizione;
            this.prod.ingredienti = form.value.ingredienti;
            this.prod.tipoProdotto = form.value.tipoProdotto;
            this.prod.categoriaProdotto = form.value.categoriaProdotto;
            this.prod.dataScadenza = form.value.dataScadenza;
            this.prod.qtaDisponibile = form.value.qtaDisponibile;
            this.prod.pesoDisponibile = form.value.pesoDisponibile;
            this.prod.prezzoAcquistoUnitario = form.value.prezzoAcquistoUnitario;
            this.prod.prezzoVenditaUnitario = form.value.prezzoVenditaUnitario;
            this.prod.percentualeOfferta = form.value.percentualeOfferta;
            // this.prodServ.getScaffaleById(form.value.idScaffale)
            this.prodServ.getScaffaleById(form.value.idScaffale).subscribe((risp) => {
                console.log(risp);
                this.prod.scaffale = risp;
                console.log(this.prod.scaffale);
                this.prodServ.editProdotto(this.prod);
            });
        }
    }

    // getScaffale(id: number) {
    //     this.prodServ.getScaffaleById(id).subscribe((risp) => {
    //         console.log(risp);
    //         this.scaf = risp;
    //         console.log(this.scaf);
    //     });
    // }

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
