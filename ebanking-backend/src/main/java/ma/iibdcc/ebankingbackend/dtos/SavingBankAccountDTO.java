package ma.iibdcc.ebankingbackend.dtos;

import lombok.Data;
import ma.iibdcc.ebankingbackend.enums.AccountStatus;

import java.util.Date;

@Data
public class SavingBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date dateCreation;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private Double interestRate;
}
