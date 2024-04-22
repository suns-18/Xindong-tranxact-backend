package neu.xindong.xact;

import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.service.impl.OrderInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderInfoServiceTest {
    @Autowired
    private OrderInfoServiceImpl orderInfoService;
    @Test
    public void findOrderInfoByPrimeAccountIdTest(){
        List<OrderInfo>orderInfos=orderInfoService.findOrderInfoByPrimeAccountId(31355654);
        orderInfos.stream().forEach(System.out::println);
    }

    /*
    因为还要加持仓，所以委托的这个就先不测
     */
    @Test
    public void doOrderTest(){

    }
    /*
    因为还要加持仓，所以委托的这个就先不测
     */
    @Test
    public void withdrawOrderTest(){

    }
}
