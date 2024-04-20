package neu.xindong.xact.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@Data
@TableName("follow_account")
@ToString
@AllArgsConstructor
public class FollowAccount {
    @TableId
    private String id;
    private Integer primeAccountId;
    private Double balanceTotal;//useless
    private Double balanceUsable;//useless
    private Integer market;
    private Date updateTime;
}
