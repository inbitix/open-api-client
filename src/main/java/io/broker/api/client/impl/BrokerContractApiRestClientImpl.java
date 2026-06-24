package io.broker.api.client.impl;

import io.broker.api.client.BrokerContractApiRestClient;
import io.broker.api.client.domain.contract.*;
import io.broker.api.client.domain.contract.request.*;
import io.broker.api.client.domain.general.BrokerInfo;
import io.broker.api.client.domain.general.TradeType;
import io.broker.api.client.domain.market.Candlestick;
import io.broker.api.client.domain.market.CandlestickInterval;
import io.broker.api.client.domain.market.OrderBook;
import io.broker.api.client.domain.market.TradeHistoryItem;
import io.broker.api.client.model.FuturesMatchResult;
import io.broker.api.client.service.BrokerContractApiService;
import org.apache.commons.lang3.StringUtils;
import retrofit2.http.Query;

import java.util.List;
import java.util.Map;

import static io.broker.api.client.impl.BrokerApiServiceGenerator.createService;
import static io.broker.api.client.impl.BrokerApiServiceGenerator.executeSync;

/**
 * Implementation of Broker's Contract REST API using Retrofit with synchronous/blocking method calls.
 */
public class BrokerContractApiRestClientImpl implements BrokerContractApiRestClient {

    private final BrokerContractApiService brokerContractApiService;

    private static final String PARAMETER_LIST_SEPARATOR = ",";

    public BrokerContractApiRestClientImpl(String baseUrl, String apiKey, String secret) {
        brokerContractApiService = createService(baseUrl, BrokerContractApiService.class, apiKey, secret);
    }

    @Override
    public BrokerInfo getBrokerInfo(TradeType type) {
        return executeSync(brokerContractApiService.getBrokerInfo(type == null ? "" : type.getValue()));
    }

    @Override
    public ContractOrderResult newContractOrder(ContractOrderRequest request) {
        String clientOrderId = request.getClientOrderId();
        if (StringUtils.isEmpty(clientOrderId)) {
            clientOrderId = String.valueOf(System.currentTimeMillis());
        }

        return executeSync(brokerContractApiService.newContractOrder(
                request.getSymbol(),
                request.getSide() == null ? "" : request.getSide().name(),
                request.getOrderType() == null ? "" : request.getOrderType().name(),
                request.getQuantity(),
                request.getLeverage(),
                request.getPrice(),
                request.getPrice()  == null ? "" : request.getPriceType().name(),
                request.getTriggerPrice(),
                request.getTimeInForce() == null ? "" : request.getTimeInForce().name(),
                clientOrderId
        ));
    }

    @Override
    public ContractOrderResult cancelContractOrder(CancelContractOrderRequest cancelOrderRequest) {
        return executeSync(brokerContractApiService.cancelContractOrder(
           cancelOrderRequest.getOrderId(),
           cancelOrderRequest.getClientOrderId(),
           cancelOrderRequest.getOrderType() == null ? "" : cancelOrderRequest.getOrderType().toString(),
                cancelOrderRequest.getFastCancel(),
                cancelOrderRequest.getSymbolId()
        ));
    }

    @Override
    public BatchCancelOrderResult batchCancelContractOrder(BatchCancelOrderRequest request) {
        return executeSync(brokerContractApiService.batchCancelContractOrder(
                StringUtils.join(request.getSymbolList(), PARAMETER_LIST_SEPARATOR)
        ));
    }

    @Override
    public ContractOrderResult getContractOrder(OrderType orderType, String clientOrderId, Long orderId) {
        return executeSync(brokerContractApiService.getContractOrder(
                orderType == null ? "" : orderType.name(),
                orderId == null ? "" : orderId.toString(),
                clientOrderId));
    }

