import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Dipendente } from 'src/app/entity/dipendente.interface';
import { Prodotto } from 'src/app/entity/prodotto.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { DipendenteService } from 'src/app/services/dipendente.service';
import { ProdottoService } from 'src/app/services/prodotto.service';

@Component({
  selector: 'app-prodotti',
  templateUrl: './prodotti.component.html',
  styleUrls: ['./prodotti.component.scss']
})
export class ProdottiComponent implements OnInit {

    listaProdotti: Prodotto[] = [];

    listaProdottiAppoggio: Prodotto[] = [];
    emailCorrente: string = '';
    mansioni: string = ''
    testo: string = '';
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

  constructor(private prodServ: ProdottoService,
                private router: Router,
                public authServ: AuthJwtService,
                private dipServ: DipendenteService) { }

  ngOnInit(): void {
    this.authServ.isAuthenticated();
    this.emailCorrente = this.authServ.getEmailCorrente();
    if (this.emailCorrente != null) {
        this.dipServ.getDipendenteByEmail(this.emailCorrente).subscribe((ris) => {
            this.dipendente = ris;
            this.ruolo = this.dipendente.mansioni[0].tipoMansione;
        })
    }
    this.visualizzaListaProdotti();
  }

  visualizzaListaProdotti() {
    this.prodServ.getAllProdotti().subscribe((response) => {
        this.listaProdotti = response;
    })
  }

  cercaProd() {
    // console.log(this.testo);
  }

  visualizzaProdottiPerCategoria(categoria: string) {
    // console.log(categoria);
  }

  nuovoProdotto() {
    this.router.navigate(['/inserisci_prodotto']);
  }

}
