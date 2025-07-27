package ma.iibdcc.ebankingbackend.services;

import ma.iibdcc.ebankingbackend.entities.BankAccount;
import ma.iibdcc.ebankingbackend.entities.CurrentAccount;
import ma.iibdcc.ebankingbackend.entities.Customer;
import ma.iibdcc.ebankingbackend.entities.SavingAccount;
import ma.iibdcc.ebankingbackend.exceptions.BalanceNotSufficentException;
import ma.iibdcc.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.iibdcc.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface IBankAccountService {

    Customer saveCustomer(Customer customer);
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, long customerId) throws CustomerNotFoundException;
    SavingAccount saveSavingBankAccount(double initialBalance, double interesRate, long customerId) throws CustomerNotFoundException;
    List<Customer> listCustomers();
    BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debitAccount(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficentException;
    void creditAccount(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficentException;
    void transfertAccount(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficentException;

    List<BankAccount> bankAccountList();
}
