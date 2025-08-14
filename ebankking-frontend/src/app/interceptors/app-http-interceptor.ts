import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth';
import {catchError, throwError} from 'rxjs';

export const AppHttpInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);

  if (!req.url.includes('/auth/login')) {
    const clonedRequest = req.clone({
      headers: req.headers.set('Authorization', 'Bearer ' + authService.accessToken)
    });
    return next(clonedRequest).pipe(
      catchError(err => {
        if(err.status == 401){
          authService.logout();
        }
        return throwError(err.message);
      })
    );
  }

  return next(req);
};
