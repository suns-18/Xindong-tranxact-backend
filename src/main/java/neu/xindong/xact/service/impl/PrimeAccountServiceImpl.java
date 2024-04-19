package neu.xindong.xact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import neu.xindong.xact.dao.PrimeAccountDao;
import neu.xindong.xact.entity.PrimeAccount;
import neu.xindong.xact.service.PrimeAccountService;

public class PrimeAccountServiceImpl extends ServiceImpl<PrimeAccountDao, PrimeAccount>
        implements PrimeAccountService {
    @Override
    public PrimeAccount findPrimeAccountByCustomerId(Integer customerId) {
        return getById(customerId);
    }
}
