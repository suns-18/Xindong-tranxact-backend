package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.entity.Customer;
import neu.xindong.xact.entity.Stock;
import neu.xindong.xact.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@Tag(name = "股票接口", description = "定义股票接口")
public class StockController {
    @Autowired
    private StockService stockService;
    @GetMapping("/all")
    @Operation(summary = "获取股票列表",
            description = "返回所有股票")
    public HttpResponse<List<Stock>> getAllStock() {
        try {
            List<Stock> stocks = stockService.findAll();
            return HttpResponse.success(stocks);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }

    @GetMapping("/getById")
    @Operation(summary = "根据id获取股票", description = "返回单个股票")
    public HttpResponse<Stock> getStockById(@RequestParam String id) {
        try {
            Stock stock=stockService.getById(id);
            return HttpResponse.success(stock);
        } catch (Exception e) {
            return HttpResponse.failure(0, "数据库访问错误");
        }

    }

    @GetMapping("/getLikeId")
    @Operation(summary = "根据id模糊查询股票", description = "返回满足模糊匹配原则的id对应股票")
    public HttpResponse<List<Stock>> getStockLikeId(@RequestParam String id) {
        try {
            return HttpResponse.success(stockService.findStockLikeId(id));
        } catch (Exception e) {
            return HttpResponse.failureWhenAccessDB();
        }
    }

}
