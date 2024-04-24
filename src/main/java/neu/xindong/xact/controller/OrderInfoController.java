package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.dto.request.OrderRequest;
import neu.xindong.xact.dto.response.OrderInfoResp;
import neu.xindong.xact.entity.*;
import neu.xindong.xact.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orderInfo")
@Tag(name = "委托接口", description = "定义委托接口")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private StockService stockService;
    @Autowired
    private CommissionService commissionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PrimeAccountService primeAccountService;

    @GetMapping("/getByPrimeAccountId")
    @Operation(summary = "根据主账户获取委托",
            description = "返回委托")
    public HttpResponse<List<OrderInfoResp>> getOrderInfoByPrimeAccountId(@RequestParam Integer primeAccountId) {
        try {
            List<OrderInfo> orderInfos = orderInfoService.findOrderInfoByPrimeAccountId(primeAccountId);
            List<OrderInfoResp>orderInfoResps=new ArrayList<>();
//            for(OrderInfo orderInfo:orderInfos){
//                Stock stock=stockService.findStockById(orderInfo.getStockId());
//                OrderInfoResp orderInfoResp=OrderInfoResp.builder()
//                        .orderInfo(orderInfo)
//                        .orderBalance(orderInfo.getOrderPrice()*orderInfo.getOrderPrice())
//                        .dealBalance(orderInfo.getDealPrice()*orderInfo.getDealAmount())
//                        .frozenBalance(orderInfo.getOrderPrice())
//                        .unfrozenBalance(orderInfo.getOrderPrice()-orderInfo.getDealPrice())
//                        .currency(stock.getCurrency())
//                        .build();
//                orderInfoResps.add(orderInfoResp);
//            }
            orderInfos.forEach((orderInfo -> {
                Stock stock=stockService.findStockById(orderInfo.getStockId());
                var orderInfoResp=OrderInfoResp.builder()
                        .orderInfo(orderInfo)
                        .orderBalance(orderInfo.getOrderPrice()*orderInfo.getOrderPrice())
                        .dealBalance(orderInfo.getDealPrice()*orderInfo.getDealAmount())
                        .frozenBalance(orderInfo.getOrderPrice())
                        .unfrozenBalance(orderInfo.getOrderPrice()-orderInfo.getDealPrice())
                        .currency(stock.getCurrency())
                        .build();
                orderInfoResps.add(orderInfoResp);
            }));
            return HttpResponse.success(orderInfoResps);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }

    @GetMapping("/doOrder")
    @Operation(summary = "做委托",
            description = "作委托")
    public HttpResponse<Object> doOrderByPrimeAccountId(@RequestBody OrderRequest orderRequest) {
        try {
            Stock stock = stockService.findStockById(orderRequest.getOrderInfo().getStockId());
            Customer customer = customerService.findCustomerById(orderRequest.getCustomerId());
            Commission commission = commissionService.findCommissionByCuacctclsAndMarket(customer.getCuacctCls());
            OrderInfo orderInfo = OrderInfo.builder()
                    .unit(orderRequest.getOrderInfo().getUnit())
                    .primeAccountId(orderRequest.getCustomerId())
                    .followAccountId(orderRequest.getOrderInfo().getFollowAccountId())
                    .stkCls(stock.getStkCls())
                    .rate(commission.getRate())
                    .trdId(orderRequest.getOrderInfo().getTrdId())
                    .stockId(stock.getId())
                    .orderAmount(orderRequest.getOrderInfo().getOrderAmount())
                    .orderPrice(orderRequest.getOrderInfo().getOrderPrice())
                    .build();
            orderInfoService.doOrder(orderInfo);
            primeAccountService.reduceBalanceUsableByOrder(orderInfo);
            return HttpResponse.success();
        }catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failure(0, "数据库访问错误");
        }
    }

    @GetMapping("/withdrawOrder")
    @Operation(summary = "撤销委托",
            description = "撤销委托")
    public HttpResponse<Object> withdrawOrderByPrimeAccountId(@RequestBody OrderRequest orderRequest) {
        try {
            OrderInfo orderInfo = orderInfoService.getById(orderRequest.getCustomerId());
            orderInfoService.withdrawOrder(orderInfo);
            if(orderRequest.getOrderInfo().getTrdId()=='B')
                primeAccountService.increaseBalanceUsableByOrder(orderInfo);
            return HttpResponse.success();
        }catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failure(0, "数据库访问错误");
        }
    }

}
