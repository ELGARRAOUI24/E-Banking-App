import {CustomerModel} from './customer';

export interface BankAccountModel {
  type:         'SavingAccount' | 'CurrentAccount';
  id:           string;
  balance:      number;
  dateCreation: Date;
  status:       string | null;
  customerDTO:  CustomerModel;
  interestRate?: number;
  overDraft?: number;
}
