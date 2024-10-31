package neu.xindong.xact.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Date;
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
    private Integer availableQuantity;
}
