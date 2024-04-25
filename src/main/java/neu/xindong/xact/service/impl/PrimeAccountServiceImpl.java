package neu.xindong.xact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import neu.xindong.xact.dao.PrimeAccountDao;
import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.entity.PrimeAccount;
import neu.xindong.xact.entity.Stock;
import neu.xindong.xact.entity.Transaction;
import neu.xindong.xact.service.PrimeAccountService;
import org.springframework.stereotype.Service;

@Service
public class PrimeAccountServiceImpl extends ServiceImpl<PrimeAccountDao, PrimeAccount>
        implements PrimeAccountService {
    @Override
    public PrimeAccount findPrimeAccountByCustomerId(Integer customerId) {
        return getById(customerId);
    }
    @Override
    //委托 更改资金
    //委托买，可用=可用余额-委托金额-佣金
    //委托卖，可用=可用-佣金
    public boolean reduceBalanceUsableByOrder(OrderInfo orderInfo) {
        PrimeAccount primeAccount=getById(orderInfo.getPrimeAccountId());
        if (orderInfo.getTrdId()=='B'){
            primeAccount.setBalanceUsable(primeAccount.getBalanceUsable()
                    - orderInfo.getOrderAmount()*orderInfo.getOrderPrice()
                    - orderInfo.getRate()*orderInfo.getOrderAmount()*orderInfo.getOrderPrice());
        }else {
            primeAccount.setBalanceUsable(primeAccount.getBalanceUsable()-
                    orderInfo.getRate()*orderInfo.getOrderAmount()*orderInfo.getOrderPrice());
        }
        return updateById(primeAccount);
    }

    @Override
    //撤单 更改资金
    //全部撤销买，可用=余额+委托金额
    //撤销卖，可用和余额都不变，所以不实现
    public boolean increaseBalanceUsableByOrder(OrderInfo orderInfo) {
        PrimeAccount primeAccount=getById(orderInfo.getPrimeAccountId());
        if(orderInfo.getTrdId()=='B'){
            primeAccount.setBalanceUsable(primeAccount.getBalanceUsable()+orderInfo.getOrderAmount()*orderInfo.getOrderPrice());
        }
        return updateById(primeAccount);
    }

    @Override
    public boolean changeBalanceTotalByDeal(Transaction transaction, OrderInfo orderInfo , Stock stock){
        PrimeAccount primeAccount = getById(orderInfo.getPrimeAccountId());
        if (orderInfo.getTrdId()=='B'){//部分成交买：余额=余额-成交金额，可用不变
            primeAccount.setBalanceTotal(primeAccount.getBalanceTotal()-transaction.getPrice()*transaction.getAmount());
        }else {//部分成交卖，余额=余额+成交金额-印花税，可用=可用+成交金额-印花税
            primeAccount.setBalanceUsable(primeAccount.getBalanceUsable()+transaction.getPrice()*transaction.getAmount()*(1-stock.getStamp()));
            primeAccount.setBalanceTotal(primeAccount.getBalanceTotal()+transaction.getPrice()*transaction.getAmount()*(1-stock.getStamp()));
        }
        return updateById(primeAccount);
    }



}
