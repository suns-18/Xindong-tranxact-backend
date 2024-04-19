package neu.xindong.xact.dto.response;
import neu.xindong.xact.entity.Position;

public class PositionResp {
    private Position position;//持仓信息
    private int frozenShareAmount;//股份冻结数量=委托表的委托数量
    private int unfrozenShareAmount;//股份解冻数量=委托表的委托数量-委托表的成交数量
}
