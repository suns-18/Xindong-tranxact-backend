package neu.xindong.xact;

import neu.xindong.xact.entity.Commission;
import neu.xindong.xact.service.impl.CommissionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommissionServiceTest {
    @Autowired
    private CommissionServiceImpl commissionService;
    @Test
    public void findCommissionByCuacctclsAndMarketTest(){
        Commission commission=commissionService.findCommissionByCuacctclsAndMarket(0);
        System.out.println(commission.toString());

        commission=commissionService.findCommissionByCuacctclsAndMarket(1);
        System.out.println(commission.toString());

        commission=commissionService.findCommissionByCuacctclsAndMarket(2);
        System.out.println(commission.toString());

        commission=commissionService.findCommissionByCuacctclsAndMarket(3);
        System.out.println(commission.toString());
    }
}
