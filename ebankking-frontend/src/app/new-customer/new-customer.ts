import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {CustomerModel} from '../models/customer';
import {CustomerService} from '../services/customer';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-new-customer',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './new-customer.html',
  styleUrl: './new-customer.css'
})
export class NewCustomer {
  newCustomerFormGroup!: FormGroup;

  constructor(private  fb : FormBuilder, private customerService:CustomerService, private router:Router) { }

  ngOnInit(): void {
    this.newCustomerFormGroup = this.fb.group({
      name : this.fb.control(null, [Validators.required, Validators.minLength(4)]),
      email : this.fb.control(null, [Validators.required, Validators.email])
    })
  }

  handleSaveCustomer() {
    let customer:CustomerModel = this.newCustomerFormGroup.value;
    this.customerService.saveCustomers(customer).subscribe({
      next : data => {
        alert("Customer has been successfully save.");
        this.router.navigateByUrl("/customers");
      },
      error : err => {
        console.log(err);
      }
    });
  }
}
