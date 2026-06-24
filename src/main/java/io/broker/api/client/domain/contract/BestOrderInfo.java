package io.broker.api.client.domain.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Best order information
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BestOrderInfo {

    /**
     * Time
     */
    private Long time;

    /**
     * Order ID
     */
    private Long orderId;

    /**
     * Account ID
     */
    private Long accountId;

    /**
     * Order price
     */
    private String price;

    /**
     * Original order quantity
     */
    private String origQty;

    /**
     * Order type
     */
    private String type;

    /**
     * Trade direction (BUY/SELL)
     */
    private String side;

    /**
     * Order status
     */
    private String status;
}
