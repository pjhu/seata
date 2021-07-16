# 启动seata & mysql & nacos
```
docker-compose up -d mysql

docker-compose up -d seata  [需要mysql 初始化完成，再启动seata,不然会报错]

docker-compose up -d nacos
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
