package neu.xindong.xact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import neu.xindong.xact.dao.PrimeAccountDao;
import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.entity.PrimeAccount;
import neu.xindong.xact.service.PrimeAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrimeAccountServiceImpl extends ServiceImpl<PrimeAccountDao, PrimeAccount>
        implements PrimeAccountService {
    @Override
    public PrimeAccount findPrimeAccountByCustomerId(Integer customerId) {
        return getById(customerId);
    }

    @Override
    //买，可用=余额-委托金额
    public boolean reduceBalanceUsableByOrder(OrderInfo orderInfo) {
        PrimeAccount primeAccount=getById(orderInfo.getPrimeAccountId());
        primeAccount.setBalanceUsable(primeAccount.getBalanceUsable()-orderInfo.getOrderAmount()*orderInfo.getOrderPrice());
        return updateById(primeAccount);
    }
    //全部撤销买，可用=余额+委托金额


    //部分成交买：余额=余额-成交金额，可用不变



}
