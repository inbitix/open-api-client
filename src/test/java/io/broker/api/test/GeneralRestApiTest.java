package io.broker.api.test;

import com.alibaba.fastjson.JSON;
import io.broker.api.client.BrokerApiClientFactory;
import io.broker.api.client.BrokerApiRestClient;
import io.broker.api.client.domain.general.BrokerInfo;
import io.broker.api.client.model.BalanceFlowNewResult;
import io.broker.api.client.constant.Constants;
import org.junit.Test;

import java.util.List;

public class GeneralRestApiTest {

    @Test
    public void testBrokerInfo() {

        BrokerApiClientFactory factory = BrokerApiClientFactory.newInstance(Constants.API_BASE_URL);
        BrokerApiRestClient client = factory.newRestClient();

        System.out.println("\n ------BrokerInfo-----");
        BrokerInfo brokerInfo = client.getBrokerInfo();
        System.out.println(brokerInfo);

    }

    @Test
    public void getBalanceFlow() {
        BrokerApiClientFactory factory = BrokerApiClientFactory.newInstance(Constants.API_BASE_URL, Constants.ACCESS_KEY, Constants.SECRET_KEY);
        BrokerApiRestClient client = factory.newRestClient();

        System.out.println("\n ------BrokerInfo-----");
        Object resObj = client.getBalanceFlow("1773020589", "1775698989", 10L, 0L, 9001);
        List<BalanceFlowNewResult> res = JSON.parseObject(JSON.toJSONString(resObj),List.class) ;
        System.out.println(res);
    }


}
