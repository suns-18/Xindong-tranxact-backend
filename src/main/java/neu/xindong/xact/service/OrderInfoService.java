package neu.xindong.xact.service;

import com.baomidou.mybatisplus.extension.service.IService;
import neu.xindong.xact.entity.OrderInfo;

import java.util.List;

public interface OrderInfoService extends IService<OrderInfo> {
    List<OrderInfo> findOrderInfoByPrimeAccountId(Integer primeAccountId);
}
