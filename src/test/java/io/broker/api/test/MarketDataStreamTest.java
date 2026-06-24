package io.broker.api.test;

import io.broker.api.client.BrokerApiClientFactory;
import io.broker.api.client.BrokerApiWebSocketClient;
import io.broker.api.client.domain.market.CandlestickInterval;
import io.broker.api.client.constant.Constants;

public class MarketDataStreamTest {

    public static void main(String[] args) {

        BrokerApiWebSocketClient client = BrokerApiClientFactory
                .newInstance(Constants.API_BASE_URL)
                .newWebSocketClient(Constants.WS_API_BASE_URL, Constants.WS_API_USER_URL);

        // depth
        client.onDepthEvent("BTC-SWAP-USDT", System.out::println);

        // kline
        client.onCandlestickEvent("BTC-SWAP-USDT", CandlestickInterval.ONE_MINUTE, System.out::println);

        // trades
        client.onTradeEvent("BTC-SWAP-USDT", System.out::println);

        // ticker for 24 hour
        client.onTicker24HourEvent("BTC-SWAP-USDT", System.out::println);

        // index
        client.onIndexEvent("BTC-SWAP-USDT", System.out::println);
    }
}
