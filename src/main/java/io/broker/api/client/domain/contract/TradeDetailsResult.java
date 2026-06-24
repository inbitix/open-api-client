package io.broker.api.client.domain.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Trade details result (V1/V2)
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeDetailsResult {

    /**
     * Trade ID
     */
    private Long tradeId;

    /**
     * Account ID
     */
    private Long accountId;

    /**
     * Order ID
     */
    private Long orderId;

    /**
     * Match order ID
     */
    private Long matchOrderId;

    /**
     * Symbol ID
     */
    private String symbolId;

    /**
     * Fee token
     */
    private String feeTokenId;

    /**
     * Trade direction
     */
    private String side;

    /**
     * Trade quantity
     */
    private String quantity;

    /**
     * Trade amount
     */
    private String amount;

    /**
     * Trade price
     */
    private String price;

    /**
     * Token fee
     */
    private String tokenFee;

    /**
     * Whether is maker
     */
    private Boolean isMarker;

    /**
     * Update time
     */
    private Long updatedAt;

    /**
     * Whether is close position
     */
    private Boolean isClose;

    /**
     * Extra info
     */
    private Long extraInfo;
}
