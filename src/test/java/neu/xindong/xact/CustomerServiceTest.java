package neu.xindong.xact;

import neu.xindong.xact.entity.Customer;
import neu.xindong.xact.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private CustomerServiceImpl customerService;
    @Test
    public void findAllTest(){
        List<Customer> customers=customerService.findAll();
        customers.forEach((customer -> {
            System.out.println(customer.toString());
        }));
    }

    @Test
    public void findCustomerById(){
        Customer customer=customerService.findCustomerById(31355654);
        System.out.println(customer.toString());
    }
}
