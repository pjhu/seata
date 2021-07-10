# set up k8s 

##### k8s 初始化后
```
➜  ~ docker images
REPOSITORY                           TAG                                                     IMAGE ID       CREATED         SIZE
docker/desktop-kubernetes            kubernetes-v1.21.2-cni-v0.8.5-critools-v1.17.0-debian   a502c6d66bd7   2 weeks ago     299MB
k8s.gcr.io/kube-apiserver            v1.21.2                                                 106ff58d4308   2 weeks ago     126MB
k8s.gcr.io/kube-proxy                v1.21.2                                                 a6ebd1c1ad98   2 weeks ago     131MB
k8s.gcr.io/kube-scheduler            v1.21.2                                                 f917b8c8f55b   2 weeks ago     50.6MB
k8s.gcr.io/kube-controller-manager   v1.21.2                                                 ae24db9aa2cc   2 weeks ago     120MB
docker/desktop-storage-provisioner   v2.0                                                    99f89471f470   2 months ago    41.9MB
k8s.gcr.io/pause                     3.4.1                                                   0f8457a4c2ec   5 months ago    683kB
k8s.gcr.io/coredns/coredns           v1.8.0                                                  296a6d5035e2   8 months ago    42.5MB
k8s.gcr.io/etcd                      3.4.13-0                                                0369cf4303ff   10 months ago   253MB


➜  ~ k get svc -A
NAMESPACE     NAME         TYPE        CLUSTER-IP   EXTERNAL-IP   PORT(S)                  AGE
default       kubernetes   ClusterIP   10.96.0.1    <none>        443/TCP                  5m30s
kube-system   kube-dns     ClusterIP   10.96.0.10   <none>        53/UDP,53/TCP,9153/TCP   5m28s


➜  ~ k get deployments -A
NAMESPACE     NAME      READY   UP-TO-DATE   AVAILABLE   AGE
kube-system   coredns   2/2     2            2           5m41s


➜  ~ k get pods -A
NAMESPACE     NAME                                     READY   STATUS    RESTARTS   AGE
kube-system   coredns-558bd4d5db-lldhm                 1/1     Running   0          5m27s
kube-system   coredns-558bd4d5db-xv6q4                 1/1     Running   0          5m27s
kube-system   etcd-docker-desktop                      1/1     Running   0          5m5s
kube-system   kube-apiserver-docker-desktop            1/1     Running   0          4m38s
kube-system   kube-controller-manager-docker-desktop   1/1     Running   0          3m55s
kube-system   kube-proxy-wrqkp                         1/1     Running   0          5m27s
kube-system   kube-scheduler-docker-desktop            1/1     Running   0          5m8s
kube-system   storage-provisioner                      1/1     Running   0          5m1s
kube-system   vpnkit-controller                        1/1     Running   0          5m1s
```
##### dashboard参考配置文档
https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/

##### dashboard配置
```
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.2.0/aio/deploy/recommended.yaml
kubectl proxy --port=8011
```

获取登录 token
```
kubectl -n kube-system describe secret deployment-controller-token-{}
```

打开网页
http://localhost:8011/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/

# setup consul

##### 参考配置文档
https://www.consul.io/docs/k8s/service-sync#sync-enable-disable
https://www.consul.io/docs/k8s/dns
https://github.com/hashicorp/demo-consul-101/tree/master/k8s
https://learn.hashicorp.com/tutorials/consul/service-mesh-deploy

##### 安装
```
kubectl create namespace consul
helm install -f consul.yml consul hashicorp/consul --version "0.31.1"  -n consul
```
##### 安装后
```
➜  devops git:(master) ✗ k get service -n consul
consul        consul-controller-webhook   ClusterIP   10.109.10.111   <none>        443/TCP                                                                   36s
consul        consul-dns                  ClusterIP   10.110.51.73    <none>        53/TCP,53/UDP                                                             36s
consul        consul-server               ClusterIP   None            <none>        8500/TCP,8301/TCP,8301/UDP,8302/TCP,8302/UDP,8300/TCP,8600/TCP,8600/UDP   36s
consul        consul-ui                   ClusterIP   10.96.97.42     <none>        80/TCP                                                                    36s

➜  consul git:(master) ✗ k get pods -n consul
NAME                                          READY   STATUS    RESTARTS   AGE
consul-controller-f84bc979d-dc4zt             1/1     Running   0          80s
consul-dzgpl                                  1/1     Running   0          80s
consul-server-0                               1/1     Running   0          80s
consul-sync-catalog-f7c99758d-h884c           1/1     Running   0          80s
consul-webhook-cert-manager-cfbb689f7-c5vxh   1/1     Running   0          80s


➜  consul git:(master) ✗ k get configmap -n consul
NAME                                 DATA   AGE
consul-client-config                 2      101s
consul-server-config                 3      101s
consul-webhook-cert-manager-config   1      101s
consul.hashicorp.com                 0      80s
kube-root-ca.crt                     1      2m38s
```

