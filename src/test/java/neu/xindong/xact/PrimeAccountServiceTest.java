package neu.xindong.xact;

import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.entity.PrimeAccount;
import neu.xindong.xact.service.impl.OrderInfoServiceImpl;
import neu.xindong.xact.service.impl.PrimeAccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeAccountServiceTest {
    @Autowired
    private PrimeAccountServiceImpl primeAccountService;
    @Autowired
    private OrderInfoServiceImpl orderInfoService;
    @Test
    public void findPrimeAccountByCustomerIdTest(){
        PrimeAccount primeAccount=primeAccountService.findPrimeAccountByCustomerId(31355654);
        System.out.println(primeAccount.toString());
    }
    @Test
    public void reduceBalanceUsableByOrderTest(){
        OrderInfo orderInfo=orderInfoService.getById(1);
        PrimeAccount primeAccount=primeAccountService.findPrimeAccountByCustomerId(orderInfo.getPrimeAccountId());
        System.out.println(primeAccount);
        if(primeAccountService.reduceBalanceUsableByOrder(orderInfo)) {
            PrimeAccount primeAccount2=primeAccountService.findPrimeAccountByCustomerId(orderInfo.getPrimeAccountId());
            System.out.println(primeAccount2);
        }
    }

    @Test
    public void increaseBalanceUsableByOrderTest(){
        OrderInfo orderInfo=orderInfoService.getById(1);
        PrimeAccount primeAccount=primeAccountService.findPrimeAccountByCustomerId(orderInfo.getPrimeAccountId());
        System.out.println(primeAccount);
        if(primeAccountService.increaseBalanceUsableByOrder(orderInfo)) {
            PrimeAccount primeAccount2=primeAccountService.findPrimeAccountByCustomerId(orderInfo.getPrimeAccountId());
            System.out.println(primeAccount2);
        }
    }

//    @Test
//    public void changeBalanceTotalByDealTest(){
//
//    }
}
