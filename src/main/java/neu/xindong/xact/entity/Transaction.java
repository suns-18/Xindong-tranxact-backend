package neu.xindong.xact.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Date;
@SuperBuilder
@Data
@TableName("transaction")
@ToString
public class Transaction {
    @TableId
    private Integer id;
    private Integer orderId;
    private Integer unit;
    private Integer amount;
    private Double price;
    private Date transactTime;
    private String stockId;
}