##### Viewing the Consul UI
```
k port-forward service/consul-ui 18500:80 --address 0.0.0.0 -n consul
```

###### 配置dns
```
k get svc consul-dns -o jsonpath='{.spec.clusterIP}' -n consul

➜  devops git:(master) ✗ k edit configmap/coredns -n kube-system

   consul {
     errors
     cache 30
     forward . 10.110.51.73
   }
```

###### 错误处理
```
consul-sync-catalog-697b4f659b-tflfx 错误日志：
Error getting leader status: Get "http://192.168.65.4:8500/v1/status/leader": dial tcp 192.168.65.4:8500: connect: no route to host

修改dns配置后，删除dns的pod, 并不能使配置生效，需要重启mac docker desktop
或执行下面的命令，删除pod
kubectl get pods -n kube-system -oname |grep coredns |xargs kubectl delete -n kube-system
```

###### 测试
https://github.com/hashicorp/demo-consul-101/tree/master/k8s
```
k create namespace dev
k apply -f counting-role.yml
k apply -f counting-service.yaml
k apply -f counting-dashboard-service.yaml

k port-forward service/dashboard 9002:9002 -n dev
打开网页 http://localhost:9002进行测试
```

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

# 部署seata服务
#### create services database
```sh
k apply -f cicd/mysql.yaml
```

```mysql
CREATE DATABASE `account` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE `account_order` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE `storage` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE `konga` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE `seata` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```

#### create seata database & tables
https://github.com/seata/seata/blob/develop/script/server/db/mysql.sql
见seata/mysql.sql

#### 部署seata服务
```
k create namespace seata
k apply -f seata-server.yaml
```

#### 配置seata到connsul
```
kubectl run -it --rm --restart=Never centos --image=centos sh -n dev

sh config.sh -h consul-server.consul -p 8500
```

#### 创建服务image
执行account / order /storage项目下的ci

#### 部署服务
部署服务的示例: https://konghq.com/blog/kubernetes-ingress-api-gateway
```
cd decvops/cicd
k apply  -f account-deploy.yaml
k apply -f order-deploy.yaml
k apply -f storage-deploy.yaml
k apply -f business-deploy.yaml
```

#### 查看pod下的container
```
kubectl get pods POD_NAME_HERE -o jsonpath='{.spec.containers[*].name}'
```






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

————————————————
版权声明：本文为CSDN博主「appleyk」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/Appleyk/article/details/108934133
Description:

Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.

Reason: Failed to determine a suitable driver class
```

#### seata server 配置问题，导致business 不能注册到TC
```
cluster = "consul"
dc = "localdc1"
namespace = "seata"
```

注册成功会有如下消息
```
register TM success
register RM success
```

#### 内存不够会链接不上seata server
```
[eoutChecker_1_1] i.s.c.r.netty.NettyClientChannelManager  : no available service found in cluster 'shanghai', please make sure registry config correct and keep your seata server running

