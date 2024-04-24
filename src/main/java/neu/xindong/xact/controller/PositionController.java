package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.dto.response.PositionResp;
import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.entity.Position;
import neu.xindong.xact.service.OrderInfoService;
import neu.xindong.xact.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/position")
@Tag(name = "持仓接口", description = "定义持仓接口")
public class PositionController {
    @Autowired
    private PositionService positionService;
    @Autowired
    private OrderInfoService orderInfoService;


    @GetMapping("/getByPrimeAccountId")
    @Operation(summary = "根据主账户获取持仓",
            description = "返回持仓")
    public HttpResponse<List<PositionResp>> getPositionByPrimeAccountId(@RequestParam Integer primeAccountId) {
        try {
            List<PositionResp> positionResps = new ArrayList<>();
            List<Position> positions = positionService.findPositionByPrimeAccountId(primeAccountId);
            List<OrderInfo> orderInfos = orderInfoService.findOrderInfoByPrimeAccountId(primeAccountId);
            positions.forEach(position -> {
                orderInfos.forEach(orderInfo -> {
                    PositionResp positionResp=PositionResp.builder()
                            .position(position)
                            .frozenShareAmount(orderInfo.getOrderAmount())
                            .unfrozenShareAmount(orderInfo.getDealAmount())
                            .build();
                    positionResps.add(positionResp);
                });
            });
            return HttpResponse.success(positionResps);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failure(0, "数据库访问错误");
        }

    }

//    @GetMapping("/getByPrimeAccountId")
//    @Operation(summary = "根据主账户获取持仓",
//            description = "返回持仓")
//    public HttpResponse<List<PositionResp>> getPositionByPrimeAccountId(@RequestParam Integer primeAccountId) {
//        try {
//            List<PositionResp> positionResps = new ArrayList<>();
//            List<Position> positions = positionService.findPositionByPrimeAccountId(primeAccountId);
//            List<OrderInfo> orderInfos = orderInfoService.findOrderInfoByPrimeAccountId(primeAccountId);
//            // 将委托信息按照证券ID分组，并计算每个证券的冻结和解冻数量
//            Map<String, List<OrderInfo>> orderInfoMap = orderInfos.stream()
//                    .collect(Collectors.groupingBy(OrderInfo::getStockId));
//            // 遍历持仓信息，并根据委托信息计算冻结和解冻数量
//            for (Position position : positions) {
//                List<OrderInfo> relatedOrderInfos = orderInfoMap.get(position.getStockId());
//                int frozenShareAmount = 0;
//                int unfrozenShareAmount = position.getShareTotal(); // 假设初始解冻数量为持仓总数
//                if (relatedOrderInfos != null) {
//                    for (OrderInfo orderInfo : relatedOrderInfos) {
//                        // 未成交 '0',已报 '2' 撤单 '6', 部分成交 '7', 全部成交 '8'
//                        switch (orderInfo.getOrderStatus()) {
//                            case '2': //已报
//                                frozenShareAmount += orderInfo.getOrderAmount();//买股票，冻结股票数量改变但是可用没改总数也没改
//                                if (orderInfo.getTrdId()=='S'){//买股票的话，开始冻结，且可用变少，总的不变
//                                    position.setShareUsable(position.getShareUsable()-orderInfo.getOrderAmount());
//                                }
//                                break;
//                            case '6': // 撤单
//                                // 对于撤单的委托，不增加冻结数量
//                                frozenShareAmount = 0;
//                                if (orderInfo.getTrdId()=='B'){
//                                    position.setShareUsable(position.getShareUsable()+orderInfo.getOrderAmount());
//                                    position.setShareTotal(position.getShareTotal()+orderInfo.getOrderAmount());
//                                }else {
//                                    position.setShareUsable(position.getShareUsable()-orderInfo.getOrderAmount());
//                                    position.setShareTotal(position.getShareTotal()-orderInfo.getOrderAmount());
//                                }
//                                break;
//                            case '7': // 部分成交
//                                frozenShareAmount=orderInfo.getOrderAmount()-orderInfo.getDealAmount();
//                            case '8': // 全部成交
//                                // 对于部分成交或全部成交的委托，按照交易类型直接改变总股份数值。可用股份的话因为已经成交了，所以直接改变
//                                if (orderInfo.getTrdId()=='B'){
//                                    position.setShareUsable(position.getShareUsable()+orderInfo.getDealAmount());
//                                    position.setShareTotal(position.getShareTotal()+orderInfo.getDealAmount());
//                                }else {
//                                    position.setShareUsable(position.getShareUsable()-orderInfo.getDealAmount());
//                                    position.setShareTotal(position.getShareTotal()-orderInfo.getDealAmount());
//                                }
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                }
//                // 计算解冻数量，即持仓总数减去冻结数量
//                unfrozenShareAmount = position.getShareTotal() - frozenShareAmount;
//                // 构建 PositionResp 对象并添加到结果列表中
//                PositionResp positionResp = new PositionResp();
//                positionResp.setPosition(position);
//                positionResp.setFrozenShareAmount(frozenShareAmount);
//                positionResp.setUnfrozenShareAmount(unfrozenShareAmount);
//                positionResps.add(positionResp);
//            }
//            return HttpResponse.success();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return HttpResponse.failure(0, "数据库访问错误");
//        }
//
//    }


}

