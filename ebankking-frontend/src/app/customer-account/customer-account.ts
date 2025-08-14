import { Component } from '@angular/core';
import {AsyncPipe, DatePipe, DecimalPipe, NgForOf, NgIf} from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from '../services/account';
import { BankAccountModel } from '../models/bankAccount';
import { Observable, catchError, throwError } from 'rxjs';

@Component({
  selector: 'app-customer-account',
  standalone: true,
  templateUrl: './customer-account.html',
  styleUrls: ['./customer-account.css'],
  imports: [
    AsyncPipe,
    DatePipe,
    NgIf,
    NgForOf,
    DecimalPipe
  ]
})
export class CustomerAccount {
  accounts!: Observable<BankAccountModel[]>;

  constructor(
    private accountService: AccountService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.accounts = this.accountService.getAccountByCustomerId(id).pipe(
      catchError(err => {
        console.error(err);
        return throwError(() => err);
      })
    );
  }

  handleAccount(id: string) {
    console.log("test");
    this.router.navigateByUrl("/accounts/"+id);
  }
}
