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
     forward . 10.109.200.205
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