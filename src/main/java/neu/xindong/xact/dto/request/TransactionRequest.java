package neu.xindong.xact.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import neu.xindong.xact.entity.OrderInfo;
@AllArgsConstructor
@Data
public class TransactionRequest {
    private OrderInfo orderInfo;
    private Integer amount;
    private Double price;
}
