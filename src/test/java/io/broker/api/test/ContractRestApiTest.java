package io.broker.api.test;

import com.alibaba.fastjson.JSON;
import io.broker.api.client.BrokerApiClientFactory;
import io.broker.api.client.BrokerContractApiRestClient;
import io.broker.api.client.domain.general.BrokerInfo;
import io.broker.api.client.domain.general.TradeType;
import io.broker.api.client.domain.market.Candlestick;
import io.broker.api.client.domain.market.CandlestickInterval;
import io.broker.api.client.domain.market.OrderBook;
import io.broker.api.client.domain.market.TradeHistoryItem;
import io.broker.api.client.model.FuturesMatchResult;
import io.broker.api.client.constant.Constants;
import io.broker.api.client.domain.contract.*;
import io.broker.api.client.domain.contract.request.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static io.broker.api.client.constant.Constants.API_BASE_URL;

@Slf4j
public class ContractRestApiTest {


    private static final String ETH_SWAP_USDT = "ETH-SWAP-USDT";

    public BrokerContractApiRestClient getContractRestClient() {
        BrokerApiClientFactory factory = BrokerApiClientFactory.newInstance(API_BASE_URL, Constants.CONTRACT_ACCESS_KEY, Constants.CONTRACT_SECRET_KEY);
        return factory.newContractRestClient();
    }

    @Test
    public void tradeApiTest() throws InterruptedException {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");
        String symbolId = ETH_SWAP_USDT;

        BrokerContractApiRestClient client = getContractRestClient();
        OrderBook orderBook = client.getContractOrderBook(symbolId, 10);

        String price = orderBook.getBids().get(0).getPrice();
        log.info("------->  api:  /openapi/quote/v1/contract/depth  ,\n depth.bids[0].price =   {}  ", price );

        TimeUnit.SECONDS.sleep(6);

        String clientOrderId = UUID.randomUUID().toString();
        ContractOrderResult orderResult = client.newContractOrder(
            ContractOrderRequest.builder()
                    .symbol(symbolId)
                    .leverage("10")
                    .orderType(OrderType.LIMIT)
                    .side(OrderSide.BUY_OPEN)
                        .price(price)
                    .quantity("100")
                    .priceType(PriceType.INPUT)
                    .timeInForce(TimeInForce.GTC)
                    .triggerPrice("")
                    .clientOrderId(clientOrderId)
                    .build()
        );
        log.info("------->  api:  /openapi/contract/v1/order  ,\n clientOrderId = {} , orderId = {} ,  price = {}   ", clientOrderId, orderResult.getOrderId(), orderResult.getPrice());


        List<ContractOrderResult> historyOrders = client.getContractHistoryOrders(
                ContractHistoryOrderRequest.builder()
                        .symbol(symbolId)
                        .limit(10)
                        .orderType(OrderType.LIMIT)
                        .side(OrderSide.BUY_OPEN)
                        .build()
        );
        log.info("------->  api:  /openapi/contract/v1/historyOrders  ,\n {}   ", JSON.toJSONString(historyOrders));

        ContractOrderResult sOrderResult = client.getContractOrder(OrderType.LIMIT, clientOrderId, orderResult.getOrderId());
        log.info("------->  api:  /openapi/contract/v1/getOrder  , \n{}   ", JSON.toJSONString(sOrderResult));

        ContractOrderResult orderResultCancel = client.cancelContractOrder(
                CancelContractOrderRequest.builder()
                        .orderId(orderResult.getOrderId())
                        .clientOrderId(orderResult.getClientOrderId())
                        .orderType(orderResult.getOrderType())
                        .build()
        );
        log.info("------->  api: /openapi/contract/v1/order/cancel  , \n{}   ", JSON.toJSONString(orderResultCancel));

        Thread.sleep(5000);
        List<FuturesMatchResult> candlesticks = client.getMyTrade(symbolId, 0L, 0L , 20);
        log.info("------->  api: /openapi/contract/v1/myTrades  , \n{}   ", candlesticks);


        List<ContractPositionResult> positionResults = client.getContractPositions(
                ContractPositionRequest.builder()
                        .symbol(symbolId)
                        .side(PositionSide.LONG)
                        .build()
        );
        log.info("------->  api: /openapi/contract/v1/positions  , \n{}   ", JSON.toJSONString(positionResults));

        Map<String, ContractAccountResult> accountResultMap = client.getContractAccount();
        log.info("------->  api: /openapi/contract/v1/account  , \n{}   ", JSON.toJSONString(accountResultMap));


        ModifyMarginResult modifyMarginResult = client.modifyMargin(
                ModifyMarginRequest.builder()
                        .symbol(symbolId)
                        .side(PositionSide.SHORT)
                        .amount("0.1")
                        .build()
        );
        log.info("------->  api: /openapi/contract/v1/modifyMargin  , \n{}   ", JSON.toJSONString(modifyMarginResult));

    }

