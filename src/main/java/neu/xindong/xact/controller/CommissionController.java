package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.entity.Commission;
import neu.xindong.xact.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/commission")
@Tag(name = "佣金接口", description = "定义佣金接口")
public class CommissionController {
    @Autowired
    private CommissionService commissionService;
    @GetMapping("/getByCuacctclsAndMarket")
    @Operation(summary = "根据资产账户类别获取佣金",
            description = "根据资产账户类别返回佣金")
    public HttpResponse<Commission> getCommissionByCuacctclsAndMarket(@RequestParam Integer cuacctCls) {
        try {
            Commission commission = commissionService.findCommissionByCuacctclsAndMarket(cuacctCls);
            return HttpResponse.success(commission);
        } catch (Exception e) {
            return HttpResponse.failure(0, "数据库访问错误");
        }
    }
}
