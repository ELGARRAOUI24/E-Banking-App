package ma.iibdcc.ebankingbackend.repositories.interfaces;

import ma.iibdcc.ebankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBankAccountRepository extends JpaRepository<BankAccount, String> {
    List<BankAccount> findByCustomer_Id(Long customerId);
}