    @Override
    public List<ContractOrderResult> getContractOpenOrders(ContractOpenOrderRequest orderRequest) {
        return executeSync(brokerContractApiService.getContractOpenOrders(
                orderRequest.getSymbol(),
                orderRequest.getOrderId(),
                orderRequest.getSide() == null ? "" : orderRequest.getSide().name(),
                orderRequest.getOrderType() == null ? "" : orderRequest.getOrderType().name(),
                orderRequest.getLimit()
        ));
    }

    @Override
    public List<ContractOrderResult> getContractHistoryOrders(ContractHistoryOrderRequest orderRequest) {
        return executeSync(brokerContractApiService.getContractHistoryOrders(
                orderRequest.getSymbol(),
                orderRequest.getOrderId(),
                orderRequest.getSide() == null ? "" : orderRequest.getSide().name(),
                orderRequest.getOrderType() == null ? "" : orderRequest.getOrderType().toString(),
                orderRequest.getLimit()
        ));
    }

    @Override
    public List<ContractMatchResult> getContractMyTrades(ContractMyTradeRequest request) {
        return executeSync(brokerContractApiService.getContractMyTrades(
                request.getSymbol(),
                request.getLimit(),
                request.getSide() == null ? "" : request.getSide().name(),
                request.getFromId(),
                request.getToId()
        ));
    }

    @Override
    public List<ContractPositionResult> getContractPositions(ContractPositionRequest request) {
        return executeSync(brokerContractApiService.getContractPositions(
                request.getSymbol(),
                request.getSide() == null ? "" : request.getSide().name()
        ));
    }

    @Override
    public ModifyMarginResult modifyMargin(ModifyMarginRequest request) {
        return executeSync(brokerContractApiService.modifyMargin(
                request.getSymbol(),
                request.getSide() == null ? "" : request.getSide().name(),
                request.getAmount()
        ));
    }

    @Override
    public Map<String, ContractAccountResult> getContractAccount() {
        return executeSync(brokerContractApiService.getContractAccount());
    }

    @Override
    public OrderBook getContractOrderBook(String symbol, Integer limit) {
        return executeSync(brokerContractApiService.getOrderBook(symbol, limit));
    }

    @Override
    public List<TradeHistoryItem> getContractTrades(String symbol, Integer limit) {
        return executeSync(brokerContractApiService.getTrades(symbol, limit));
    }

    @Override
    public List<Candlestick> getContractCandlestickBars(String symbol, CandlestickInterval interval, Integer limit, Long to) {
        return executeSync(brokerContractApiService.getCandlestickBars(
                symbol, interval == null ? "" : interval.getIntervalId(), limit, to));
    }

    @Override
    public List<FuturesMatchResult> getMyTrade(String symbolId, Long fromId, Long toId, Integer limit) {
        return executeSync(brokerContractApiService.getMyTrades(
                symbolId, fromId, toId, limit
        ));
    }

    @Override
    public List<TradeDetailsResult> getTradeDetails(Long fromId, Long toId, Long startTime, Long endTime, Integer limit) {
        return executeSync(brokerContractApiService.getTradeDetails(
                fromId, toId, startTime, endTime, limit
        ));
    }

    @Override
    public List<TradeDetailsResult> getTradeDetailsV2(String symbolId, Long fromId, Long toId, Long startTime, Long endTime, Integer limit) {
        return executeSync(brokerContractApiService.getTradeDetailsV2(
                symbolId, fromId, toId, startTime, endTime, limit
        ));
    }

    @Override
    public List<FundingRateResult> getFundingRates(String symbol, String state) {
        return executeSync(brokerContractApiService.getFundingRates(
                symbol, state
        ));
    }

    @Override
    public List<HistoryFundingRateResult> getHistoryFundingRates(String symbol, Long fromId, Long endId, Integer limit) {
        return executeSync(brokerContractApiService.getHistoryFundingRates(
                symbol, fromId, endId, limit
        ));
    }

    @Override
    public BestOrderResult getBestOrder(String symbol) {
        return executeSync(brokerContractApiService.getBestOrder(
                symbol
        ));
    }
}
