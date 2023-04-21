import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Prodotto } from 'src/app/entity/prodotto.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { ProdottoService } from 'src/app/services/prodotto.service';

@Component({
    selector: 'app-dettaglio-prodotto',
    templateUrl: './dettaglio-prodotto.component.html',
    styleUrls: ['./dettaglio-prodotto.component.scss']
})
export class DettaglioProdottoComponent implements OnInit {

    listaProdotti: Prodotto[] = [];

    prod: Prodotto = {
        idProdotto: 0,
        nome: '',
        marca: '',
        descrizione: '',
        ingredienti: '',
        tipoProdotto: '',
        categoriaProdotto: '',
        dataScadenza: new Date(9999, 12, 31),
        qtaDisponibile: 0,
        pesoDisponibile: 0,
        prezzoAcquistoUnitario: 0,
        prezzoVenditaUnitario: 0,
        percentualeOfferta: 0,
        scaffale: null
    }

    constructor(private ar: ActivatedRoute, private prodServ: ProdottoService, private authServ: AuthJwtService) { }

    ngOnInit(): void {
        this.authServ.isAuthenticated();
        let id: number = this.ar.snapshot.params['id'];
        this.prodServ.getAllProdotti().subscribe((risp) => {
            this.listaProdotti = risp;
            console.log(this.listaProdotti);
            for (let pr of this.listaProdotti) {
                if (Number(pr.idProdotto) == id) {
                    this.prod = pr;
                }
            }
        });
    }

    tornaIndietro() {
        this.prodServ.indietro();
    }


}
