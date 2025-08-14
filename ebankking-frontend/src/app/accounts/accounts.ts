import { Component } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AccountService} from '../services/account';
import {AccountModel} from '../models/account';
import {AsyncPipe, CommonModule} from '@angular/common';
import {catchError, Observable, throwError} from 'rxjs';
import {ActivatedRoute} from '@angular/router';
import {AuthService} from '../services/auth';

@Component({
  selector: 'app-accounts',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    AsyncPipe
  ],
  templateUrl: './accounts.html',
  styleUrl: './accounts.css'
})
export class Accounts {
  searchFormGroup! : FormGroup;
  currentPage: number = 0;
  pageSize: number = 10;
  accountObservable!: Observable<AccountModel>;
  operationFormGroup!: FormGroup;
  errorMessage! : string;
  constructor(private accounSerivce: AccountService,
              private fb:FormBuilder,
              private route: ActivatedRoute,
              public authService : AuthService) { }

  ngOnInit(): void {
    this.searchFormGroup = this.fb.group({
      accountId: this.fb.control("")
    });
    this.operationFormGroup = this.fb.group({
      operationType: this.fb.control(null),
      amount: this.fb.control(0),
      accountDestination: this.fb.control(null),
      description: this.fb.control(null)
    });
    const idAccount = this.route.snapshot.params['id'];
    if(idAccount != '' && idAccount != null){
      this.handleGetAccount(idAccount);
      this.searchFormGroup.patchValue({ accountId: idAccount });
    }
  }

  handleSearchAccount() {
    let accountId: string = this.searchFormGroup.value.accountId;
    this.handleGetAccount(accountId);
  }
  handleGetAccount(accountId : string){
    this.accountObservable = this.accounSerivce.getAccount(accountId, this.currentPage, this.pageSize).pipe(
      catchError(err=>{
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }

  goToPage(page: number) {
    this.currentPage = page;
    this.handleSearchAccount();

  }

  handleAccountOperation() {
    let accountId:string = this.searchFormGroup.value.accountId;
    let operationType = this.operationFormGroup.value.operationType;
    let amount = this.operationFormGroup.value.amount;
    let description = this.operationFormGroup.value.description;
    if(operationType == 'Debut'){
      this.accounSerivce.debit(accountId, amount, description).subscribe({
        next: (data)=>{
          this.handleSearchAccount();
          this.operationFormGroup.reset();
          alert("Succes Debut");
        },
        error: err =>{
          console.log(err);
        }
      })
    }else if(operationType == 'Credit'){
      this.accounSerivce.credit(accountId, amount, description).subscribe({
        next: (data)=>{
          this.handleSearchAccount();
          this.operationFormGroup.reset();
          alert("Succes Credit");
        },
        error: err =>{
          console.log(err);
        }
      })
    }else if(operationType == 'Transfert'){
      this.accounSerivce.transfert(accountId, this.operationFormGroup.value.accountDestination, amount).subscribe({
        next: (data)=>{
          this.handleSearchAccount();
          this.operationFormGroup.reset();
          alert("Succes Transfert");
        },
        error: err =>{
          console.log(err);
        }
      })
    }
  }
}
