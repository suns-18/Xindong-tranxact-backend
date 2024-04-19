package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.entity.Position;
import neu.xindong.xact.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/position")
@Tag(name = "持仓接口", description = "定义持仓接口")
public class PositionController {
    @Autowired
    private PositionService positionService;
    @GetMapping("/getByPrimeAccountId")
    @Operation(summary = "根据主账户获取持仓",
            description = "返回持仓")
    public HttpResponse<List<Position>> getPositionByPrimeAccountId(Integer primeAccountId) {
        try {
            List<Position> positions = positionService.findPositionByPrimeAccountId(primeAccountId);
            return HttpResponse.success(positions);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }

}
