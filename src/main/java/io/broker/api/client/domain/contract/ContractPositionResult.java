package io.broker.api.client.domain.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractPositionResult {

    /**
     * Name of the contract.
     */
    private String symbol;

    /**
     * Position side.
     */
    private PositionSide side;

    /**
     * Average price for opening the position.
     */
    private String avgPrice;

    /**
     * Amount of contracts opened.
     */
    private String position;

    /**
     * Amount of contracts available to close.
     */
    private String available;

    /**
     * Leverage of the position.
     */
    private String leverage;

    /**
     * Last trade price of the symbol.
     */
    private String lastPrice;

    /**
     * Current position value.
     */
    private String positionValue;

    /**
     * Forced liquidation price.
     */
    private String flp;

    /**
     * Margin for this position.
     */
    private String margin;

    /**
     * Margin rate for current position.
     */
    private String marginRate;

    /**
     * Unrealized profit and loss for current position held.
     */
    private String unrealizedPnL;

    /**
     * Rate of return for the position.
     */
    private String profitRate;

    /**
     * Cumulative realized profit and loss for this symbol.
     */
    private String realizedPnL;

    /**
     * Mark price.
     */
    private String markPrice;

    /**
     * Index price.
     */
    private String indexPrice;

    /**
     * Estimated liquidation price.
     */
    private String liquidationPrice;

    /**
     * Margin mode: cross / isolated.
     */
    private String marginMode;

    /**
     * Position update time in milliseconds.
     */
    private Long updatedAt;

    /**
     * Position initial margin.
     */
    private String initialMargin;

    /**
     * Maintenance margin.
     */
    private String maintenanceMargin;
}
