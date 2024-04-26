package neu.xindong.xact;

import neu.xindong.xact.entity.MarketInfo;
import neu.xindong.xact.service.MarketInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

//    @Test
//    void writeAndRead() {
//        var DATA_COUNT = 100;
//        //var DATA_PER_THREAD = 10;
//
//        List<MarketInfo> list = new Vector<>();
//        for (int i = 0; i < DATA_COUNT; i++) {
//            list.add(generateTestData());
//        }
//        list.add(MarketInfo.builder().stockId("114514").build());
//
//        repo.saveAll(list);
//
//        var paged = repo.findAll(Pageable.unpaged());
//        paged.forEach(System.out::println);
//    }

    @Test
    void delete() {
//        repo.deleteByStockCode("114514");
//
//        System.out.println(repo.count());

        repo.deleteAll();

        var paged = repo.findAll();
        System.out.print("Remain = ");
        paged.forEach(System.out::println);
    }
    @Test
    void generateData(){
        List<MarketInfo> marketInfoList = new ArrayList<>();
        // 假设获取当前时间为市场时间
        Date marketTime = new Date();
        // 添加数据到marketInfoList
        marketInfoList.add(new MarketInfo("000001", marketTime, 9.81, 9.67, 10.64, 8.7, 10000));
        marketInfoList.add(new MarketInfo("000651", marketTime, 36.98, 36.14, 39.75, 32.53, 10000));
        marketInfoList.add(new MarketInfo("000858", marketTime, 136.15, 133.59, 146.95, 120.23, 10000));
        marketInfoList.add(new MarketInfo("600446", marketTime, 12.85, 12.31, 13.54, 11.08, 10000));
        marketInfoList.add(new MarketInfo("600900", marketTime, 25.2, 24.64, 27.1, 22.18, 10000));
        marketInfoList.add(new MarketInfo("601857", marketTime, 8.65, 8.28, 9.11, 7.45, 10000));
        repo.saveAll(marketInfoList);
    }

//    MarketInfo generateTestData() {
//        return MarketInfo.builder()
//                .currentPrice(randomDouble())
//                .yesterdayCollectionPrice(randomDouble())
//                .stockId(randomString(6))
//                .limitUpPrice(randomDouble())
//                .limitDownPrice(randomDouble())
//                .marketTime(new Date(System.currentTimeMillis()))
//                .build();
//    }

//    private Double randomDouble() {
//
//        return random.nextDouble() * 100;
//    }
//
//    private String randomString(int length) {
//        final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        StringBuilder sb = new StringBuilder(length);
//        for (int i = 0; i < length; i++) {
//            sb.append(characters.charAt(random.nextInt(characters.length())));
//        }
//        return sb.toString();
//    }
}
