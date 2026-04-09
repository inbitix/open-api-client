package io.broker.api.client.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(builderClassName = "Builder", toBuilder = true)
public class BalanceFlowNewResult {
    private Long accountId;

    private Long balanceId;

    private String tokenId;

    private Integer subjectId;

    private Long subjectExtId;

    private BigDecimal changed;

    private BigDecimal total;

    private Long ticketId = 0L;

    private Long offsetId;

    private Long createdAt;
}
