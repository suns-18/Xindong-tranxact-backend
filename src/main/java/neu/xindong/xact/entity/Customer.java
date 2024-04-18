package neu.xindong.xact.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@TableName("customer")
@ToString
public class Customer {
    @TableId
    private Integer id;
    private String customerName;
    private String idType;
    private String idNumber;
    private Integer cuacctCls;
    private Integer cuacctStatus;
}
