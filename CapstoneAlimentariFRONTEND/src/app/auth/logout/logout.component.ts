import { Component, OnInit } from '@angular/core';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent implements OnInit {

  constructor(private authServ: AuthJwtService) { }    // devo cambiare il servizio di sicurezza

  ngOnInit(): void {
    this.authServ.isAuthenticated();
    this.authServ.logout();
  }

}
