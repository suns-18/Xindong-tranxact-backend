package neu.xindong.xact.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;


@Data
@TableName("follow_account")
@ToString
public class FollowAccount {
    @TableId
    private String id;
    private Integer primeAccountId;
    private Double balanceTotal;
    private Double balanceUsable;
    private Integer market;
    private Date updateTime;
}
