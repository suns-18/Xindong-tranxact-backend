package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.dto.response.OrderInfoResp;
import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public HttpResponse<List<OrderInfoResp>> getOrderInfoByPrimeAccountId(@RequestParam Integer primeAccountId) {
        try {
            List<OrderInfo> orderInfos = orderInfoService.findOrderInfoByPrimeAccountId(primeAccountId);
            List<OrderInfoResp>orderInfoResps=new ArrayList<>();
            for(OrderInfo orderInfo:orderInfos){
                OrderInfoResp orderInfoResp=OrderInfoResp.builder()
                        .orderInfo(orderInfo)
                        .orderBalance(orderInfo.getOrderPrice()*orderInfo.getOrderPrice())
                        .dealBalance(orderInfo.getDealPrice()*orderInfo.getDealAmount())
                        .frozenBalance(orderInfo.getOrderPrice())
                        .unfrozenBalance(orderInfo.getOrderPrice()-orderInfo.getDealPrice())
                        .build();
                orderInfoResps.add(orderInfoResp);
            }
            return HttpResponse.success(orderInfoResps);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }

}
