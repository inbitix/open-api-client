package io.broker.api.client;

import io.broker.api.client.domain.account.*;
import io.broker.api.client.domain.account.request.*;
import io.broker.api.client.domain.contract.*;
import io.broker.api.client.domain.contract.OrderType;
import io.broker.api.client.domain.contract.request.*;
import io.broker.api.client.domain.general.BrokerInfo;
import io.broker.api.client.domain.general.TradeType;
import io.broker.api.client.domain.market.*;
import io.broker.api.client.model.FuturesMatchResult;

import java.util.List;
import java.util.Map;

public interface TradeBrokerContractApiRestClient {

    // ------------------------- public Endpoints ---------------------------------------------------

    BrokerInfo getBrokerInfo(TradeType type);

    Index getIndex(String symbol);

    OrderBook getDepth(String symbol, Integer limit);

    List<TradeHistoryItem> getTrades(String symbol, Integer limit);

    List<Candlestick> getKlines(String symbol, CandlestickInterval interval, Long startTime, Long endTime, Integer limit);


    // -----------------------  Private Endpoints  ---------------------------------------------------

    ContractOrderResult newContractOrder(ContractOrderRequest order);

    ContractOrderResult cancelContractOrder(CancelContractOrderRequest cancelOrderRequest);

    List<ContractOrderResult> getContractHistoryOrders(ContractHistoryOrderRequest orderRequest);

    ContractOrderResult getContractOrder(OrderType orderType, String clientOrderId, Long orderId);

    List<FuturesMatchResult> getMyTrade(String symbolId, Long fromId, Long toId, Integer limit);

    List<ContractPositionResult> getContractPositions(ContractPositionRequest request);

    Map<String, ContractAccountResult> getContractAccount();

    ModifyMarginResult modifyMargin(ModifyMarginRequest request);
}
