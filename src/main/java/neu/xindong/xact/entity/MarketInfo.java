package neu.xindong.xact.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MarketInfo {
    private Double currentPrice;
    private Double yesterdayCollectionPrice;
    private String stockCode;
    private Double limitUpPrice;
    private Double limitDownPrice;
    private Date marketTime;
}
