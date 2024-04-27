package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.entity.MarketInfo;
import neu.xindong.xact.entity.Stock;
import neu.xindong.xact.service.MarketInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/marketInfo")
public class MarketInfoController {
    @Autowired
    MarketInfoRepository repo;

    @GetMapping("/getByStockId")
    @Operation(summary = "获取股票对应的行情信息",
            description = "返回1条股票对应的行情信息")
    public HttpResponse<MarketInfo> getByStockId(@RequestParam String id) {
        try {
            return HttpResponse.success(
                    repo.findByStockId(id).orElse(null));
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }

    @GetMapping("/init")
    @Operation(summary = "初始化行情信息",
            description = "初始化行情信息，返回操作结果")
    public HttpResponse<Object> init(@RequestParam String id) {
        try {
            List<MarketInfo> marketInfoList = new ArrayList<>();

            Date marketTime = new Date();

            marketInfoList.add(new MarketInfo("000001", marketTime, 9.81, 9.67, 10.64, 8.7, 10000));
            marketInfoList.add(new MarketInfo("000651", marketTime, 36.98, 36.14, 39.75, 32.53, 10000));
            marketInfoList.add(new MarketInfo("000858", marketTime, 136.15, 133.59, 146.95, 120.23, 10000));
            marketInfoList.add(new MarketInfo("600446", marketTime, 12.85, 12.31, 13.54, 11.08, 10000));
            marketInfoList.add(new MarketInfo("600900", marketTime, 25.2, 24.64, 27.1, 22.18, 10000));
            marketInfoList.add(new MarketInfo("601857", marketTime, 8.65, 8.28, 9.11, 7.45, 10000));
            repo.saveAll(marketInfoList);
            return HttpResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }


}
