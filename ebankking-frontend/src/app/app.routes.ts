import { Routes } from '@angular/router';
import {Customers} from './customers/customers';
import {Accounts} from './accounts/accounts';
import {NewCustomer} from './new-customer/new-customer';
import {CustomerAccount} from './customer-account/customer-account';
import {Login} from './login/login';
import {Admin} from './admin/admin';
import {AuthenticationGuard} from './guards/authentication-guard';
import {AuthorizationGuard} from './guards/authorization-guard';
import {NotAuthorized} from './not-authorized/not-authorized';

export const routes: Routes = [
  { path: "", redirectTo: "login", pathMatch: "full"},
  { path: "login", component: Login},
  { path: "admin", component: Admin, canActivate : [AuthenticationGuard],
    children :[
      { path: "customers", component: Customers},
      { path: "new-customer", component: NewCustomer, canActivate : [AuthorizationGuard], data : {role:"ADMIN"}},
      { path: "accounts", component: Accounts},
      { path: "accounts/:id", component: Accounts},
      { path: "customer-account/:id", component: CustomerAccount},
      { path: "notAuthorized", component: NotAuthorized}
    ]},
];
