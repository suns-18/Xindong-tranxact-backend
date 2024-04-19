package neu.xindong.xact.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import neu.xindong.xact.entity.Transaction;
@SuperBuilder
@Data
public class TransactionResp {
    private Transaction transaction;
    private Double transactionBalance;//(#成交金额=成交数量*成交价格)
}
