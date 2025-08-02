package ma.iibdcc.ebankingbackend.services;

import ma.iibdcc.ebankingbackend.dtos.*;
import ma.iibdcc.ebankingbackend.exceptions.BalanceNotSufficentException;
import ma.iibdcc.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.iibdcc.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface IBankAccountService {
    List<CustomerDTO> listCustomers();
    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long customerId);

    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    List<BankAccountDTO> bankAccountList();
    List<AccountOperationDTO> accountHistory(String accountId);
    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;

    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interesRate, long customerId) throws CustomerNotFoundException;
    void debitAccount(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficentException;
    void creditAccount(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficentException;
    void transfertAccount(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficentException;
    }
