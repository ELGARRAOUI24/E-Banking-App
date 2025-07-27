package ma.iibdcc.ebankingbackend;

import ma.iibdcc.ebankingbackend.entities.AccountOperation;
import ma.iibdcc.ebankingbackend.entities.CurrentAccount;
import ma.iibdcc.ebankingbackend.entities.Customer;
import ma.iibdcc.ebankingbackend.entities.SavingAccount;
import ma.iibdcc.ebankingbackend.enums.AccountStatus;
import ma.iibdcc.ebankingbackend.enums.OperationType;
import ma.iibdcc.ebankingbackend.repositories.interfaces.IAccountOperationRepository;
import ma.iibdcc.ebankingbackend.repositories.interfaces.IBankAccountRepository;
import ma.iibdcc.ebankingbackend.repositories.interfaces.ICustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ICustomerRepository customerRepository,
                            IBankAccountRepository bankAccountRepository,
                            IAccountOperationRepository accountOperationRepository){
        return args -> {
            Stream.of("Abdellah","Amine","Saad").forEach(name->{
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(cust -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*90000);
                currentAccount.setDateCreation(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()*90000);
                savingAccount.setDateCreation(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterestRate(4.5);
                bankAccountRepository.save(savingAccount);
            });
            bankAccountRepository.findAll().forEach(bankAccount -> {
                for (int i = 0; i < 5; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random()*10000);
                    accountOperation.setOperationType(Math.random()>0.5 ? OperationType.CREDIT:OperationType.DEBIT);
                    accountOperation.setBankaccount(bankAccount);
                    accountOperationRepository.save(accountOperation);
                }
            });
        };
    }
}
