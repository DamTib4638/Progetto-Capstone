import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { ErrorComponent } from './auth/error/error.component';
import { ProdottiComponent } from './components/prodotti/prodotti.component';
import { LogoutComponent } from './auth/logout/logout.component';
import { RouteGuardService } from './services/route-guard.service';
import { RegisterComponent } from './auth/register/register.component';
import { Mansioni } from './entity/mansioni.enum';
import { AccessoVietatoComponent } from './components/accesso-vietato/accesso-vietato.component';
import { DettaglioProdottoComponent } from './components/dettaglio-prodotto/dettaglio-prodotto.component';
import { ModificaProdottoComponent } from './components/modifica-prodotto/modifica-prodotto.component';
import { InserisciProdottoComponent } from './components/inserisci-prodotto/inserisci-prodotto.component';
import { ScarichiComponent } from './components/scarichi/scarichi.component';
import { TransazioniComponent } from './components/transazioni/transazioni.component';
import { DettaglioScaricoComponent } from './components/dettaglio-scarico/dettaglio-scarico.component';
import { InserisciScaricoComponent } from './components/inserisci-scarico/inserisci-scarico.component';
import { DettaglioTransazioneComponent } from './components/dettaglio-transazione/dettaglio-transazione.component';
import { InserisciTransazioneComponent } from './components/inserisci-transazione/inserisci-transazione.component';
import { DipendentiComponent } from './components/dipendenti/dipendenti.component';
import { DettaglioDipendenteComponent } from './components/dettaglio-dipendente/dettaglio-dipendente.component';
import { ModificaDipendenteComponent } from './components/modifica-dipendente/modifica-dipendente.component';

const routes: Routes = [
    {path:'', component: WelcomeComponent},
    {path:'forbidden', component: AccessoVietatoComponent},
    {path:'login', component: LoginComponent},
    {path:'welcome', component: WelcomeComponent},
    {path:'prodotti', component: ProdottiComponent},
    {path:'dettaglio_prodotto/:id', component: DettaglioProdottoComponent},
    {path:'modifica_prodotto/:id', component: ModificaProdottoComponent},
    {path:'inserisci_prodotto', component: InserisciProdottoComponent, },

    {path:'scarichi', component: ScarichiComponent},
    {path:'dettaglio_scarico/:id', component: DettaglioScaricoComponent},
    {path:'inserisci_scarico', component: InserisciScaricoComponent},

    {path:'transazioni', component: TransazioniComponent},
    {path:'dettaglio_transazione/:id', component: DettaglioTransazioneComponent},
    {path:'inserisci_transazione', component: InserisciTransazioneComponent},

    {path:'dipendenti', component: DipendentiComponent},
    {path:'dettaglio_dipendente/:id', component: DettaglioDipendenteComponent},
    {path:'modifica_dipendente/:id', component: ModificaDipendenteComponent},

    {path:'logout', component: LogoutComponent},
    {path:'register', component: RegisterComponent},
    {path:'**', component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
