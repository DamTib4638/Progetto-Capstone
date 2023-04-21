import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate {

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        if (!this.authServ.dipLogged()) {
            this.router.navigate(['login']);
            return this.authServ.dipLogged();
        }
        return this.authServ.dipLogged();
    }

    constructor(private authServ: AuthService, private router: Router) { }

}
