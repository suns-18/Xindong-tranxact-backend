package neu.xindong.xact.service;

import com.baomidou.mybatisplus.extension.service.IService;
import neu.xindong.xact.entity.Customer;

import java.util.List;

public interface CustomerService extends IService<Customer> {
    List<Customer>findAll();
    Customer findCustomerById(Integer id);

    List<Customer> findCustomerLikeId(Integer id);

}
