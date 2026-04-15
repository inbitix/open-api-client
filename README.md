# open-api-client

`open-api-client` is a Java SDK wrapper for Broker OpenAPI. It provides:

- Synchronous REST calls (Retrofit + OkHttp)
- WebSocket market and user stream subscriptions
- Automatic API key injection and HMAC-SHA256 signing
- Request/response domain models for common trading scenarios

This project is suitable as an SDK foundation layer for wallet or trading systems integrating with Broker OpenAPI.

## Stack

- Java 8
- Maven
- Retrofit 2.5.0
- OkHttp
- Jackson
- Lombok
- JUnit 4

## Maven Coordinates

Current coordinates in `pom.xml`:

- `groupId`: `io.broker.api`
- `artifactId`: `broker-api-client`
- `version`: `3.2.2`

## Project Layout

```text
open-api-client
├── src/main/java/io/broker/api/client
│   ├── BrokerApiClientFactory.java        # Client factory
│   ├── BrokerApiRestClient.java           # REST API facade
│   ├── BrokerApiWebSocketClient.java      # WebSocket facade
│   ├── service/BrokerApiService.java      # Retrofit API mappings
│   ├── impl/                              # REST/WS implementations
│   ├── security/                          # API key + HMAC signer
│   ├── domain/                            # Request/response models
│   └── constant/                          # Constants
└── src/test/java/io/broker/api/test       # Integration examples/tests
```

## Core Features

### 1) REST client (blocking)

`BrokerApiRestClient` wraps the major endpoints:

- General: `ping`, `getServerTime`, `getBrokerInfo`
- Market: `getOrderBook`, `getTrades`, `getCandlestickBars`, `get24HrPriceStatistics`, `getPrice`, `getBookTicker`, `getIndex`
- Account: `newOrder`, `cancelOrder`, `getOrderStatus`, `getOpenOrders`, `getHistoryOrders`, `getAccount`, `getMyTrades`, `getDepositOrders`
- User stream management: `startUserDataStream`, `keepAliveUserDataStream`, `closeUserDataStream`
- Extension: `getBalanceFlow`

### 2) WebSocket client

`BrokerApiWebSocketClient` supports:

- Depth stream: `onDepthEvent`
- Kline stream: `onCandlestickEvent`
- Trade stream: `onTradeEvent`
- 24h ticker stream: `onTicker24HourEvent`
- Index stream: `onIndexEvent`
- User stream: `onUserEvent`

Optional auto-reconnect is available via `autoRetry`, with built-in heartbeat handling.

### 3) Authentication and signature

`AuthenticationInterceptor` automatically:

- Adds `X-BH-APIKEY`
- Adds `timestamp` for signed requests if missing
- Signs query payload with HMAC-SHA256 and appends `signature`

## Quick Start

### 1) Create client

```java
import io.broker.api.client.BrokerApiClientFactory;
import io.broker.api.client.BrokerApiRestClient;

String baseUrl = "https://openapi.inbitix.com/openapi";
String apiKey = "your_api_key";
String secret = "your_secret";

BrokerApiClientFactory factory = BrokerApiClientFactory.newInstance(baseUrl, apiKey, secret);
BrokerApiRestClient restClient = factory.newRestClient();
```

### 2) Call market endpoints

```java
restClient.ping();
Long serverTime = restClient.getServerTime();
System.out.println("serverTime=" + serverTime);
```

### 3) Place an order

```java
import io.broker.api.client.domain.account.NewOrder;
import io.broker.api.client.domain.account.NewOrderResponse;
import io.broker.api.client.domain.account.TimeInForce;

NewOrderResponse resp = restClient.newOrder(
    NewOrder.limitBuy("BTCUSDT", TimeInForce.GTC, "0.01", "66000")
);
System.out.println(resp);
```

### 4) WebSocket Subscription

#### Market Data Subscription

```java
import io.broker.api.client.BrokerApiWebSocketClient;

String wsQuoteUrl = "wss://ws.inbitix.com/ws/quote/v1";
String wsUserUrl = "wss://openapi.inbitix.com/openapi/ws/";

BrokerApiWebSocketClient wsClient = factory.newWebSocketClient(wsQuoteUrl, wsUserUrl);

wsClient.onDepthEvent("BTCUSDT", event -> {
    System.out.println("depth=" + event);
}, true);
```

#### User Data Stream Subscription

Requires obtaining a listenKey first:

```java
import io.broker.api.client.constant.BrokerConstants;

// 1. Get listenKey
String listenKey = restClient.startUserDataStream(
    BrokerConstants.DEFAULT_RECEIVING_WINDOW, 
    System.currentTimeMillis()
);

// 2. Subscribe to user events (orders, account changes, etc.)
wsClient.onUserEvent(listenKey, response -> {
    System.out.println("user event=" + response);
}, true);

// 3. Keep listenKey alive (call periodically to prevent expiration)
restClient.keepAliveUserDataStream(
    listenKey, 
    BrokerConstants.DEFAULT_RECEIVING_WINDOW, 
    System.currentTimeMillis()
);
```

## Tests and Integration Samples

Examples are in `src/test/java/io/broker/api/test`:

- `GeneralRestApiTest`
- `MarketDataRestApiTest`
- `AccountRestApiTest`
- `UserDataStreamRestApiTest`
- `UserDataStreamTest`

Run locally:

```bash
mvn test
```

## Important Notes

1. **Do not hard-code real API keys in repository code**
   - The current `Constants` class contains test key values. For production, use env vars or config center.

2. **`baseUrl` should end with `/openapi/`**
   - Retrofit mappings assume that base path style.

3. **SIGNED endpoints use millisecond timestamps**
   - Generate timestamps consistently on client side to avoid signature expiry.

4. **Exception handling**
   - Non-2xx responses are wrapped as `BrokerApiException`; use `getError()` to inspect code/message.

5. **Release long-lived resources**
   - Subscription methods return `Closeable`; close them explicitly when shutting down.

## Implementation Characteristics

- REST is synchronous/blocking only; no async REST facade is provided.
- `BrokerApiWebSocketClient.close()` is marked deprecated in behavior; prefer closing each returned `Closeable`.