```
#### 事务提交后，会清楚seata数据库表中的日志
```
https://github.com/seata/seata/issues/3018
```
```
mysql> show processlist;
+------+-----------------+-----------------+---------------+---------+--------+------------------------+------------------+
| Id   | User            | Host            | db            | Command | Time   | State                  | Info             |
+------+-----------------+-----------------+---------------+---------+--------+------------------------+------------------+
|    5 | event_scheduler | localhost       | NULL          | Daemon  | 172727 | Waiting on empty queue | NULL             |
| 1107 | root            | localhost       | account       | Query   |      0 | init                   | show processlist |
| 1108 | root            | 10.1.0.69:45980 | account       | Sleep   |   1030 |                        | NULL             |
| 1109 | root            | 10.1.0.69:45994 | account       | Sleep   |   1380 |                        | NULL             |
| 1110 | root            | 10.1.0.69:45996 | account       | Sleep   |   1380 |                        | NULL             |
| 1111 | root            | 10.1.0.69:45998 | account       | Sleep   |   1380 |                        | NULL             |
| 1112 | root            | 10.1.0.69:46000 | account       | Sleep   |   1380 |                        | NULL             |
| 1113 | root            | 10.1.0.69:46002 | account       | Sleep   |   1380 |                        | NULL             |
| 1114 | root            | 10.1.0.69:46004 | account       | Sleep   |   1380 |                        | NULL             |
| 1115 | root            | 10.1.0.69:46006 | account       | Sleep   |   1380 |                        | NULL             |
| 1116 | root            | 10.1.0.69:46008 | account       | Sleep   |   1380 |                        | NULL             |
| 1117 | root            | 10.1.0.69:46010 | account       | Sleep   |   1379 |                        | NULL             |
| 1118 | root            | 10.1.0.70:55586 | account_order | Sleep   |   1030 |                        | NULL             |
| 1119 | root            | 10.1.0.70:55610 | account_order | Sleep   |   1333 |                        | NULL             |
| 1120 | root            | 10.1.0.70:55612 | account_order | Sleep   |   1333 |                        | NULL             |
| 1121 | root            | 10.1.0.70:55614 | account_order | Sleep   |   1333 |                        | NULL             |
| 1122 | root            | 10.1.0.70:55618 | account_order | Sleep   |   1332 |                        | NULL             |
| 1123 | root            | 10.1.0.70:55620 | account_order | Sleep   |   1332 |                        | NULL             |
| 1124 | root            | 10.1.0.70:55624 | account_order | Sleep   |   1332 |                        | NULL             |
| 1125 | root            | 10.1.0.70:55626 | account_order | Sleep   |   1332 |                        | NULL             |
| 1126 | root            | 10.1.0.70:55628 | account_order | Sleep   |   1332 |                        | NULL             |
| 1127 | root            | 10.1.0.70:55630 | account_order | Sleep   |   1332 |                        | NULL             |
| 1128 | root            | 10.1.0.71:46644 | storage       | Sleep   |   1030 |                        | NULL             |
| 1129 | root            | 10.1.0.71:46654 | storage       | Sleep   |   1292 |                        | NULL             |
| 1130 | root            | 10.1.0.71:46656 | storage       | Sleep   |   1292 |                        | NULL             |
| 1131 | root            | 10.1.0.71:46658 | storage       | Sleep   |   1292 |                        | NULL             |
| 1132 | root            | 10.1.0.71:46660 | storage       | Sleep   |   1292 |                        | NULL             |
| 1133 | root            | 10.1.0.71:46662 | storage       | Sleep   |   1292 |                        | NULL             |
| 1134 | root            | 10.1.0.71:46664 | storage       | Sleep   |   1292 |                        | NULL             |
| 1135 | root            | 10.1.0.71:46670 | storage       | Sleep   |   1292 |                        | NULL             |
| 1136 | root            | 10.1.0.71:46674 | storage       | Sleep   |   1292 |                        | NULL             |
| 1137 | root            | 10.1.0.71:46678 | storage       | Sleep   |   1292 |                        | NULL             |
| 1138 | root            | 10.1.0.67:50164 | seata         | Sleep   |      1 |                        | NULL             |
| 1139 | root            | 10.1.0.67:50422 | seata         | Sleep   |      1 |                        | NULL             |
| 1140 | root            | 10.1.0.67:50636 | seata         | Sleep   |      1 |                        | NULL             |
| 1141 | root            | 10.1.0.67:50768 | seata         | Sleep   |      1 |                        | NULL             |
| 1142 | root            | 10.1.0.67:50882 | seata         | Sleep   |    944 |                        | NULL             |
+------+-----------------+-----------------+---------------+---------+--------+------------------------+------------------+
37 rows in set (0.00 sec)
```