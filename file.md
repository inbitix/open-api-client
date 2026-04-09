## 检查配置项

1.  Apollo
1.  项目代码
1.  k8sSecret

## 项目信息

### Bluehelix

#### sync2es

同步交易信息到ES

这是一个 Python 项目通过读取系统变量中的密码配置来初始化对应的数据库连接修改源应该是 **k8s-secret**

<https://gitlab.kittycoin.tech/bhpc/bluehelix/sync2es>

<https://gitlab.kittycoin.tech/bhpc/ops/config/-/blob/master/BHPC_PROD/bluehelix/sync2es.yaml>

#### margin-service

读取系统环境变量修改源 **k8s-secret**

<https://gitlab.kittycoin.tech/bhpc/bluehelix/margin-service>

<https://gitlab.kittycoin.tech/bhpc/ops/config/-/blob/master/BHPC_PROD/bluehelix/margin-server.yaml>

#### bh-shard

读取apollo中的配置修改源 **apollo**

<https://gitlab.kittycoin.tech/bhpc/bluehelix/bh-shard>

<https://apollo.kittycoin.tech/config.html?#/appid=bh-shard>

#### bh-order

读取环境变量修改源 **k8s-secret**

<https://gitlab.kittycoin.tech/bhpc/bluehelix/bh-order>

<https://gitlab.kittycoin.tech/bhpc/ops/config/-/blob/master/BHPC_PROD/bluehelix/bh-order.yaml>

#### bh

读取 apollo 配置修改源 **apollo**

<https://gitlab.kittycoin.tech/bhpc/bluehelix/bh>

<https://apollo.kittycoin.tech/config.html?#/appid=bh-server>

### Broker 券商Core

#### bhex-common-service

读取 apollo 配置修改源 **apollo**

<https://gitlab.kittycoin.tech/bhpc/broker/bhex-common-service>

<https://apollo.kittycoin.tech/config.html?#/appid=common-server>

#### broker-admin-grpc

读取系统环境变量修改源 **k8s-secret**

<https://gitlab.kittycoin.tech/bhpc/broker/broker-admin-grpc>

<https://gitlab.kittycoin.tech/bhpc/ops/config/-/blob/master/BHPC_PROD/broker/broker-milk-grpc-server.yaml>

#### broker-server

读取 apollo 配置修改源 **apollo**

<https://gitlab.kittycoin.tech/bhpc/broker/broker-server>

<https://apollo.kittycoin.tech/config.html?#/appid=broker-server>

#### candy-common-service

读取 apollo 配置修改源 **apollo**

<https://gitlab.kittycoin.tech/bhpc/broker/candy-common-service>

<https://apollo.kittycoin.tech/config.html?#/appid=candy-common-service-scheduler>

<https://apollo.kittycoin.tech/config.html?#/appid=candy-common-service-server>

#### security

读取 apollo 配置修改源 **apollo**

<https://gitlab.kittycoin.tech/bhpc/broker/security>

<https://apollo.kittycoin.tech/config.html?#/appid=broker-security>

#### saas-admin

读取系统环境变量修改源 **k8s-secret**

<https://gitlab.kittycoin.tech/bhpc/broker/saas-admin>

<https://gitlab.kittycoin.tech/bhpc/ops/config/-/blob/master/BHPC_PROD/broker/milk-server.yaml>

#### eagle-eyes

读取 apollo 配置修改源 **apollo**

<https://gitlab.kittycoin.tech/bhpc/broker/eagle-eyes>

<https://apollo.kittycoin.tech/config.html?#/appid=eagle-eyes-scheduler>

#### exchange-otc

<https://gitlab.kittycoin.tech/bhpc/broker/exchange-otc>

<https://gitlab.kittycoin.tech/bhpc/ops/config/-/blob/master/BHPC_PROD/broker/otc-server.yaml>

<https://gitlab.kittycoin.tech/bhpc/ops/config/-/blob/master/BHPC_PROD/broker/otc-server-9004.yaml>

<https://gitlab.kittycoin.tech/bhpc/ops/config/-/blob/master/BHPC_PROD/broker/otc-server-9005.yaml>

<https://gitlab.kittycoin.tech/bhpc/ops/config/-/blob/master/BHPC_PROD/broker/otc-server-9007.yaml>

#### robot-9006-java

【本次暂不修改】这里是项目代码直接写死的，修改位置 data/config/database.php

<https://gitlab.kittycoin.tech/bhpc/broker/robot-9006-java>

#### robot-interlocution-java

