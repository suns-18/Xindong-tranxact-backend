package neu.xindong.xact.service;

import com.baomidou.mybatisplus.extension.service.IService;
import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.entity.Transaction;

import java.util.List;

public interface OrderInfoService extends IService<OrderInfo> {
    List<OrderInfo> findOrderInfoByPrimeAccountId(Integer primeAccountId);

    List<OrderInfo> findOrderInfoByPrimeAccountIdToDeal(Integer primeAccountId);

    boolean doOrder(OrderInfo orderInfo);

    boolean withdrawOrder(OrderInfo orderInfo);

    boolean updateOrderInfoByDeal(OrderInfo orderInfo, Transaction transaction);
}
