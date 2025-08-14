package ma.iibdcc.ebankingbackend;

import ma.iibdcc.ebankingbackend.dtos.*;
import ma.iibdcc.ebankingbackend.exceptions.*;
import ma.iibdcc.ebankingbackend.services.IBankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }


    //@Bean
    CommandLineRunner start(IBankAccountService bankAccountService){
        return args -> {
            Stream.of("Abdellah","Amine","Saad").forEach(name-> {
                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomers().forEach(customer->{
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*90000, 9000, customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*90000, 3.75, customer.getId());
                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });
            List<BankAccountDTO> bankAccountList = bankAccountService.bankAccountList();
            for(BankAccountDTO bankAccount:bankAccountList){
                for (int i = 0; i < 10; i++) {
                    String accountId;
                    if(bankAccount instanceof CurrentBankAccountDTO){
                        accountId = ((CurrentBankAccountDTO) bankAccount).getId();
                    }else {
                        accountId = ((SavingBankAccountDTO) bankAccount).getId();
                    }
                    bankAccountService.creditAccount(accountId, 10000+Math.random()*120000, "Credit");
                    bankAccountService.debitAccount(accountId, 1000+Math.random()*8000, "Debit");
                }
            }
        };
    }

}
