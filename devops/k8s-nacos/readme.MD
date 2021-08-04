# 参考文档
https://nacos.io/zh-cn/docs/use-nacos-with-kubernetes.html
https://nacos.io/zh-cn/docs/cluster-mode-quick-start.html
https://github.com/nacos-group/nacos-k8s/blob/master/README-CN.md

# setup
```
k create namespace nacos
k apply -f k8s-nacos/nacos-no-pvc.yaml
```

# 配置host
/etc/hosts下加
```
127.0.0.1 web.nacos.com
```

# 访问页面, 查看集群管理/节点列表

http://web.nacos.com/nacos

nacos:nacos