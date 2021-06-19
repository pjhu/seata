# setup consul

##### 参考配置文档
https://www.consul.io/docs/k8s/service-sync#sync-enable-disable
https://www.consul.io/docs/k8s/dns
https://github.com/hashicorp/demo-consul-101/tree/master/k8s
https://learn.hashicorp.com/tutorials/consul/service-mesh-deploy

##### 安装前
```
➜  devops git:(master) ✗ k get service -A
NAMESPACE     NAME         TYPE        CLUSTER-IP   EXTERNAL-IP   PORT(S)                  AGE
default       kubernetes   ClusterIP   10.96.0.1    <none>        443/TCP                  3m33s
kube-system   kube-dns     ClusterIP   10.96.0.10   <none>        53/UDP,53/TCP,9153/TCP   3m31s


➜  devops git:(master) ✗ k get pods -A
NAMESPACE     NAME                                     READY   STATUS    RESTARTS   AGE
kube-system   coredns-f9fd979d6-4xt42                  1/1     Running   0          3m46s
kube-system   coredns-f9fd979d6-6m9k2                  1/1     Running   0          3m46s
kube-system   etcd-docker-desktop                      1/1     Running   0          2m36s
kube-system   kube-apiserver-docker-desktop            1/1     Running   0          2m54s
kube-system   kube-controller-manager-docker-desktop   1/1     Running   0          2m53s
kube-system   kube-proxy-r87cc                         1/1     Running   0          3m46s
kube-system   kube-scheduler-docker-desktop            1/1     Running   0          2m28s
kube-system   storage-provisioner                      1/1     Running   0          3m38s
kube-system   vpnkit-controller                        1/1     Running   0          3m37s
```

##### 安装
```
kubectl create namespace consul
helm install -f consul.yml consul hashicorp/consul --version "0.31.1"  -n consul
```

##### 安装后
```
➜  devops git:(master) ✗ k get service -A
NAMESPACE     NAME                          TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)                                                                   AGE
consul        consul-connect-injector-svc   ClusterIP   10.108.119.190   <none>        443/TCP                                                                   29s
consul        consul-controller-webhook     ClusterIP   10.105.114.176   <none>        443/TCP                                                                   29s
consul        consul-dns                    ClusterIP   10.100.217.252   <none>        53/TCP,53/UDP                                                             29s
consul        consul-server                 ClusterIP   None             <none>        8500/TCP,8301/TCP,8301/UDP,8302/TCP,8302/UDP,8300/TCP,8600/TCP,8600/UDP   29s
consul        consul-ui                     ClusterIP   10.109.83.33     <none>        80/TCP                                                                    29s
default       kubernetes                    ClusterIP   10.96.0.1        <none>        443/TCP                                                                   6m9s
kube-system   kube-dns                      ClusterIP   10.96.0.10       <none>        53/UDP,53/TCP,9153/TCP                                                    6m7s

➜  devops git:(master) ✗ k get pods -A
NAMESPACE     NAME                                                          READY   STATUS    RESTARTS   AGE
consul        consul-connect-injector-webhook-deployment-8696dcddf6-rgwtq   1/1     Running   0          51s
consul        consul-controller-f84bc979d-7clzk                             1/1     Running   0          51s
consul        consul-server-0                                               1/1     Running   0          40s
consul        consul-sync-catalog-697b4f659b-tflfx                          0/1     Running   0          51s
consul        consul-webhook-cert-manager-cfbb689f7-lhmmn                   1/1     Running   0          51s
consul        consul-zjs2k                                                  1/1     Running   0          51s
kube-system   coredns-f9fd979d6-4xt42                                       1/1     Running   0          6m24s
kube-system   coredns-f9fd979d6-6m9k2                                       1/1     Running   0          6m24s
kube-system   etcd-docker-desktop                                           1/1     Running   0          5m14s
kube-system   kube-apiserver-docker-desktop                                 1/1     Running   0          5m32s
kube-system   kube-controller-manager-docker-desktop                        1/1     Running   0          5m31s
kube-system   kube-proxy-r87cc                                              1/1     Running   0          6m24s
kube-system   kube-scheduler-docker-desktop                                 1/1     Running   0          5m6s
kube-system   storage-provisioner                                           1/1     Running   0          6m16s
kube-system   vpnkit-controller                                             1/1     Running   0          6m15s


➜  devops git:(master) ✗ k get configmap -A
NAMESPACE     NAME                                 DATA   AGE
consul        consul-client-config                 3      69s
consul        consul-server-config                 3      69s
consul        consul-webhook-cert-manager-config   1      69s
consul        consul.hashicorp.com                 0      46s
kube-public   cluster-info                         2      6m47s
kube-system   coredns                              1      6m47s
kube-system   extension-apiserver-authentication   6      6m50s
kube-system   kube-proxy                           2      6m47s
kube-system   kubeadm-config                       2      6m48s
kube-system   kubelet-config-1.19                  1      6m48s
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
     forward . 10.97.61.111
   }
```

###### 错误处理
```
consul-sync-catalog-697b4f659b-tflfx 错误日志：
Error getting leader status: Get "http://192.168.65.4:8500/v1/status/leader": dial tcp 192.168.65.4:8500: connect: no route to host

修改dns配置后，删除dns的pod, 并不能使配置生效，需要重启mac docker desktop
```

###### 测试
```
k apply -f counting-role.yml
k apply -f counting-service.yaml
k apply -f counting-dashboard-service.yaml

k port-forward service/dashboard 9002:9002 -n dev
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
```
k apply -f konga.yaml -n kong
```
部署完后，打开localhost:1337
创建用户 
配置kong, 使用ip:8001可以激活，使用服务名没有激活

# 部署seata服务
#### 创建服务image
执行account / order /storage项目下的ci

查看pod下的container
```
kubectl get pods POD_NAME_HERE -o jsonpath='{.spec.containers[*].name}'
```
#### create database
```sh
k apply -f devops/cicd/mysql.yaml
```

```mysql
CREATE DATABASE `account` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE `account_order` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE `storage` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

insert into account(id, money) values(1, 10000);
insert into storage(id, count) values(1, 10000);
```
#### 部署服务
部署服务的示例: https://konghq.com/blog/kubernetes-ingress-api-gateway
```
cd decvops/cicd
k apply  -f account-deploy.yaml
k apply -f order-deploy.yaml
k apply -f storage-deploy.yaml
```



