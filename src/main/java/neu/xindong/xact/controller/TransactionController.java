package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.dto.request.TransactionRequest;
import neu.xindong.xact.dto.response.TransactionResp;
import neu.xindong.xact.entity.Transaction;
import neu.xindong.xact.service.TransactionService;
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
    @GetMapping("/getByPrimeAccountId")
    @Operation(summary = "根据主账户获取成交信息",
            description = "返回成交信息")
    public HttpResponse<List<TransactionResp>> getTransactionsByPrimeAccountId(@RequestParam Integer primeAccountId) {
        try {
            List<Transaction> transactions = transactionService.findTranscationByPrimeAccountId(primeAccountId);
            List<TransactionResp>transactionResps=new ArrayList<>();
            for(Transaction transaction:transactions){
                TransactionResp transactionResp=TransactionResp.builder()
                        .transaction(transaction)
                        .transactionBalance(transaction.getAmount()*transaction.getPrice())
                        .build();
                transactionResps.add(transactionResp);
            }
            return HttpResponse.success(transactionResps);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }
    @PostMapping("/saveTransaction")
    @Operation(summary = "保存成交信息",
            description = "输入成交的信息，提交到服务器，返回提交操作的结果")
    public HttpResponse<Object> saveTransaction(
            @RequestBody TransactionRequest transactionRequest){
        try {
            Transaction transaction = Transaction.builder()
                    .price(transactionRequest.getPrice())
                    .amount(transactionRequest.getAmount())
                    .transactTime(transactionRequest.getTransactTime())
                    .build();
            transactionService.save(transaction);
            return HttpResponse.success();
        }catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failure(
                    0, "操作失败，数据库访问错误");
        }

    }
}
