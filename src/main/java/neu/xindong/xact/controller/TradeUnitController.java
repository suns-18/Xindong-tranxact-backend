package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.entity.TradeUnit;
import neu.xindong.xact.service.TradeUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tradeUnit")
@Tag(name = "交易单元接口", description = "定义交易单元接口")
public class TradeUnitController {
    @Autowired
    private TradeUnitService tradeUnitService;
    @GetMapping("/all")
    @Operation(summary = "获取交易单元",
            description = "返回所有交易单元")
    public HttpResponse<List<TradeUnit>> getAllTradeUnits() {
        try {
            List<TradeUnit> tradeUnits=tradeUnitService.findAll();
            return HttpResponse.success(tradeUnits);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }
}
