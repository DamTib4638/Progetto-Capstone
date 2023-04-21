import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthJwtService } from '../services/auth-jwt.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(public authServ: AuthJwtService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (request.url.includes('/api/auth/alimentari/login') || request.url.includes('/api/auth/alimentari/register')) {
        return next.handle(request);
    }
    request = request.clone({
        setHeaders: {
            Authorization: `Bearer ${this.authServ.getToken()}`
        }
    })
    return next.handle(request);
  }
}
