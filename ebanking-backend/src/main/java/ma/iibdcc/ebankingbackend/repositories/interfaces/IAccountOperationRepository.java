package ma.iibdcc.ebankingbackend.repositories.interfaces;

import ma.iibdcc.ebankingbackend.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountOperationRepository extends JpaRepository<AccountOperation, Long> {
}
