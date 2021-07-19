# 启动mysql & nacos & seata
```
cd devops/local/nacos
docker-compose up -d
```
# nacos启动前，需要初始化数据库，nacos没有初始化 【见devops/init.sql】
https://github.com/alibaba/nacos/blob/1.4.2/distribution/conf/nacos-mysql.sql

# 设置seata配置参数, 然后重启seata
```
sh nacos-config.sh -h localhost -g SEATA_GROUP
```
# 启动business服务, 更改配置
- server.port=28080
- 将application.yaml registry /config type改为nacos
- 将StorageClient url 改为 http://localhost:18080

# 启动storage服务, 更改配置
- server.port=18080
- 将application.yaml registry /config type改为nacos

# 执行分布式事务
```
curl --location --request POST 'http://localhost:28080/business/buy' \
--header 'Content-Type: application/json' \
--data-raw '{
	"userId": 1,
	"productId": 1,
  "count": 1
}'
```

# 执行分布式事务, 查看回滚状态，可通过debug+断点看table数据
```
curl --location --request POST 'http://localhost:28080/business/buy-rollback' \
--header 'Content-Type: application/json' \
--data-raw '{
	"userId": 1,
	"productId": 1,
  "count": 1
}'
```