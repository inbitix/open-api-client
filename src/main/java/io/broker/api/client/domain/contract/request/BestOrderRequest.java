package io.broker.api.client.domain.contract.request;

import lombok.Builder;
import lombok.Data;

/**
 * Best order request parameters
 */
@Data
@Builder(builderClassName = "Builder", toBuilder = true)
public class BestOrderRequest {

    /**
     * Symbol (required)
     */
    private String symbol;
}
