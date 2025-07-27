package ma.iibdcc.ebankingbackend.repositories.interfaces;

import ma.iibdcc.ebankingbackend.entities.Bankaccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBankAccountRepository extends JpaRepository<Bankaccount, String> {
}
