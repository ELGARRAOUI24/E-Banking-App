package ma.iibdcc.ebankingbackend.dtos;

import lombok.Data;

@Data
public class DebitCreditDTO {
    private String accountId;
    private Double amount;
    private String description;
}
