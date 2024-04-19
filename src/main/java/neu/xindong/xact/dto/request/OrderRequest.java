package neu.xindong.xact.dto.request;

import neu.xindong.xact.entity.OrderInfo;

import java.util.Date;

public class OrderRequest {
    private Integer customerId;
    private Order Order;
    static class Order{
        private String followAccountId;
        private char trdId;
        private String stockId;
        private Integer orderAmount;
        private Double orderPrice;
    }
}
