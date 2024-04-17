package neu.xindong.xact.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import neu.xindong.xact.entity.MarketInfo;

import java.util.List;
@SuperBuilder
@Data
public class MarketInfoResp {
    List<MarketInfo> list;
    String msg;
}
