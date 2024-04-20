package neu.xindong.xact.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import neu.xindong.xact.entity.Transaction;

import java.util.Date;
@AllArgsConstructor
@Data
public class TransactionRequest {
//    private Integer id;
    private Integer orderId;
//    private Integer unit;
//    private Integer primeAccountId;
//    private String followAccountId;
//    private char trdId;
    private Integer amount;
    private Double price;
    private Date transactTime;
//    private String stockId;
}
