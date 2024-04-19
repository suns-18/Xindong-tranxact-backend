package neu.xindong.xact.dto.response;

import neu.xindong.xact.entity.OrderInfo;

public class OrderInfoResp {
    private OrderInfo orderInfo;//委托信息
    private Double orderBalance;//委托金额=委托数量*委托价格
    private Double dealBalance;//成交金额=成交数量*成交价格
    private Double frozenBalance;//冻结金额=委托金额
    private Double unfrozenBalance;//解冻金额=委托金额-成交金额

}
