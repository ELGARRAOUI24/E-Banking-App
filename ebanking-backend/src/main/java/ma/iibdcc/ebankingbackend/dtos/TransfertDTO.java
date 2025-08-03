package ma.iibdcc.ebankingbackend.dtos;

import lombok.Data;

@Data
public class TransfertDTO {
    private String accountIdSource;
    private String accountIdDestination;
    private Double amount;
}
