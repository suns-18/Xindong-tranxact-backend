package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.dto.request.TransactionRequest;
import neu.xindong.xact.dto.response.TransactionResp;
import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.entity.Position;
import neu.xindong.xact.entity.Stock;
import neu.xindong.xact.entity.Transaction;
import neu.xindong.xact.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@Tag(name = "成交接口", description = "定义成交接口")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private PrimeAccountService primeAccountService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private StockService stockService;
    @GetMapping("/getByPrimeAccountId")
    @Operation(summary = "根据主账户获取成交信息",
            description = "返回成交信息")
    public HttpResponse<List<TransactionResp>> getTransactionsByPrimeAccountId(@RequestParam Integer primeAccountId) {
        try {
            List<Transaction> transactions = transactionService.findTranscationByPrimeAccountId(primeAccountId);
            List<TransactionResp>transactionResps=new ArrayList<>();
            transactions.forEach(transaction -> {
                OrderInfo orderInfo=orderInfoService.getById(transaction.getOrderId());
                var transactionResp=TransactionResp
                        .builder()
                        .transaction(transaction)
                        .primeAccountId(primeAccountId)
                        .followAccountId(orderInfo.getFollowAccountId())
                        .trdId(orderInfo.getTrdId())
                        .tradeUnit(orderInfo.getUnit())
                        .transactionBalance(transaction.getAmount()*transaction.getPrice())
                        .build();
                transactionResps.add(transactionResp);
            });
            return HttpResponse.success(transactionResps);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }
    @PostMapping("/doTransaction")
    @Operation(summary = "做成交",
            description = "做成交")
    public HttpResponse<Object> doTransaction(
            @RequestBody TransactionRequest transactionRequest){
        try {
            OrderInfo orderInfo=orderInfoService.getById(transactionRequest.getOrderInfo().getId());
            Stock stock=stockService.getById(orderInfo.getStockId());
            Position position=positionService.findPositionByStockId(stock.getId(),orderInfo.getPrimeAccountId());

            Transaction transaction = Transaction.builder()
                    .price(transactionRequest.getPrice())
                    .amount(transactionRequest.getAmount())
                    .build();
            transactionService.doDeal(transaction,orderInfo);
            //改完之后要重新读取
            Transaction transaction2=transactionService.getById(transaction.getId());
            primeAccountService.changeBalanceTotalByDeal(transaction2,orderInfo,stock);
            orderInfoService.updateOrderInfoByDeal(orderInfo,transaction2);
            //成交买
            if(orderInfo.getTrdId()=='B'){
                positionService.increaseShareByDeal(position,stock,orderInfo,transaction);
            }else{//成交卖
                positionService.reduceShareByDeal(position,transaction);
            }
            return HttpResponse.success();
        }catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failure(
                    0, "操作失败，数据库访问错误");
        }

    }
}
