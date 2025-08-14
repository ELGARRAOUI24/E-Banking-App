import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth';
import { inject } from '@angular/core';
export const AuthorizationGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  if (authService.roles.includes('ADMIN')) {
    return true;
  } else {
    router.navigateByUrl('/admin/notAuthorized');
    return false;
  }
};
