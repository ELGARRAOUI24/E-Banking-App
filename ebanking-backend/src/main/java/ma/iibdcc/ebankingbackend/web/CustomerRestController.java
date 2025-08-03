package ma.iibdcc.ebankingbackend.web;

import lombok.AllArgsConstructor;
import ma.iibdcc.ebankingbackend.dtos.CustomerDTO;
import ma.iibdcc.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.iibdcc.ebankingbackend.services.IBankAccountService;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerRestController {
    private final PathMatcher pathMatcher;
    private IBankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers() {
        return bankAccountService.listCustomers();
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomerById(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(customerId);
    }

    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping("/customers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable(name = "id") Long customerId) {
        bankAccountService.deleteCustomer(customerId);
    }

    @GetMapping("/customers/search")
    public List<CustomerDTO> searchCustomer(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return bankAccountService.searchCustomers("%"+keyword+"%");
    }
}
