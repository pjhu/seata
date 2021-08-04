# 目前该方式只支持注册中心，配置中心无法支持
https://github.com/seata/seata/issues/3777
https://juejin.cn/post/6976191318305275912

# 参考文件
https://github.com/hashicorp/learn-consul-docker/tree/main/datacenter-deploy-secure
https://learn.hashicorp.com/tutorials/consul/docker-compose-datacenter
# 启动seata & mysql & consul
```
docker-compose up -d
[需要mysql 初始化完成，在启动seata,不然会报错]
```

# 打开consul控制台

http://localhost:8500/
# 配置seata/服务的参数到consul
```
sh consul-config.sh -h localhost -p 8500
```

# 重启seata
```
docker-compose restart seata
```

# 启动business服务，更改配置,编辑application.yaml
- server.port=28080
- 配置registry / config type为consul
# 启动storage服务，更改配置,编辑application.yaml
- server.port=18080
- 配置registry / config type为consul

# 无法执行分布式事务, 上一步会启动失败
```
curl --location --request POST 'http://localhost:28080/business/buy' \
--header 'Content-Type: application/json' \
--data-raw '{
	"userId": 1,
	"productId": 1,
  "count": 1
}'
```

# 无法执行分布式事务, 查看回滚状态
```
curl --location --request POST 'http://localhost:28080/business/buy-rollback' \
--header 'Content-Type: application/json' \
--data-raw '{
	"userId": 1,
	"productId": 1,
  "count": 1
}'
```