package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.entity.Transaction;
import neu.xindong.xact.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public HttpResponse<List<Transaction>> getTransactionsByPrimeAccountId(Integer primeAccountId) {
        try {
            List<Transaction> transactions=transactionService.findTranscationByPrimeAccountId(primeAccountId);
            return HttpResponse.success(transactions);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }
}