    @Test
    public void testContract() {
        BrokerContractApiRestClient client = getContractRestClient();
        System.out.println("\n ------limit buy-----");
        // POST /openapi/contract/v1/order
        ContractOrderResult orderResult = client.newContractOrder(
                ContractOrderRequest.builder()
                        .symbol("ETH-SWAP-USDT")
                        .leverage("5")
                        .orderType(io.broker.api.client.domain.contract.OrderType.LIMIT)
                        .side(io.broker.api.client.domain.contract.OrderSide.BUY_OPEN)
                        .price("2000")
                        .quantity("1")
                        .priceType(PriceType.INPUT)
                        .timeInForce(io.broker.api.client.domain.contract.TimeInForce.GTC)
                        .triggerPrice("")
                        .clientOrderId(UUID.randomUUID().toString())
                        .build()
        );
        System.out.println(orderResult);
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //	DELETE /openapi/contract/v1/order/cancel
        ContractOrderResult contractOrderResult = client.cancelContractOrder(CancelContractOrderRequest.builder()
                .orderId(orderResult.getOrderId())
                .clientOrderId(orderResult.getClientOrderId())
                .orderType(OrderType.LIMIT)
                .build());
        System.out.println(contractOrderResult);
    }

    @Test
    public void testContractMain() {
        BrokerContractApiRestClient client = getContractRestClient();

        ContractOrderResult orderResult = client.newContractOrder(
                ContractOrderRequest.builder()
                        .symbol("ETH-SWAP-USDT")
                        .leverage("2")
                        .orderType(OrderType.LIMIT)
                        .side(OrderSide.BUY_OPEN)
                        .price("2100")
                        .quantity("1")
                        .priceType(PriceType.INPUT)
                        .timeInForce(TimeInForce.GTC)
                        .triggerPrice("")
                        .clientOrderId(UUID.randomUUID().toString())
                        .build()
        );
    }

    @Test
    public void testContractAccount() {
        BrokerContractApiRestClient client = getContractRestClient();

        Map<String, ContractAccountResult> accountResultMap = client.getContractAccount();
        System.out.println(JSON.toJSONString(accountResultMap, true));
    }

    @Test
    public void contractMyTrades() {
        long start = new Date().getTime();
        BrokerContractApiRestClient client = getContractRestClient();
        
        List<ContractMatchResult> matchResults = client.getContractMyTrades(
                ContractMyTradeRequest.builder()
                        .symbol("ETH-SWAP-USDT")
                        .fromId(0L)
                        .limit(500)
                        .build()
        );
        System.out.println(JSON.toJSONString(matchResults, true));
        System.out.println("elapsed time: " + (new Date().getTime() - start) + "ms");
    }

    @Test
    public void testTradeDetails() {
        BrokerContractApiRestClient client = getContractRestClient();
        
        List<TradeDetailsResult> tradeDetails = client.getTradeDetails(null, null, null, null, 10);
        System.out.println("getTradeDetails count: " + (tradeDetails != null ? tradeDetails.size() : 0));
        System.out.println(JSON.toJSONString(tradeDetails, true));
    }

    @Test
    public void testTradeDetailsV2() {
        BrokerContractApiRestClient client = getContractRestClient();
        
        List<TradeDetailsResult> tradeDetailsV2 = client.getTradeDetailsV2(ETH_SWAP_USDT, null, null, null, null, 10);
        System.out.println("getTradeDetailsV2 count: " + (tradeDetailsV2 != null ? tradeDetailsV2.size() : 0));
        System.out.println(JSON.toJSONString(tradeDetailsV2, true));
    }

    @Test
    public void testFundingRates() {
        BrokerContractApiRestClient client = getContractRestClient();
        
        List<FundingRateResult> fundingRates = client.getFundingRates(ETH_SWAP_USDT, null);
        System.out.println("getFundingRates count: " + (fundingRates != null ? fundingRates.size() : 0));
        System.out.println(JSON.toJSONString(fundingRates, true));
    }

    @Test
    public void testHistoryFundingRates() {
        BrokerContractApiRestClient client = getContractRestClient();
        
        List<HistoryFundingRateResult> historyFundingRates = client.getHistoryFundingRates(ETH_SWAP_USDT, null, null, 10);
        System.out.println("getHistoryFundingRates count: " + (historyFundingRates != null ? historyFundingRates.size() : 0));
        System.out.println(JSON.toJSONString(historyFundingRates, true));
    }

    @Test
    public void testBestOrder() {
        BrokerContractApiRestClient client = getContractRestClient();
        
        BestOrderResult bestOrder = client.getBestOrder(ETH_SWAP_USDT);
        System.out.println(JSON.toJSONString(bestOrder, true));
    }

