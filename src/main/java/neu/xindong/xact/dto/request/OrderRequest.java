package neu.xindong.xact.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import neu.xindong.xact.entity.OrderInfo;
@SuperBuilder
@Data
@AllArgsConstructor
public class OrderRequest {
    private Integer customerId;
    private OrderInfo orderInfo;
}
//@Data
//class Order{
//    private String followAccountId;
//    private char trdId;
//    private String stockId;
//    private Integer orderAmount;
//    private Double orderPrice;
//    private Integer unit;
//}
