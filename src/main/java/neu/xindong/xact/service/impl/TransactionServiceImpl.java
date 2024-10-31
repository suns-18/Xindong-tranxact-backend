package neu.xindong.xact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import neu.xindong.xact.dao.TransactionDao;
import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.entity.Transaction;
import neu.xindong.xact.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionDao, Transaction>
        implements TransactionService{
    @Override
    public List<Transaction> findTranscationByPrimeAccountId(Integer primeAccountId) {
        return query().eq("prime_account_id",primeAccountId).list();
    }

    @Override
    public boolean doDeal(Transaction transaction, OrderInfo orderInfo) {
        transaction.setPrimeAccountId(orderInfo.getPrimeAccountId());
        transaction.setOrderId(orderInfo.getId());
        transaction.setTransactTime(new Date());
        return save(transaction);
    }

}
