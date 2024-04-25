package neu.xindong.xact;

import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.entity.Position;
import neu.xindong.xact.entity.Stock;
import neu.xindong.xact.entity.Transaction;
import neu.xindong.xact.service.impl.OrderInfoServiceImpl;
import neu.xindong.xact.service.impl.PositionServiceImpl;
import neu.xindong.xact.service.impl.StockServiceImpl;
import neu.xindong.xact.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PositionServiceImplTest {
    @Autowired
    private PositionServiceImpl positionService;
    @Autowired
    private OrderInfoServiceImpl orderInfoService;
    @Autowired
    private StockServiceImpl stockService;
    @Autowired
    private TransactionServiceImpl transactionService;

    @Test
    public void findPositionByPrimeAccountIdTest(){
        List<Position>positions=positionService.findPositionByPrimeAccountId(31355654);
        positions.stream().forEach(System.out::println);
    }

    @Test
    public void findPositionByStockIdTest(){
        Position position=positionService.findPositionByStockId("600446",31355654);
        System.out.println(position.toString());
    }

    @Test
    public void reduceShareByOrderTest(){
        Position position=positionService.findPositionByStockId("000858",11111111);
        OrderInfo orderInfo=orderInfoService.getById(2);
        positionService.reduceShareByOrder(position,orderInfo);
    }

    @Test
    public void increaseShareByOrderTest(){
        Position position=positionService.findPositionByStockId("000858",11111111);
        positionService.increaseShareByOrder(position);
    }

    @Test
    public void increaseShareByDealTest(){
        Position position=Position.builder().build();
        Stock stock=stockService.getById(600900);
        OrderInfo orderInfo=orderInfoService.getById(3202759);
        Transaction transaction=transactionService.getById(1);
        positionService.increaseShareByDeal(position,stock,orderInfo,transaction);
    }

    @Test
    public void increaseShareByDealTest2(){
        Position position=positionService.getById(1);
        Stock stock=stockService.getById(600446);
        OrderInfo orderInfo=orderInfoService.getById(3);
        Transaction transaction=transactionService.getById(2);
        positionService.increaseShareByDeal(position,stock,orderInfo,transaction);
    }

    @Test
    public void reduceShareByDealTest(){
        Position position=positionService.getById(1);
        Transaction transaction=transactionService.getById(3);
        positionService.reduceShareByDeal(position,transaction);
    }
}
