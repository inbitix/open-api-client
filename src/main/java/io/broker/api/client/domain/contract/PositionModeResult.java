package io.broker.api.client.domain.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionModeResult {

    private Boolean isCross;

    private Boolean switchFlag;

    private Boolean success;

    @JsonProperty("symbol_id")
    private String symbolId;
}
