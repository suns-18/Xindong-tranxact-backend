package neu.xindong.xact;

import neu.xindong.xact.entity.Stock;
import neu.xindong.xact.service.impl.StockServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StockServiceTest {
    @Autowired
    private StockServiceImpl stockService;
    @Test
    public void findAllTest(){
        List<Stock>stocks=stockService.findAll();
        stocks.stream().forEach(System.out::println);
    }
}
