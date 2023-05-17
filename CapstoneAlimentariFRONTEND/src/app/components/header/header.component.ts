import { Component, OnInit } from '@angular/core';
import { Dipendente } from 'src/app/entity/dipendente.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { DipendenteService } from 'src/app/services/dipendente.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

    cognomeDipLoggato:string = ''
    nomeDipLoggato:string = ''
    emailCorrente:string = ''
    mansioni: string = ''

  constructor(public authServ: AuthJwtService, private dipServ: DipendenteService) { }

  ngOnInit(): void {
    this.authServ.isAuthenticated();
    this.emailCorrente = this.authServ.getEmailCorrente();
    if (this.emailCorrente != null) {
        this.dipServ.getDipendenteByEmail(this.emailCorrente).subscribe((ris) => {
            this.cognomeDipLoggato = ris.cognome;
            this.nomeDipLoggato = ris.nome;
            this.mansioni = ris.mansioni[0].tipoMansione;
        })
    }
  }

}
