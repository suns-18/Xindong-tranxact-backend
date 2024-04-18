package neu.xindong.xact.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@TableName("stock")
@ToString
public class Stock {
    @TableId
    private String id;
    private String stockName;
    private Integer market;
    private String stkCls;
    private Double stamp;
    private String currency;
}
