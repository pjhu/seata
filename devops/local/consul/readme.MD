# 启动seata & mysql
```
docker-compose up -d mysql

docker-compose up -d seata  [需要mysql 初始化完成，在启动seata,不然会报错]
```

# 启动business服务
#### 更改配置
1. server.port=28080
2. 将application.yaml registry /config type改为file，如果测试consul不需要改

# 启动storage服务

#### 更改配置
1. server.port=18080
2. 将application.yaml registry /config type改为file，如果测试consul不需要改


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


```
curl --location --request POST 'http://localhost:28080/business/buy-rollback' \
--header 'Content-Type: application/json' \
--data-raw '{
	"userId": 1,
	"productId": 1,
  "count": 1
}'
```

# 本地调试consul问题

1. 查看文档启动文档 /devops/consul/readme.MD
2. 映射到本地
```
k port-forward statefulset/consul-server 28500:8500 --address 0.0.0.0 -n consul
```
3. 更改配置，可调试链接consul加载慢的问题
   取消注释 application.yaml中的reigstry / config为consul的部分

   [consul 问题](https://github.com/pjhu/seata/tree/master/devops/k8s-seata/consul/readme.MD)

4. 启动服务，执行分布式事务