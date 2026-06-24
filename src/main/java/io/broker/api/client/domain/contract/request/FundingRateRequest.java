package io.broker.api.client.domain.contract.request;

import lombok.Builder;
import lombok.Data;

/**
 * Funding rate request parameters
 */
@Data
@Builder(builderClassName = "Builder", toBuilder = true)
public class FundingRateRequest {

    /**
     * Symbol
     */
    private String symbol;

    /**
     * State (default: current)
     */
    private String state;
}
