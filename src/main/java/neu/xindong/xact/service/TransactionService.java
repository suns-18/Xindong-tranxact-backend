package neu.xindong.xact.service;

import com.baomidou.mybatisplus.extension.service.IService;
import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.entity.Transaction;

import java.util.List;

public interface TransactionService extends IService<Transaction> {
    List<Transaction>findTranscationByPrimeAccountId(Integer primeAccountId);

    boolean doDeal(Transaction transaction, OrderInfo orderInfo);
}
