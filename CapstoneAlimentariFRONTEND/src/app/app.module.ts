import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { FormsModule } from '@angular/forms';
import { ErrorComponent } from './auth/error/error.component';
import { ProdottiComponent } from './components/prodotti/prodotti.component';
import { InserisciProdottoComponent } from './components/inserisci-prodotto/inserisci-prodotto.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { RouterModule } from '@angular/router';
import { LogoutComponent } from './auth/logout/logout.component';
import { AccessoVietatoComponent } from './components/accesso-vietato/accesso-vietato.component';
import { DettaglioProdottoComponent } from './components/dettaglio-prodotto/dettaglio-prodotto.component';
import { ModificaProdottoComponent } from './components/modifica-prodotto/modifica-prodotto.component';
import { ScarichiComponent } from './components/scarichi/scarichi.component';
import { TransazioniComponent } from './components/transazioni/transazioni.component';
import { DettaglioScaricoComponent } from './components/dettaglio-scarico/dettaglio-scarico.component';
import { InserisciScaricoComponent } from './components/inserisci-scarico/inserisci-scarico.component';
import { DettaglioTransazioneComponent } from './components/dettaglio-transazione/dettaglio-transazione.component';
import { InserisciTransazioneComponent } from './components/inserisci-transazione/inserisci-transazione.component';
import { DipendentiComponent } from './components/dipendenti/dipendenti.component';
import { ModificaDipendenteComponent } from './components/modifica-dipendente/modifica-dipendente.component';
import { DettaglioDipendenteComponent } from './components/dettaglio-dipendente/dettaglio-dipendente.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    WelcomeComponent,
    ErrorComponent,
    ProdottiComponent,
    InserisciProdottoComponent,
    HeaderComponent,
    FooterComponent,
    LogoutComponent,
    AccessoVietatoComponent,
    DettaglioProdottoComponent,
    ModificaProdottoComponent,
    ScarichiComponent,
    TransazioniComponent,
    DettaglioScaricoComponent,
    InserisciScaricoComponent,
    DettaglioTransazioneComponent,
    InserisciTransazioneComponent,
    DipendentiComponent,
    ModificaDipendenteComponent,
    DettaglioDipendenteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    RouterModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
