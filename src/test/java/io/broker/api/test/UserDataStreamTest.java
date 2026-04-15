package io.broker.api.test;

import io.broker.api.client.BrokerApiClientFactory;
import io.broker.api.client.BrokerApiRestClient;
import io.broker.api.client.BrokerApiWebSocketClient;
import io.broker.api.client.constant.BrokerConstants;
import io.broker.api.client.domain.account.*;
import io.broker.api.client.domain.account.request.CancelOrderRequest;
import io.broker.api.client.domain.account.request.OrderStatusRequest;
import io.broker.api.client.constant.Constants;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import io.jsonwebtoken.Jwts;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
public class UserDataStreamTest {

    static BrokerApiClientFactory factory = BrokerApiClientFactory.newInstance(Constants.API_BASE_URL, Constants.ACCESS_KEY, Constants.SECRET_KEY);
    static BrokerApiWebSocketClient wsClient = factory.newWebSocketClient(Constants.WS_API_BASE_URL, Constants.WS_API_USER_URL);
    static BrokerApiRestClient restClient = factory.newRestClient();

    @Test
    public void testUserDataStream() throws InterruptedException {

        log.info("\n ------Get Listen Key -----");
        String listenKey = restClient.startUserDataStream(BrokerConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
        log.info("listenKey:" + listenKey);

        // check received msg time delay
        wsClient.onUserEvent(listenKey, response -> {
            long now = System.currentTimeMillis();
            List<SocketOrder> orderList = response.getOrderList();
            List<SocketAccount> accountList = response.getAccountList();
            log.info("received event: {}", response);

            if (orderList != null && orderList.size() > 0) {
                SocketOrder order = orderList.get(0);
                long costTime1 = now - order.getCreateTime();
                long costTime2 = now - order.getEventTime();
                if (costTime2 > 100L) {
                    log.warn("received event {}, order id {}, \n -- order status {}, costTime1 {}, costTime2 {}",
                            order.getEventType(), order.getOrderId(), order.getStatus(), costTime1, costTime2);
                } else {
                    log.info("received event {}, order id {}, \n -- order status {}, costTime1 {}, costTime2 {}",
                            order.getEventType(), order.getOrderId(), order.getStatus(), costTime1, costTime2);
                }
            }

            if (accountList != null && accountList.size() > 0) {
                SocketAccount account = accountList.get(0);
                List<SocketBalance> balances = account.getBalanceChangedList();
                long costTime1 = now - account.getLastUpdated();
                long costTime2 = now - account.getEventTime();
                log.info("received event {}, Account Balance changed size {}, costTime1 {}, costTime2 {}",
                        account.getEventType(), balances.size(), costTime1, costTime2);
            }
        }, true);

        new Thread(() -> {
            startPlaceOrder();
        }).start();
        Thread.sleep(600000L);
    }

    private static void startPlaceOrder() {
        Account account = restClient.getAccount(5L, System.currentTimeMillis() / 1000);
        log.info("start place order using account: {}", account);

        for (int i = 0; i < 10; i++) {

            NewOrderResponse response = restClient.newOrder(NewOrder.limitBuy("BTCUSDT", TimeInForce.GTC, "0.1", "68000"));
            safeSleep(1000L);
            try {
                restClient.cancelOrder(new CancelOrderRequest(response.getOrderId()));
            } catch (Exception e) {
                log.error("cancel order catch exception, order {}, exception {}", response, e);

                Order order2 = restClient.getOrderStatus(new OrderStatusRequest(response.getOrderId()));
                log.info("order is {}", order2);
            }
            Order order = restClient.getOrderStatus(new OrderStatusRequest(response.getOrderId()));
            //log.info("new order no.{}, order id {}, status: {}", i, order.getOrderId(), order.getStatus());
            safeSleep(1000L);
        }
    }

    private static void safeSleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long timeInMillis = timestamp.getTime();
        System.out.println(timeInMillis);
    }

    @Test
    public void testJwtParser() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjA2Mjc1NzcwNTc5NzYzMjAwIiwiX3RpbWUiOjE3MzMzOTcyMzkyMTksIl9yIjoiUXNZdWZ2Wld2Z0RaIiwiX3AiOiJkNDVkYzU5NWQ3OGJhZjcwMDljOWU5MzFhNGMxNDhmMiJ9.VuiHF9FMTKhE35qxBDBWYnm_3N1szGlhXGda8kD8ClI";
        Claims claims = Jwts.parser().setSigningKey("change_me").parseClaimsJws(token).getBody();

        System.out.println(claims.getSubject());

//        Claims claims = Jwts.parser().setSigningKey(getTokenEncryptPassword()).parseClaimsJws(token).getBody();

    }
}
