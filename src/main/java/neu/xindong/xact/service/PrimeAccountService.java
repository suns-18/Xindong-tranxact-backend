package neu.xindong.xact.service;

import com.baomidou.mybatisplus.extension.service.IService;
import neu.xindong.xact.entity.PrimeAccount;

public interface PrimeAccountService extends IService<PrimeAccount> {
    PrimeAccount findPrimeAccountByCustomerId(Integer customerId);
}