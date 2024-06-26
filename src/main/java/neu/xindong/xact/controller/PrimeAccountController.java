package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.entity.PrimeAccount;
import neu.xindong.xact.service.PrimeAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/primeAccount")
@Tag(name = "主账户接口", description = "定义主账户接口")
public class PrimeAccountController {
    @Autowired
    private PrimeAccountService primeAccountService;
    @GetMapping("/getByCustomerId")
    @Operation(summary = "根据客户代码获取主账户", description = "返回主账户")
    public HttpResponse<PrimeAccount> getPrimeAccountByCustomerId(@RequestParam Integer customerId) {
        try {
            PrimeAccount primeAccount = primeAccountService.findPrimeAccountByCustomerId(customerId);
            return HttpResponse.success(primeAccount);
        } catch (Exception e) {
            return HttpResponse.failure(0, "数据库访问错误");
        }

    }

    @GetMapping("/generateId")
    @Operation(summary = "生成主账户ID", description = "返回生成的主账户ID")
    public HttpResponse<Integer>
    generatePrimeAccountByCustomerId() {
        try {
            var lastAccount = primeAccountService.findLastPrimeAccountOrderById();
            if (lastAccount == null) {
                //10000001 ->lastCustomer;
                return HttpResponse.success(44444444);
            } else {
                return HttpResponse.success(lastAccount.getId() + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }
}
