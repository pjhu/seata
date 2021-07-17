# setup nacos

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

# 控制台用户名密码
nacos/nacos

# 添加seata 配置
```
sh nacos-config.sh -h localhost -g SEATA_GROUP
```

### 配置 kong
[readme](https://github.com/pjhu/seata/tree/master/devops/kong/readme.MD)

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


# 遇到的问题
nacos启动，需要初始化数据库，nacocs服务不会初始化

https://github.com/nacos-group/nacos-docker/issues/121