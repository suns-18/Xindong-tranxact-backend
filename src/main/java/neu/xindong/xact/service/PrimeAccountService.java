package neu.xindong.xact.service;

import com.baomidou.mybatisplus.extension.service.IService;
import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.entity.PrimeAccount;

public interface PrimeAccountService extends IService<PrimeAccount> {
    PrimeAccount findPrimeAccountByCustomerId(Integer customerId);
    boolean reduceBalanceUsableByOrder(OrderInfo orderInfo);

    boolean increaseBalanceUsableByOrder(OrderInfo orderInfo);

    boolean reduceBalanceTotalByDeal();

    boolean increaseBalanceTotalByDeal();
}
