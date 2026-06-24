package io.broker.api.client.domain.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * History funding rate result
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryFundingRateResult {

    /**
     * Record ID
     */
    private Long id;

    /**
     * Symbol
     */
    private String symbol;

    /**
     * Funding rate settlement time
     */
    private Long settleTime;

    /**
     * Funding rate
     */
    private String settleRate;
}
