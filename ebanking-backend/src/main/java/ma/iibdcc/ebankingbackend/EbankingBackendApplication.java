package ma.iibdcc.ebankingbackend;

import ma.iibdcc.ebankingbackend.entities.*;
import ma.iibdcc.ebankingbackend.enums.AccountStatus;
import ma.iibdcc.ebankingbackend.enums.OperationType;
import ma.iibdcc.ebankingbackend.exceptions.BalanceNotSufficentException;
import ma.iibdcc.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.iibdcc.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.iibdcc.ebankingbackend.repositories.interfaces.IAccountOperationRepository;
import ma.iibdcc.ebankingbackend.repositories.interfaces.IBankAccountRepository;
import ma.iibdcc.ebankingbackend.repositories.interfaces.ICustomerRepository;
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


    @Bean
    CommandLineRunner start(IBankAccountService bankAccountService){
        return args -> {
            Stream.of("Abdellah","Amine","Saad").forEach(name-> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomers().forEach(customer->{
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*90000, 9000, customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*90000, 3.75, customer.getId());
                    List<BankAccount> bankAccountList = bankAccountService.bankAccountList();
                    for(BankAccount bankAccount:bankAccountList){
                        for (int i = 0; i < 10; i++) {
                            bankAccountService.creditAccount(bankAccount.getId(), 10000+Math.random()*120000, "Credit");
                            bankAccountService.debitAccount(bankAccount.getId(), 1000+Math.random()*8000, "Debit");
                        }
                    }
                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                } catch (BankAccountNotFoundException | BalanceNotSufficentException e) {
                    e.printStackTrace();
                }
            });
        };
    }

}
