package io.broker.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ticketInfo 成交推送事件
 * [{"e":"ticketInfo","E":1786377181495,"s":"BNB-SWAP-USDT","q":"1","t":1786377181495,"p":"600.97","T":2278691388218753025,"o":2278691316614380032,"c":"amm-xxx","O":2278691387825273344,"a":2253760758625152514,"A":2259488144750614018}]
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SocketTicketInfo {

    @JsonProperty("e")
    private String eventType;

    @JsonProperty("E")
    private Long eventTime;

    @JsonProperty("s")
    private String symbol;

    @JsonProperty("q")
    private String quantity;

    @JsonProperty("t")
    private Long time;

    @JsonProperty("p")
    private String price;

    @JsonProperty("T")
    private Long ticketId;

    @JsonProperty("o")
    private Long orderId;

    @JsonProperty("c")
    private String clientOrderId;

    @JsonProperty("O")
    private Long matchOrderId;

    @JsonProperty("a")
    private Long accountId;

    @JsonProperty("A")
    private Long matchAccountId;
}
