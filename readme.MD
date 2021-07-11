# 配置k8s & consul
[readme](https://github.com/pjhu/seata/tree/master/devops/consul/readme.MD)
# 创建数据库
```
cd devops
k apply -f cicd/mysql.yaml
```

# 配置权限管理
```
cd devops
k apply -f cicd/role.yml
```

# 配置 kong
[readme](https://github.com/pjhu/seata/tree/master/devops/kong/readme.MD)
# 配置seata
[readme](https://github.com/pjhu/seata/tree/master/devops/seata/readme.MD)

# 创建服务image
执行 business / account / order / storage项目下的cis

# 部署服务
部署服务的示例: https://konghq.com/blog/kubernetes-ingress-api-gateway
```
cd decvops/

k apply -f cicd/account-deploy.yaml
k apply -f cicd/order-deploy.yaml
k apply -f cicd/storage-deploy.yaml
k apply -f cicd/business-deploy.yaml
```

# 执行过程日志
[readme](https://github.com/pjhu/seata/tree/master/devops/local/readme.MD)

# 遇到的问题

#### 没有consul客户端
```
Bean 'configConsulProperties' of type [io.seata.spring.boot.autoconfigure.properties.config.ConfigConsulProperties] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
Exception in thread "consul-config-executor_1_1" java.lang.NoClassDefFoundError: com/ecwid/consul/v1/ConsulClient
```

#### seata server 链接mysql 8.0没有驱动，默认是v5的驱动
```
could not be instantiated: Failed to load 
driver class com.mysql.cj.jdbc.Driver in either of HikariConfig class loader or Thread 
context classloader

Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.

Reason: Failed to determine a suitable driver class
```

#### 调用api后， seata 表为空，undo_log为空
seata在执行完事务后，会删除表中数据，如果需要查看，进入debug模式，打断点

#### seata server 配置问题，导致business 不能注册到TC
```
cluster = "consul"
dc = "localdc1"
namespace = "seata"
```

```
seata.tx-service-group=${group_a} 
service.vgroup-mapping.${group_a}=${default}
grouplist.default: seata-server.seata:8091

另外consul中需要配置 service.vgroup-mapping.${group_a}=${default}
```

注册成功会有如下消息
```
register RM success. client version:1.4.2, server version:1.4.2,channel:[id: 0x6f2af96f, L:/10.1.0.73:58076 - R:/10.1.0.62:8091]
register TM success. client version:1.4.2, server version:1.4.2,channel:[id: 0x339ed68f, L:/10.1.0.73:57906 - R:/10.1.0.62:8091]
register success, cost 310 ms, version:1.4.2,role:RMROLE,channel:[id: 0x6f2af96f, L:/10.1.0.73:58076 - R:/10.1.0.62:8091]
register success, cost 10142 ms, version:1.4.2,role:TMROLE,channel:[id: 0x339ed68f, L:/10.1.0.73:57906 - R:/10.1.0.62:8091]
```

#### 内存不够会链接不上seata server
```
[eoutChecker_1_1] i.s.c.r.netty.NettyClientChannelManager  : no available service found in cluster 'shanghai', please make sure registry config correct and keep your seata server running
```

#### 其它问题
1. 服务启动后，出现注册成功，中间需要半分钟以上
2. appliycation.yaml中 config选择 consul，出现如下错误
```
ERROR 1 --- [nio-8080-exec-1] io.seata.config.ConfigFuture: config operation timeout,cost:5001 ms,op:GET,dataId:client.xxxxxxxxxxx
```
3. 当删除 business 或 storage服务，会出现如下问题
```
no available service found in cluster 'default', please make sure registry config correct and keep your seata server running
```