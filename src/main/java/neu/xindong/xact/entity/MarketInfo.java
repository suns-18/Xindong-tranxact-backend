package neu.xindong.xact.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;
import org.springframework.data.redis.core.index.Indexed;
@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("market_info")
@SuperBuilder
public class MarketInfo {
    @Id
    private String stockId;

    @Indexed
    private Date marketTime;

    private Double currentPrice;
    private Double yesterdayCollectionPrice;
    private Double limitUpPrice;
    private Double limitDownPrice;
}
