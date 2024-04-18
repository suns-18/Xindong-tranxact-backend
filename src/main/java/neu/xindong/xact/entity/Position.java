package neu.xindong.xact.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@TableName("position")
@ToString
public class Position {
    @TableId
    private Integer id;
    private Integer primeAmountId;
    private String followAccountId;
    private String stockId;
    private Integer market;
    private Timestamp updateTime;
    private Integer shareTotal;
    private Integer shareUsable;
}
