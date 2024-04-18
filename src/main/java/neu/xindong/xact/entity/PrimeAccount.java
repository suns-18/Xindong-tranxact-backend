package neu.xindong.xact.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@TableName("prime_account")
@ToString
public class PrimeAccount {
    @TableId
    private Integer id;
    private Double balanceTotal;
    private Double balanceUsable;
    private Integer cuacctCls;
    private Timestamp updateTime;
    private Integer password;

}
