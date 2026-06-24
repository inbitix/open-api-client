package io.broker.api.client.domain.contract.request;

import lombok.Builder;
import lombok.Data;

/**
 * History funding rate request parameters
 */
@Data
@Builder(builderClassName = "Builder", toBuilder = true)
public class HistoryFundingRateRequest {

    /**
     * Symbol
     */
    private String symbol;

    /**
     * Start record ID
     */
    private Long fromId;

    /**
     * End record ID
     */
    private Long endId;

    /**
     * Result limit (default 20)
     */
    private Integer limit;
}
