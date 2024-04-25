package neu.xindong.xact;

import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.entity.Transaction;
import neu.xindong.xact.service.impl.OrderInfoServiceImpl;
import neu.xindong.xact.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionServiceTest {
    @Autowired
    private TransactionServiceImpl transactionService;
    @Autowired
    private OrderInfoServiceImpl orderInfoService;

    @Test
    public void findTranscationByPrimeAccountIdTest(){
        List<Transaction> transactions=transactionService.findTranscationByPrimeAccountId(31355654);
        transactions.stream().forEach(System.out::println);
    }

    @Test
    public void doDealTest(){
        Transaction transaction=Transaction.builder()
                .build();
        OrderInfo orderInfo=orderInfoService.getById(6);
        transactionService.doDeal(transaction,orderInfo);
    }

}
