package neu.xindong.xact.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@TableName("bank")
@ToString
public class Bank {
    @TableId
    private Integer id;
    private String bankName;
    private String account;
    private Integer password;
    private Integer customerId;
}
