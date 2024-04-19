package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orderInfo")
@Tag(name = "委托接口", description = "定义委托接口")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;
    @GetMapping("/getByPrimeAccountId")
    @Operation(summary = "根据主账户获取委托",
            description = "返回委托")
    public HttpResponse<List<OrderInfo>> getOrderInfoByPrimeAccountId(Integer primeAccountId) {
        try {
            List<OrderInfo> orderInfos = orderInfoService.findOrderInfoByPrimeAccountId(primeAccountId);
            return HttpResponse.success(orderInfos);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }

}
