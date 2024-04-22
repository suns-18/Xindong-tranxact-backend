package neu.xindong.xact;

import neu.xindong.xact.service.impl.PrimeAccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PrimeAccountServiceTest {
    @Autowired
    private PrimeAccountServiceImpl primeAccountService;
    @Test
    public void reduceBalanceUsableByOrderTest(){

    }

    @Test
    public void increaseBalanceUsableByOrderTest(){

    }

    @Test
    public void changeBalanceTotalByDealTest(){

    }
}
