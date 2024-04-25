package neu.xindong.xact;

import neu.xindong.xact.entity.TradeUnit;
import neu.xindong.xact.service.impl.TradeUnitServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TradeUnitServiceTest {
    @Autowired
    private TradeUnitServiceImpl tradeUnitService;
    @Test
    public void findAllTest(){
        List<TradeUnit>tradeUnits=tradeUnitService.findAll();
        tradeUnits.stream().forEach(System.out::println);
    }
}
