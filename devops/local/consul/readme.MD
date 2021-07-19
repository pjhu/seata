# 启动seata & mysql
```
docker-compose up -d mysql

docker-compose up -d seata  [需要mysql 初始化完成，在启动seata,不然会报错]
```

# 启动consul 
- 查看文档 /devops/k8s-consul/readme.MD
# 映射consul到本地
```
k port-forward statefulset/consul-server 8500:8500 --address 0.0.0.0 -n consul
```

# 配置seata/服务的参数到consul
```
sh consul-config.sh -h localhost -p 8500
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