import { Routes } from '@angular/router';
import {Customers} from './customers/customers';
import {Accounts} from './accounts/accounts';
import {NewCustomer} from './new-customer/new-customer';
import {CustomerAccount} from './customer-account/customer-account';

export const routes: Routes = [
  { path: "customers", component: Customers},
  { path: "new-customer", component: NewCustomer},
  { path: "accounts", component: Accounts},
  { path: "customer-account/:id", component: CustomerAccount}
];
