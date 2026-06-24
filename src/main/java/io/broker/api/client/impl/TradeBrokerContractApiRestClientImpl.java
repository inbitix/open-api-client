package io.broker.api.client.impl;

import io.broker.api.client.TradeBrokerContractApiRestClient;
import io.broker.api.client.domain.contract.*;
import io.broker.api.client.domain.contract.request.*;
import io.broker.api.client.domain.general.BrokerInfo;
import io.broker.api.client.domain.general.TradeType;
import io.broker.api.client.domain.market.*;
import io.broker.api.client.model.FuturesMatchResult;
import io.broker.api.client.service.trade.TradeBrokerApiService;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

import static io.broker.api.client.impl.BrokerApiServiceGenerator.executeSync;

public class TradeBrokerContractApiRestClientImpl implements TradeBrokerContractApiRestClient {

    private final TradeBrokerApiService tradeBrokerApiService;

    public TradeBrokerContractApiRestClientImpl(String baseUrl, String apiKey, String secret) {
        tradeBrokerApiService = BrokerApiServiceGenerator.createService(baseUrl, TradeBrokerApiService.class, apiKey, secret);
    }


    @Override
    public BrokerInfo getBrokerInfo(TradeType type) {
        return BrokerApiServiceGenerator.executeSync(tradeBrokerApiService.getBrokerInfo());
    }

    @Override
    public Index getIndex(String symbol) {
        return BrokerApiServiceGenerator.executeSync(tradeBrokerApiService.getIndex(symbol));
    }

    @Override
    public OrderBook getDepth(String symbol, Integer limit) {
        return BrokerApiServiceGenerator.executeSync(tradeBrokerApiService.getOrderBook(symbol, limit));
    }

    @Override
    public List<TradeHistoryItem> getTrades(String symbol, Integer limit) {
        return BrokerApiServiceGenerator.executeSync(tradeBrokerApiService.getTrades(symbol, limit));
    }

    @Override
    public List<Candlestick> getKlines(String symbol, CandlestickInterval interval, Long startTime, Long endTime, Integer limit) {
        return BrokerApiServiceGenerator.executeSync(tradeBrokerApiService.getCandlestickBars(symbol, interval.getIntervalId(), startTime, endTime, limit));
    }

    @Override
    public ContractOrderResult newContractOrder(ContractOrderRequest request) {
        String clientOrderId = request.getClientOrderId();
        if (StringUtils.isEmpty(clientOrderId)) {
            clientOrderId = String.valueOf(System.currentTimeMillis());
        }

        return executeSync(tradeBrokerApiService.newContractOrder(
                request.getSymbol(),
                request.getSide() == null ? "" : request.getSide().name(),
                request.getOrderType() == null ? "" : request.getOrderType().name(),
                request.getQuantity(),
                request.getLeverage(),
                request.getPrice(),
                request.getPrice() == null ? "" : request.getPriceType().name(),
                request.getTriggerPrice(),
                request.getTimeInForce() == null ? "" : request.getTimeInForce().name(),
                clientOrderId
        ));
    }

    @Override
    public ContractOrderResult cancelContractOrder(CancelContractOrderRequest cancelOrderRequest) {
        return executeSync(tradeBrokerApiService.cancelContractOrder(
                cancelOrderRequest.getOrderId(),
                cancelOrderRequest.getClientOrderId(),
                cancelOrderRequest.getOrderType() == null ? "" : cancelOrderRequest.getOrderType().toString(),
                cancelOrderRequest.getFastCancel(),
                cancelOrderRequest.getSymbolId()
        ));
    }

    @Override
    public List<ContractOrderResult> getContractHistoryOrders(ContractHistoryOrderRequest orderRequest) {
        return executeSync(tradeBrokerApiService.getContractHistoryOrders(
                orderRequest.getSymbol(),
                orderRequest.getOrderId(),
                orderRequest.getSide() == null ? "" : orderRequest.getSide().name(),
                orderRequest.getOrderType() == null ? "" : orderRequest.getOrderType().toString(),
                orderRequest.getLimit()
        ));
    }

    @Override
    public ContractOrderResult getContractOrder(OrderType orderType, String clientOrderId, Long orderId) {
        return executeSync(tradeBrokerApiService.getContractOrder(
                orderType == null ? "" : orderType.name(),
                orderId == null ? "" : orderId.toString(),
                clientOrderId));
    }

    @Override
    public List<FuturesMatchResult> getMyTrade(String symbolId, Long fromId, Long toId, Integer limit) {
        return executeSync(tradeBrokerApiService.getMyTrades(
                symbolId, fromId, toId, limit
        ));
    }

    @Override
    public List<ContractPositionResult> getContractPositions(ContractPositionRequest request) {
        return executeSync(tradeBrokerApiService.getContractPositions(
                request.getSymbol(),
                request.getSide() == null ? "" : request.getSide().name()
        ));
    }

    @Override
    public Map<String, ContractAccountResult> getContractAccount() {
        return executeSync(tradeBrokerApiService.getContractAccount());

    }

    @Override
    public ModifyMarginResult modifyMargin(ModifyMarginRequest request) {
        return executeSync(tradeBrokerApiService.modifyMargin(
                request.getSymbol(),
                request.getSide() == null ? "" : request.getSide().name(),
                request.getAmount()
        ));
    }
}
