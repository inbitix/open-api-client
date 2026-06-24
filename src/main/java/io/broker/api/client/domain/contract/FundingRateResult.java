package io.broker.api.client.domain.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Current funding rate result
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FundingRateResult {

    /**
     * Symbol
     */
    private String symbol;

    /**
     * Settlement period start time
     */
    private Long intervalStart;

    /**
     * Settlement period end time
     */
    private Long intervalEnd;

    /**
     * Funding rate
     */
    private String rate;

    /**
     * Index price
     */
    private String index;
}
