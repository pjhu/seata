# 启动mysql & nacos & seata
```
cd devops/local/nacos
docker-compose up -d
```

1. nacos启动前，需要初始化数据库，nacos没有初始化 【见init.sql】
https://github.com/alibaba/nacos/blob/1.4.2/distribution/conf/nacos-mysql.sql

2. 设置seata配置参数
```
cd devops/k8s-seata/nacos
sh nacos-config.sh -h localhost -g SEATA_GROUP
```
# 启动business服务
#### 更改配置
1. server.port=28080
2. 将application.yaml registry /config type改为nacos

# 启动storage服务

#### 更改配置
1. server.port=18080
2. 将application.yaml registry /config type改为nacos

# 执行分布式事务, 日志可以查看log.MD
```
curl --location --request POST 'http://localhost:28080/business/buy' \
--header 'Content-Type: application/json' \
--data-raw '{
	"userId": 1,
	"productId": 1,
  "count": 1
}'
```


```
curl --location --request POST 'http://localhost:28080/business/buy-rollback' \
--header 'Content-Type: application/json' \
--data-raw '{
	"userId": 1,
	"productId": 1,
  "count": 1
}'
```
