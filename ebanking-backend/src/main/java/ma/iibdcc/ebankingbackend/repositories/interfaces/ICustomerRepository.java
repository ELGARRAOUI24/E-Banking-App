package ma.iibdcc.ebankingbackend.repositories.interfaces;

import ma.iibdcc.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
}