【本次暂不修改】这里是项目代码直接写死的，修改位置 data/config/database.php

<https://gitlab.kittycoin.tech/bhpc/broker/robot-interlocution-java>

### Exchange 交易

#### gateway

读取系统环境变量修改源 **k8s-secret**

<https://gitlab.kittycoin.tech/bhpc/exchange/gateway>

<https://gitlab.kittycoin.tech/bhpc/ops/config/-/blob/master/BHPC_PROD/exchange/gateway-service.yaml>

#### match（物理部署）

代码中配置 match-engine/src/main/resources/config/application-online.yml

<https://gitlab.kittycoin.tech/bhpc/exchange/match>

#### quote-index（物理部署）

代码中配置 quote-index-server/src/main/resources/config/application-online.yml

<https://gitlab.kittycoin.tech/bhpc/exchange/quote-index>

#### quote-engin（物理部署）

读取 apollo 配置修改源 **apollo**

<https://gitlab.kittycoin.tech/bhpc/exchange/quote-engine>

<https://apollo.kittycoin.tech/config.html?#/appid=quote-engine>

#### quote-service

读取 apollo 配置修改源 **apollo**

<https://gitlab.kittycoin.tech/bhpc/exchange/quote-service>

<https://apollo.kittycoin.tech/config.html?#/appid=quote-data-service>

#### settle

读取系统环境变量修改源 **k8s-secret**

<https://gitlab.kittycoin.tech/bhpc/exchange/settle>

<https://gitlab.kittycoin.tech/bhpc/ops/config/-/blob/master/BHPC_PROD/exchange/settle-engine.yaml>

### Rc 结算

#### exrc

读取系统环境变量修改源 **k8s-secret**

<https://gitlab.kittycoin.tech/bhpc/rc/exrc>

<https://gitlab.kittycoin.tech/bhpc/ops/config/-/blob/master/BHPC_PROD/rc/exrc-server.yaml>

#### exrcadmin

读取系统环境变量修改源 **k8s-secret**

<https://gitlab.kittycoin.tech/bhpc/rc/exrcadmin>

<https://gitlab.kittycoin.tech/bhpc/ops/config/-/blob/master/BHPC_PROD/rc/exrcadmin.yaml>

### Clear 清算

<https://gitlab.kittycoin.tech/bhpc/clear/clear/-/tree/master?ref_type=heads>

```
需要联系运维同学去 azkban 中修改

这个需要去容器里改用户名密码
azkaban的数据库配置有单独的文件
脚本用的数据库配置得去容器里改
```

### Wallet 钱包

```
需要运维同学进行修改
```

## 密码信息

```

线上环境开发库

shard库
新：shard1 LlFafEPEH3yaeU57
原：shard  shard_2022Gs7mqh
Host：shard.cluster-c8e0w4lqfy0z.ap-northeast-1.rds.amazonaws.com
 
exchange 库
新：exchange1 rpptnjfbe5mTrkTc
原：exchange exchange_2022Gs7mqh
Host：exchange.cluster-c8e0w4lqfy0z.ap-northeast-1.rds.amazonaws.com

broker库
新：broker1 K59wUwAXUI4hFxo3
原：broker broker_2022Gs7mqh
Host：broker.cluster-c8e0w4lqfy0z.ap-northeast-1.rds.amazonaws.com

clear库
新：clear1 LBxBML050PROTxVp
原：clear  clear_2022Gs7mqh
Host：clear.cluster-c8e0w4lqfy0z.ap-northeast-1.rds.amazonaws.com
```

## Apollo配置信息

### Apollo 地址

正式环境：<https://apollo.kittycoin.tech>

IO 环境：<https://apollo.domain.io/signin>

### Apollo应用

<https://apollo.kittycoin.tech/config.html?#/appid=bh-shard>

<https://apollo.kittycoin.tech/config.html?#/appid=bh-server>

<https://apollo.kittycoin.tech/config.html?#/appid=quote-data-service>

<https://apollo.kittycoin.tech/config.html?#/appid=broker-server>

<https://apollo.kittycoin.tech/config.html?#/appid=quote-engine>

<https://apollo.kittycoin.tech/config.html?#/appid=candy-common-service-scheduler>

<https://apollo.kittycoin.tech/config.html?#/appid=candy-common-service-server>

<https://apollo.kittycoin.tech/config.html?#/appid=common-server>

<https://apollo.kittycoin.tech/config.html?#/appid=broker-security>

<https://apollo.kittycoin.tech/config.html?#/appid=eagle-eyes-scheduler>

## 检查手册