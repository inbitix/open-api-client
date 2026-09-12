package io.broker.api.client.domain.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateLeverageMergeResult {

    private Boolean success;
}
