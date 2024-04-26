package neu.xindong.xact.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import neu.xindong.xact.entity.Transaction;
@SuperBuilder
@Data
public class TransactionResp {
    private Transaction transaction;
    private Integer primeAccountId;
    private String followAccountId;
    private String trdId;
    private Integer tradeUnit;
    private Double transactionBalance;//(#成交金额=成交数量*成交价格)
}
