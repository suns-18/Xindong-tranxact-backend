package neu.xindong.xact;

import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.entity.Transaction;
import neu.xindong.xact.service.impl.OrderInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderInfoServiceTest {
    @Autowired
    private OrderInfoServiceImpl orderInfoService;
    @Test
    public void findOrderInfoByPrimeAccountIdTest() {
        List<OrderInfo> orderInfos = orderInfoService.findOrderInfoByPrimeAccountId(31355654);
        orderInfos.stream().forEach(System.out::println);
    }

    @Test
    public void doOrderTest() {
        OrderInfo orderInfo = OrderInfo.builder()
                .id(5)
                .build();
        orderInfoService.doOrder(orderInfo);
    }

    @Test
    public void withdrawOrderTest() {
        OrderInfo orderInfo = OrderInfo.builder()
                .id(1)
                .build();
        orderInfoService.withdrawOrder(orderInfo);
    }

    @Test
    public void updateOrderInfoByDealTest() {
        Transaction transaction = Transaction.builder()
                .amount(1)
                .price(137.0)
                .build();
        OrderInfo orderInfo = OrderInfo.builder()
                .id(2)
                .build();
        orderInfoService.updateOrderInfoByDeal(orderInfo, transaction);

    }
}
