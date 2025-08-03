package ma.iibdcc.ebankingbackend.web;

import ma.iibdcc.ebankingbackend.dtos.AccountHistoryDTO;
import ma.iibdcc.ebankingbackend.dtos.AccountOperationDTO;
import ma.iibdcc.ebankingbackend.dtos.BankAccountDTO;
import ma.iibdcc.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.iibdcc.ebankingbackend.services.IBankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BankAccountRestController {
    private IBankAccountService bankAccountService;

    public BankAccountRestController(IBankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccountById(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    @GetMapping("/accounts")
    public List<BankAccountDTO> getAllBankAccounts() {
        return bankAccountService.bankAccountList();
    }

    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(String accountId) throws BankAccountNotFoundException {
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId, page, size);
    }
}
