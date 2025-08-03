package ma.iibdcc.ebankingbackend.repositories.interfaces;

import ma.iibdcc.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    @Query("select c from Customer c where c.name like :keyword")
    List<Customer> searchCustomer(@Param("keyword") String keyword);
}
