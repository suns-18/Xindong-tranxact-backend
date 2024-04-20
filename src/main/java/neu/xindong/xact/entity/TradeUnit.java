package neu.xindong.xact.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@TableName("trade_unit")
@ToString
public class TradeUnit {
    @TableId
    private Integer id;
}