    @Test
    public void testGetBrokerInfo() {
        BrokerContractApiRestClient client = getContractRestClient();
        
        BrokerInfo brokerInfo = client.getBrokerInfo(TradeType.CONTRACTS);
        System.out.println("timezone: " + brokerInfo.getTimezone());
        System.out.println("serverTime: " + brokerInfo.getServerTime());
        System.out.println("contracts count: " + (brokerInfo.getContracts() != null ? brokerInfo.getContracts().size() : 0));
        
        if (brokerInfo.getContracts() != null && !brokerInfo.getContracts().isEmpty()) {
            for (int i = 0; i < Math.min(3, brokerInfo.getContracts().size()); i++) {
                ContractSymbol contract = brokerInfo.getContracts().get(i);
                System.out.println("contract " + (i+1) + ": " + contract.getSymbol() + 
                    ", status: " + contract.getStatus() + 
                    ", baseAsset: " + contract.getBaseAsset() + 
                    ", quoteAsset: " + contract.getQuoteAsset() +
                    ", contractMultiplier: " + contract.getContractMultiplier());
            }
        }
    }

    @Test
    public void testGetContractOrderBook() {
        BrokerContractApiRestClient client = getContractRestClient();
        
        OrderBook orderBook = client.getContractOrderBook(ETH_SWAP_USDT, 20);
        System.out.println("bids count: " + orderBook.getBids().size() + ", asks count: " + orderBook.getAsks().size());
        if (!orderBook.getBids().isEmpty()) {
            System.out.println("best bid: " + orderBook.getBids().get(0).getPrice() + ", qty: " + orderBook.getBids().get(0).getQty());
        }
        if (!orderBook.getAsks().isEmpty()) {
            System.out.println("best ask: " + orderBook.getAsks().get(0).getPrice() + ", qty: " + orderBook.getAsks().get(0).getQty());
        }
    }

    @Test
    public void testGetContractTrades() {
        BrokerContractApiRestClient client = getContractRestClient();
        
        List<TradeHistoryItem> trades = client.getContractTrades(ETH_SWAP_USDT, 20);
        System.out.println("trades count: " + trades.size());
        if (!trades.isEmpty()) {
            System.out.println(JSON.toJSONString(trades.get(0), true));
        }
    }

    @Test
    public void testGetContractCandlestickBars() {
        BrokerContractApiRestClient client = getContractRestClient();
        
        List<Candlestick> klines = client.getContractCandlestickBars(ETH_SWAP_USDT, CandlestickInterval.HOURLY, 20, null);
        System.out.println("klines count: " + klines.size());
        if (!klines.isEmpty()) {
            System.out.println(JSON.toJSONString(klines.get(0), true));
        }
    }

    @Test
    public void testBatchCancelContractOrder() {
        BrokerContractApiRestClient client = getContractRestClient();
        
        try {
            BatchCancelOrderResult result = client.batchCancelContractOrder(
                BatchCancelOrderRequest.builder()
                    .symbolList(java.util.Arrays.asList(ETH_SWAP_USDT))
                    .build()
            );
            System.out.println(JSON.toJSONString(result, true));
        } catch (Exception e) {
            System.err.println("batchCancelContractOrder error: " + e.getMessage());
        }
    }

    @Test
    public void testGetContractOpenOrders() {
        BrokerContractApiRestClient client = getContractRestClient();
        
        try {
            List<ContractOrderResult> openOrders = client.getContractOpenOrders(
                ContractOpenOrderRequest.builder()
                    .symbol(ETH_SWAP_USDT)
                    .limit(20)
                    .build()
            );
            System.out.println("openOrders count: " + openOrders.size());
            if (!openOrders.isEmpty()) {
                System.out.println(JSON.toJSONString(openOrders.get(0), true));
            }
        } catch (Exception e) {
            System.err.println("getContractOpenOrders error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        long start = new Date().getTime();
        BrokerContractApiRestClient client = getContractRestClientStatic();
        
        List<ContractMatchResult> matchResults = client.getContractMyTrades(
                ContractMyTradeRequest.builder()
                        .symbol("AUD-SWAP-USDT")
                        .fromId(0L)
                        .limit(500)
                        .build()
        );
        System.out.println(JSON.toJSONString(matchResults, true));
        System.out.println("elapsed time: " + (new Date().getTime() - start) + "ms");
    }

    private static BrokerContractApiRestClient getContractRestClientStatic() {
        BrokerApiClientFactory factory = BrokerApiClientFactory.newInstance(API_BASE_URL, Constants.CONTRACT_ACCESS_KEY, Constants.CONTRACT_SECRET_KEY);
        return factory.newContractRestClient();
    }

}
