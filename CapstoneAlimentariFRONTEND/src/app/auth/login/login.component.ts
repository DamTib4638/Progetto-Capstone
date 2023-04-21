import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginDto } from 'src/app/entity/login-dto.interface';
import { AuthJwtService } from 'src/app/services/auth-jwt.service';
import { AuthService } from 'src/app/services/auth.service';

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

    // questo è il primo esempio di code Injection, cioè: definita la variabile route di tipo Router nel costruttore, siamo in grado di accedere a tutti i metodi e le proprietà di questo oggetto. Lo utilizziamo nel metodo gestioneAuth.
    constructor(private route: Router, private authServ: AuthJwtService) { }

    ngOnInit(): void {
    }

    gestioneAuth (formLogin: NgForm): void {
        if (formLogin.value.email != null && formLogin.value.password != null) {
            this.login.email = formLogin.value.email;
            this.login.password = formLogin.value.password;
            console.log(this.login.email);
            this.authServ.login(this.login).subscribe((data) => {
                window.location.href = 'welcome';
                // this.route.navigate(['welcome']);
                console.log('success', data);
                this.mail = data.email;

            },
            (error) => {
                console.log('Login non andato a buon fine', error);
                this.isLogged = false;
                this.errMsg = 'EMAIL e/o PASSWORD errati o assenti'
            })
        }
        // this.authServ.login(formLogin.value).subscribe((data) => {
        //     console.log('success', data);
        //     this.mail = data.email;
        //     this.route.navigate(['/welcome'])
        // },
        // (error) => {
        //     console.log('Login non andato a buon fine', error);
        //     this.isLogged = false;
        //     this.errMsg = 'EMAIL e/o PASSWORD errati o assenti. Compilare correttamente i campi'
        // })
    }

    reloadPage() {
        window.location.reload();
    }

    // gestioneAuth = (): void => {
    //     if (this.authServ.autentica(this.email, this.password)) {
    //         this.route.navigate(['welcome']);
    //         this.isLogged = true;
    //     } else {
    //         this.isLogged = false;
    //         this.errMsg = 'email e/o password errati. Correggere!';
    //     }
    // }


}
