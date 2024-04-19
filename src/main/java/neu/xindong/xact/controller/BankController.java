package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.entity.Bank;
import neu.xindong.xact.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/bank")
@Tag(name = "银行账号接口", description = "定义银行账号接口")
public class BankController {
    @Autowired
    private BankService bankService;
    @GetMapping("/getByCustomerId")
    @Operation(summary = "根据客户代码获取银行账号",
            description = "根据客户代码返回银行账号")
    public HttpResponse<List<Bank>> getBankByCustomerId(@RequestParam Integer customerId) {
        try {
            List<Bank> banks = bankService.findBankByCustomerId(customerId);
            return HttpResponse.success(banks);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }
}
