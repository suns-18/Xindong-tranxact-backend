package neu.xindong.xact.service;

import com.baomidou.mybatisplus.extension.service.IService;
import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.entity.PrimeAccount;
import neu.xindong.xact.entity.Stock;
import neu.xindong.xact.entity.Transaction;

public interface PrimeAccountService extends IService<PrimeAccount> {
    PrimeAccount findPrimeAccountByCustomerId(Integer customerId);
    boolean reduceBalanceUsableByOrder(OrderInfo orderInfo);

    boolean increaseBalanceUsableByOrder(OrderInfo orderInfo);

    boolean changeBalanceTotalByDeal(Transaction transaction, OrderInfo orderInfo, Stock stock);

    PrimeAccount findLastPrimeAccountOrderById();
}
