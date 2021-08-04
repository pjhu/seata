# 配置 nacos，使用nacos作为seata的注册/配置中心
# 创建数据库
```
cd devops
k apply -f k8s-cicd/mysql.yaml
```
# 确认nacos/kong/seata 所需的database, talbe已经创建
# 配置权限管理
```
cd devops
k apply -f k8s-cicd/role.yml
```
# 配置 kong
[readme](https://github.com/pjhu/seata/tree/master/devops/kong/readme.MD)

# setup nacos
[readme](https://github.com/pjhu/seata/tree/master/devops/consul/readme.MD)

# 配置seata到nacos,配置在config.txt文件中
```
cd k8s-seata/nacos
sh nacos-config.sh -h web.nacos.com -p 80 -g SEATA_GROUP
```
# 部署seata服务
```
k create namespace seata
k apply -f seata-server.yaml
```
# 创建服务image
执行 business / account / order / storage项目下的ci

# 部署服务
部署服务的示例: https://konghq.com/blog/kubernetes-ingress-api-gateway
```
cd decvops/

<!-- k apply -f cicd/account-deploy.yaml -->
<!-- k apply -f cicd/order-deploy.yaml -->
k apply -f cicd/storage-deploy.yaml
k apply -f cicd/business-deploy.yaml
```

# 测试事务
curl --location --request POST 'http://localhost:80/business/buy' \
--header 'Content-Type: application/json' \
--data-raw '{
	"userId": 1,
	"productId": 1,
    "count": 1
}'

# 测试事务回滚
curl --location --request POST 'http://localhost:80/business/buy-rollback' \
--header 'Content-Type: application/json' \
--data-raw '{
	"userId": 1,
	"productId": 1,
  "count": 1
}'