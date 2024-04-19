package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.entity.FollowAccount;
import neu.xindong.xact.service.FollowAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/followAccount")
@Tag(name = "从账户接口", description = "定义从账户接口")
public class FollowAccountController {
    @Autowired
    private FollowAccountService followAccountService;

    @GetMapping("/getByPrimeAccountId")
    @Operation(summary = "根据主账户id获取从账户",
            description = "返回从账户列表")
    public HttpResponse<List<FollowAccount>> getFollowAccountByPrimeAccountId(Integer primeAccountId) {
        try {
            List<FollowAccount> followAccounts = followAccountService.findFollowAccountByPrimeAccountId(primeAccountId);
            return HttpResponse.success(followAccounts);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }

}
