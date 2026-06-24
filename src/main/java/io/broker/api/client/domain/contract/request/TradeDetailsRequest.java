package io.broker.api.client.domain.contract.request;

import lombok.Builder;
import lombok.Data;

/**
 * Trade details V1 request parameters
 */
@Data
@Builder(builderClassName = "Builder", toBuilder = true)
public class TradeDetailsRequest {

    /**
     * Start trade ID
     */
    private Long fromId;

    /**
     * End trade ID
     */
    private Long toId;

    /**
     * Start time (milliseconds timestamp)
     */
    private Long startTime;

    /**
     * End time (milliseconds timestamp)
     */
    private Long endTime;

    /**
     * Result limit (default 20, max 500)
     */
    private Integer limit;
}
