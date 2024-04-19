package neu.xindong.xact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import neu.xindong.xact.dao.TransactionDao;
import neu.xindong.xact.service.TransactionService;
import neu.xindong.xact.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionDao, Transaction>
        implements TransactionService{
    @Override
    public List<Transaction> findTranscationByPrimeAccountId(Integer primeAccountId) {
        return query().eq("prime_account_id",primeAccountId).list();
    }

}
