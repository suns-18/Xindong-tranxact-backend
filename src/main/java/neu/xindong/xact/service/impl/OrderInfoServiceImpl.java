package neu.xindong.xact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import neu.xindong.xact.dao.OrderInfoDao;
import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.service.OrderInfoService;

import java.util.List;

public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoDao, OrderInfo>
        implements OrderInfoService {
    @Override
    public List<OrderInfo> findOrderInfoByPrimeAccountId(Integer primeAccountId) {
        return query().eq("prime_account_id",primeAccountId).list();
    }
}
