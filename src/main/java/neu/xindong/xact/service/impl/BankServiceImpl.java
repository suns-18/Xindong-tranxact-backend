package neu.xindong.xact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import neu.xindong.xact.dao.BankDao;
import neu.xindong.xact.entity.Bank;
import neu.xindong.xact.service.BankService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BankServiceImpl extends ServiceImpl<BankDao, Bank>
        implements BankService {

    @Override
    public List<Bank> findBankByCustomerId(Integer customerId) {
        return query().eq("customer_id",customerId).list();
    }
}
