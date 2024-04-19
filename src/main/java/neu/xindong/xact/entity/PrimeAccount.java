package neu.xindong.xact.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@TableName("prime_account")
@ToString
public class PrimeAccount {
    @TableId
    private Integer id;
    private Double balanceTotal;
    private Double balanceUsable;
    private Integer cuacctCls;
    private Date updateTime;
    private Integer password;

}
