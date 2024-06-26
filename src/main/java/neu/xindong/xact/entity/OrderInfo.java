package neu.xindong.xact.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Date;
@SuperBuilder
@AllArgsConstructor
@Data
@TableName("order_info")
@ToString
public class OrderInfo {
    @TableId
    private Integer id;
    private Integer unit;
    private  Integer primeAccountId;
    private String followAccountId;
    private String stkCls;
    private Double rate;
    private String trdId;
    private String stockId;
    private Date orderTime;
    private String orderStatus;
    private Integer orderAmount;
    private Double orderPrice;
    private Integer dealAmount;
    private Double dealPrice;
    private Integer isWithdraw;
    private Integer withdrawAmount;
}
