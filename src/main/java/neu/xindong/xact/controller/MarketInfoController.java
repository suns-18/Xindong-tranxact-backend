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

import java.util.List;

@RestController
@RequestMapping("/marketInfo")
public class MarketInfoController {
    @Autowired
    MarketInfoRepository repo;

    @GetMapping("/getByStockId")
    @Operation(summary = "获取股票对应的行情信息",
            description = "返回1条股票对应的行情信息")
    public HttpResponse<MarketInfo> getByStockId(@RequestParam String id) {
        try {
            return HttpResponse.success(
                    repo.findByStockCode(id).orElse(null));
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }
}
