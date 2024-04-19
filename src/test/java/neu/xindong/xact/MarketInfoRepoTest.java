package neu.xindong.xact;

import neu.xindong.xact.entity.MarketInfo;
import neu.xindong.xact.service.MarketInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

@SpringBootTest
public class MarketInfoRepoTest {
    Random random = new Random();

    @Autowired
    MarketInfoRepository repo;

    @Test
    void connect() {
        try {
            System.out.println("Count: " + repo.count());
            System.out.println("Test finished: connect");
        } catch (Exception e) {
            System.out.println("Test failed: connect." + e.getMessage());
        }
    }

    @Test
    void writeAndRead() {
        var DATA_COUNT = 100;
        //var DATA_PER_THREAD = 10;

        List<MarketInfo> list = new Vector<>();
        for (int i = 0; i < DATA_COUNT; i++) {
            list.add(generateTestData());
        }
        list.add(MarketInfo.builder().stockId("114514").build());

        repo.saveAll(list);

        var paged = repo.findAll(Pageable.unpaged());
        paged.forEach(System.out::println);
    }

    @Test
    void delete() {
        repo.deleteByStockCode("114514");

        System.out.println(repo.count());

        repo.deleteAll();

        var paged = repo.findAll();
        System.out.print("Remain = ");
        paged.forEach(System.out::println);
    }

    MarketInfo generateTestData() {
        return MarketInfo.builder()
                .currentPrice(randomDouble())
                .yesterdayCollectionPrice(randomDouble())
                .stockId(randomString(6))
                .limitUpPrice(randomDouble())
                .limitDownPrice(randomDouble())
                .marketTime(new Date(System.currentTimeMillis()))
                .build();
    }

    private Double randomDouble() {

        return random.nextDouble() * 100;
    }

    private String randomString(int length) {
        final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
