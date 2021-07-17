# 配置 consul，使用consul作为seata的注册/配置中心

### setup consul
[readme](https://github.com/pjhu/seata/tree/master/devops/consul/readme.MD)

### 创建数据库
```
cd devops
k apply -f cicd/mysql.yaml
```

### 配置权限管理
```
cd devops
k apply -f cicd/role.yml
```

### 配置 kong
[readme](https://github.com/pjhu/seata/tree/master/devops/kong/readme.MD)

### 配置seata

部署之前，确认database, talbe已经创建

##### 部署seata服务
```
cd ..
k create namespace seata
k apply -f seata-server.yaml
```

### 配置seata到connsul

#### 配置在config.txt文件中

```
k port-forward statefulset/consul-server 28500:8500 --address 0.0.0.0 -n consul

sh consul-config.sh -h localhost -p 28500
```

### 创建服务image
执行 business / account / order / storage项目下的ci

### 部署服务
部署服务的示例: https://konghq.com/blog/kubernetes-ingress-api-gateway
```
cd decvops/

k apply -f cicd/account-deploy.yaml
k apply -f cicd/order-deploy.yaml
k apply -f cicd/storage-deploy.yaml
k apply -f cicd/business-deploy.yaml
```