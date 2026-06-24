package io.broker.api.client.domain.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Best order result
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BestOrderResult {

    /**
     * Current price
     */
    private String price;

    /**
     * Best bid order
     */
    private BestOrderInfo bid;

    /**
     * Best ask order
     */
    private BestOrderInfo ask;
}
