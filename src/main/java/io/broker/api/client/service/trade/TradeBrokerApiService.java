package io.broker.api.client.service.trade;

import io.broker.api.client.constant.BrokerConstants;
import io.broker.api.client.constant.Constants;
import io.broker.api.client.domain.account.*;
import io.broker.api.client.domain.contract.*;
import io.broker.api.client.domain.general.BrokerInfo;
import io.broker.api.client.domain.general.ServerTime;
import io.broker.api.client.domain.market.*;
import io.broker.api.client.model.FuturesMatchResult;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

/**
 * Broker's REST API URL mappings and endpoint security configuration.
 */
public interface TradeBrokerApiService {

    public static final String SERVER_URL_PREFIX = Constants.TradeApi.SERVER_URL_PREFIX;

    // ------------------------- public Endpoints ---------------------------------------------------

    @GET(SERVER_URL_PREFIX + "/v1/brokerInfo")
    @Headers(BrokerConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    Call<BrokerInfo> getBrokerInfo();

    @GET(SERVER_URL_PREFIX + "/quote/v1/index")
    Call<Index> getIndex(@Query("symbol") String symbol);

    @GET(SERVER_URL_PREFIX + "/quote/v1/depth")
    @Headers(BrokerConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    Call<OrderBook> getOrderBook(@Query("symbol") String symbol, @Query("limit") Integer limit);

    @GET(SERVER_URL_PREFIX + "/quote/v1/trades")
    @Headers(BrokerConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    Call<List<TradeHistoryItem>> getTrades(@Query("symbol") String symbol, @Query("limit") Integer limit);

    @GET(SERVER_URL_PREFIX + "/quote/v1/klines")
    @Headers(BrokerConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    Call<List<Candlestick>> getCandlestickBars(@Query("symbol") String symbol,
                                               @Query("interval") String interval,
                                               @Query("startTime") Long startTime,
                                               @Query("endTime") Long endTime,
                                               @Query("limit") Integer limit);

    // -----------------------  Private Endpoints  ---------------------------------------------------


    @Headers(BrokerConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST(SERVER_URL_PREFIX + "/contract/v1/order")
    Call<ContractOrderResult> newContractOrder(@Query("symbol") String symbol,
                                               @Query("side") String side,
                                               @Query("orderType") String orderType,
                                               @Query("quantity") String quantity,
                                               @Query("leverage") String leverage,
                                               @Query("price") String price,
                                               @Query("priceType") String priceType,
                                               @Query("triggerPrice") String triggerPrice,
                                               @Query("timeInForce") String timeInForce,
                                               @Query("clientOrderId") String clientOrderId);

    @Headers(BrokerConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET(SERVER_URL_PREFIX + "/contract/v1/historyOrders")
    Call<List<ContractOrderResult>> getContractHistoryOrders(@Query("symbol") String symbol,
                                                             @Query("orderId") Long orderId,
                                                             @Query("side") String side,
                                                             @Query("orderType") String orderType,
                                                             @Query("limit") Integer limit);

    @Headers(BrokerConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @DELETE(SERVER_URL_PREFIX + "/contract/v1/order/cancel")
    Call<ContractOrderResult> cancelContractOrder(@Query("orderId") Long orderId,
                                                  @Query("clientOrderId") String clientOrderId,
                                                  @Query("orderType") String orderType,
                                                  @Query("fastCancel") Integer fastCancel,
                                                  @Query("symbolId") String symbolId);

    @Headers(BrokerConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET(SERVER_URL_PREFIX + "/contract/v1/getOrder")
    Call<ContractOrderResult> getContractOrder(@Query("orderType") String orderType,
                                               @Query("orderId") String orderId,
                                               @Query("clientOrderId") String clientOrderId);

    @Headers(BrokerConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET(SERVER_URL_PREFIX + "/contract/v1/myTrades")
    Call<List<FuturesMatchResult>> getMyTrades(@Query("symbol")  String symbolId,
                                               @Query("fromId") Long fromId,
                                               @Query("toId") Long toId,
                                               @Query("limit") Integer limit);

    @Headers(BrokerConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET(SERVER_URL_PREFIX + "/contract/v1/positions")
    Call<List<ContractPositionResult>> getContractPositions(@Query("symbol") String symbol,
                                                            @Query("side") String side);


    @Headers(BrokerConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET(SERVER_URL_PREFIX + "/contract/v1/account")
    Call<Map<String, ContractAccountResult>> getContractAccount();

    @Headers(BrokerConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST(SERVER_URL_PREFIX + "/contract/v1/modifyMargin")
    Call<ModifyMarginResult> modifyMargin(@Query("symbol") String symbol,
                                          @Query("side") String side,
                                          @Query("amount") String amount);
}
