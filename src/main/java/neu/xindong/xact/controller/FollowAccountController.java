package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.dto.request.OrderRequest;
import neu.xindong.xact.dto.response.AccountsResp;
import neu.xindong.xact.entity.FollowAccount;
import neu.xindong.xact.entity.PrimeAccount;
import neu.xindong.xact.service.FollowAccountService;
import neu.xindong.xact.service.PrimeAccountService;
import neu.xindong.xact.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/followAccount")
@Tag(name = "从账户接口", description = "定义从账户接口")
public class FollowAccountController {
    @Autowired
    private FollowAccountService followAccountService;
    @Autowired
    private PrimeAccountService primeAccountService;
    @Autowired
    private StockService stockService;

    @GetMapping("/getByPrimeAccountId")
    @Operation(summary = "根据主账户id获取从账户",
            description = "返回从账户列表")
    public HttpResponse<List<FollowAccount>> getFollowAccountByPrimeAccountId(@RequestParam Integer primeAccountId) {
        try {
            List<FollowAccount> followAccounts = followAccountService.findFollowAccountByPrimeAccountId(primeAccountId);
            return HttpResponse.success(followAccounts);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }
    @PostMapping("/getAccountsByCustomerAndStock")
    @Operation(summary = "根据客户ID和股票代码获取主账户资产和对应附账户",
            description = "返回账户")
    public HttpResponse<AccountsResp> getAccountsByCustomerAndStock(@RequestBody OrderRequest orderRequest){
        try {
            PrimeAccount primeAccount=primeAccountService.findPrimeAccountByCustomerId(orderRequest.getCustomerId());
            int market=stockService.getById(orderRequest.getOrderInfo().getStockId()).getMarket();
            FollowAccount followAccount=followAccountService.findFollowAccountByPrimeAccountIdAndMarket(orderRequest.getCustomerId(),market);
            AccountsResp accountsResp=AccountsResp.builder().
                    primeAccount(primeAccount).
                    followAccount(followAccount).
                    build();
            return HttpResponse.success(accountsResp);
        } catch (Exception e) {
            return HttpResponse.failure(0, "数据库访问错误");
        }
    }

}
