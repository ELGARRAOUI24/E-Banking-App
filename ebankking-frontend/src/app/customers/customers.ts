import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../services/customer";
import {CustomerModel} from "../models/customer";
import {catchError, map, Observable, throwError} from "rxjs";
import {AsyncPipe} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customers',
  imports: [
    AsyncPipe,
    ReactiveFormsModule
  ],
  templateUrl: './customers.html',
  styleUrl: './customers.css'
})
export class Customers implements OnInit {
  customers! : Observable<Array<CustomerModel>>;
  errorMessage! : string;
  searchFormGroup! : FormGroup;

  constructor(private customerService:CustomerService, private fb:FormBuilder, private router:Router) { }

  ngOnInit(): void {
    this.searchFormGroup = this.fb.group({
      keyword: this.fb.control("")
    })
    this.handleSearchCustomers();
  }

  handleSearchCustomers() {
    let kw = this.searchFormGroup?.value.keyword;
    this.customers = this.customerService.searchCustomers(kw).pipe(
        catchError(err => {
          this.errorMessage = err.message;
          return throwError(err);
        })
    );
  }

  handleDeleteCustomer(c: CustomerModel) {
    let conf = confirm("Are you sure?")
    if(!conf) return;
    this.customerService.deleteCustomers(c.id).subscribe({
      next: () => {
        this.customers = this.customers.pipe(
            map(data=>{
              let index = data.indexOf(c);
              data.slice(index,1)
              return data;
            })
        )
      },
      error: err =>{
        console.log(err);
      }
    })
  }

  handleCustomerAccounts(c: CustomerModel) {
    this.router.navigateByUrl("/admin/customer-account/"+c.id, {state: c});
  }
}
