package neu.xindong.xact.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@TableName("commission")
@ToString
public class Commission {
    @TableId
    private Integer id;
    private Integer cuacctCls;
    private Integer market;
    private String stkCls;
    private Double rate;
    private Integer primeAccountId;
}
