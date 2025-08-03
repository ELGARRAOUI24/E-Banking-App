import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CustomerModel} from '../models/customer';
import {AccountModel} from '../models/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  backendHost: string = "http://localhost:8585";

  constructor(private http: HttpClient) { }

  public getAccount(accountId : string, page: number, size: number):Observable<AccountModel>{
    return this.http.get<AccountModel>(this.backendHost+"/accounts/"+accountId+"/pageOperations?page="+page+"&size="+size);
  }

  public debit(accountId : string, amount: number, description: string){
    return this.http.post(this.backendHost+"/operation/debitaccount",{"accountId":accountId, "amount":amount, "description":description});
  }

  public credit(accountId : string, amount: number, description: string){
    return this.http.post(this.backendHost+"/operation/creditaccount",{"accountId":accountId, "amount":amount, "description":description});
  }

  public transfert(accountIdSource : string, accountIdDestination: string, amount: number){
    return this.http.post(this.backendHost+"/operation/transfertaccount",{"accountIdSource":accountIdSource, "accountIdDestination":accountIdDestination, "amount":amount});
  }


}
