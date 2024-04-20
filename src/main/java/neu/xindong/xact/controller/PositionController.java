//package neu.xindong.xact.controller;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import neu.xindong.xact.dto.HttpResponse;
//import neu.xindong.xact.dto.response.PositionResp;
//import neu.xindong.xact.entity.Position;
//import neu.xindong.xact.service.PositionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/position")
//@Tag(name = "持仓接口", description = "定义持仓接口")
//public class PositionController {
//    @Autowired
//    private PositionService positionService;
//    @GetMapping("/getByPrimeAccountId")
//    @Operation(summary = "根据主账户获取持仓",
//            description = "返回持仓")
//    public HttpResponse<List<PositionResp>> getPositionByPrimeAccountId(@RequestParam Integer primeAccountId) {
//        try {
//            List<PositionResp>positionResps=new ArrayList<>();
//            List<Position> positions = positionService.findPositionByPrimeAccountId(primeAccountId);
//            for(Position position:positions){
//                PositionResp positionResp=PositionResp.builder()
//                        .position(position)
//                        .frozenShareAmount()
//                        .unfrozenShareAmount()
//            }
//            return HttpResponse.success(positionResps);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return HttpResponse.failureWhenAccessDB();
//        }
//    }
//
//}
