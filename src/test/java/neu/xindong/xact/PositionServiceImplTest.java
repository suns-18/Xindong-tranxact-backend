package neu.xindong.xact;

import neu.xindong.xact.entity.Position;
import neu.xindong.xact.service.impl.PositionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PositionServiceImplTest {
    @Autowired
    private PositionServiceImpl positionService;

    @Test
    public void findPositionByPrimeAccountIdTest(){
        List<Position>positions=positionService.findPositionByPrimeAccountId(31355654);
        positions.stream().forEach(System.out::println);
    }

    @Test
    public void findPositionByStockIdTest(){

    }

    @Test
    public void reduceShareByOrderTest(){

    }

    @Test
    public void increaseShareByOrderTest(){

    }

    @Test
    public void increaseShareByDealTest(){

    }

    @Test
    public void reduceShareByDealTest(){

    }
}
