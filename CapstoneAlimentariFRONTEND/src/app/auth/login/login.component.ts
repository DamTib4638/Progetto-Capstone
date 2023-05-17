import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginDto } from 'src/app/entity/login-dto.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    login: LoginDto = {
        email: '',
        password: ''
    }
    mail: string = '';
    isLogged: boolean = true;
    errMsg: string = "";
    okMsg: string = "";

    constructor(private route: Router, private authServ: AuthJwtService) { }

    ngOnInit(): void {
    }

    gestioneAuth (formLogin: NgForm): void {
        if (formLogin.value.email != null && formLogin.value.password != null) {
            this.login.email = formLogin.value.email;
            this.login.password = formLogin.value.password;
            this.authServ.login(this.login).subscribe((data) => {
                this.mail = data.email;
                window.location.href = 'welcome';
            },
            (error) => {
                this.isLogged = false;
                this.errMsg = 'EMAIL e/o PASSWORD errati o assenti'
            })
        }
    }

    reloadPage() {
        window.location.reload();
    }
}
