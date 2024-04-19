package neu.xindong.xact.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@TableName("transaction")
@ToString
public class Transaction {
    @TableId
    private Integer id;
    private Integer orderId;
    private Integer unit;
    private Integer primeAccountId;
    private String followAccountId;
    private char trdId;
    private Integer amount;
    private Double price;
    private Date transactTime;
    private String stockId;
}
