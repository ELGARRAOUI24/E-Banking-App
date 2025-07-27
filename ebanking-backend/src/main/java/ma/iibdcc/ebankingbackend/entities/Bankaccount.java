package ma.iibdcc.ebankingbackend.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.iibdcc.ebankingbackend.enums.AccountStatus;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name = "TYPE", length = 2, discriminatorType = DiscriminatorType.STRING)
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class Bankaccount {
    @Id
    private String id;
    private double balance;
    private Date dateCreation;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankaccount")
    private List<AccountOperation> operations;
}
