package neu.xindong.xact.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@TableName("follow_account")
@ToString
public class FollowAccount {
    @TableId
    private String id;
    private Integer primeAccountId;
    private Double balance;
    private Integer market;
    private Timestamp updateTime;
}
