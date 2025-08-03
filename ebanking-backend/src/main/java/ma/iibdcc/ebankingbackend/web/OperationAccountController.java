package ma.iibdcc.ebankingbackend.web;

import ma.iibdcc.ebankingbackend.dtos.DebitCreditDTO;
import ma.iibdcc.ebankingbackend.dtos.TransfertDTO;
import ma.iibdcc.ebankingbackend.exceptions.BalanceNotSufficentException;
import ma.iibdcc.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.iibdcc.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.iibdcc.ebankingbackend.services.IBankAccountService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
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
    public void debitAccount(@RequestBody DebitCreditDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficentException {
        bankAccountService.debitAccount(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());
    }

    @PostMapping("/operation/creditaccount")
    public void creditAccount(@RequestBody DebitCreditDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficentException {
        bankAccountService.creditAccount(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());
    }

    @PostMapping("/operation/transfertaccount")
    public void transfertAccount(@RequestBody TransfertDTO transfertDTO) throws BankAccountNotFoundException, BalanceNotSufficentException {
        bankAccountService.transfertAccount(transfertDTO.getAccountIdSource(), transfertDTO.getAccountIdDestination(), transfertDTO.getAmount());
    }
}
