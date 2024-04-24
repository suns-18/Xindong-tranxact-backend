package neu.xindong.xact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import neu.xindong.xact.dao.CustomerDao;
import neu.xindong.xact.entity.Customer;
import neu.xindong.xact.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, Customer>
        implements CustomerService {

    @Override
    public List<Customer> findAll() {
        return query().list();
    }

    @Override
    public Customer findCustomerById(Integer id) {
        return getById(id);
    }

    @Override
    public List<Customer> findCustomerLikeId(Integer id) {
        return query().like("id", id).list();
    }
}
