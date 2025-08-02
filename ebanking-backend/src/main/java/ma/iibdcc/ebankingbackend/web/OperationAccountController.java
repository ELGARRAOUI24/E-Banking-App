package ma.iibdcc.ebankingbackend.web;

import ma.iibdcc.ebankingbackend.exceptions.BalanceNotSufficentException;
import ma.iibdcc.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.iibdcc.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.iibdcc.ebankingbackend.services.IBankAccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperationAccountController {
    private IBankAccountService bankAccountService;

    public OperationAccountController(IBankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/operation/currentaccount")
    public void saveCurrentBankAccount(double balance, double overDarft, Long customerId) throws CustomerNotFoundException {
        bankAccountService.saveCurrentBankAccount(balance,overDarft,customerId);
    }

    @PostMapping("/operation/savingaccount")
    public void saveSavingBankAccount(double balance, double rate, Long customerId) throws CustomerNotFoundException {
        bankAccountService.saveSavingBankAccount(balance, rate, customerId);
    }

    @PostMapping("/operation/debitaccount")
    public void debitAccount(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficentException {
        bankAccountService.debitAccount(accountId, amount, description);
    }

    @PostMapping("/operation/creditaccount")
    public void creditAccount(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficentException {
        bankAccountService.creditAccount(accountId, amount, description);
    }

    @PostMapping("/operation/transfertaccount")
    public void transfertAccount(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficentException {
        bankAccountService.transfertAccount(accountIdSource, accountIdDestination, amount);
    }
}
