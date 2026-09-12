package io.broker.api.client.domain.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeverageMergeResult {

    private Long accountId;

    private Boolean isCross;

    private Integer leverage;

    private Integer leverageLong;

    private Integer leverageShort;

    @JsonProperty("symbol_id")
    private String symbolId;
}
