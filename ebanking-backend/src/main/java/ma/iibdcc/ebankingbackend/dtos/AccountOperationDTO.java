package ma.iibdcc.ebankingbackend.dtos;

import lombok.Data;
import ma.iibdcc.ebankingbackend.enums.OperationType;

import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType operationType;
    private String description;
}
