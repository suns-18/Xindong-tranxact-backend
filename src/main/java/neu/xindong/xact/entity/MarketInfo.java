package neu.xindong.xact.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;
import org.springframework.data.redis.core.index.Indexed;
@Data
@RedisHash("market_info")
public class MarketInfo {
    @Id
    private String stockCode;

    @Indexed
    private Date marketTime;
    private Double currentPrice;
    private Double yesterdayCollectionPrice;
    private Double limitUpPrice;
    private Double limitDownPrice;

}
