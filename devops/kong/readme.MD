# kong配置
#### 部署kong
https://docs.konghq.com/gateway-oss/2.4.x/kong-for-kubernetes/install/
```
k create namespace kong
helm install -f myvalues.yaml kong kong/kong --set ingressController.installCRDs=false -n kong
```

#### 测试
https://docs.konghq.com/kubernetes-ingress-controller/1.3.x/guides/getting-started/

##### 部署服务：
```
k apply -f demo.yaml -n dev

curl -i localhost:80/foo
```

##### 增加request-id插件
```
k apply  -f demo-plugin-requestid.yaml -n dev

curl -i -H "Host: example.com" localhost:80/bar/sample
```

##### 增加rate-limit插件
https://docs.konghq.com/hub/kong-inc/rate-limiting/

```
k apply -f demo-plugin-ratelimit.yaml -n dev
```

#### konga配置
https://github.com/pantsel/konga
```
k apply -f konga.yaml -n kong
```
需要先创建数据库
部署完后，打开localhost:1337
创建用户 
配置kong, 使用ip:8001可以激活，使用服务名没有激活