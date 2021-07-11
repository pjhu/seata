# 部署seata服务

部署之前，确认database, talbe已经创建

#### 部署seata服务
```
k create namespace seata
k apply -f seata-server.yaml
```

#### 配置seata到connsul
配置在config.txt文件中

```
kubectl run -it --rm --restart=Never centos --image=centos sh -n dev

sh config.sh -h consul-server.consul -p 8500
```
