# Reference
https://www.consul.io/docs/discovery/dns
https://www.consul.io/docs/k8s/dns
https://www.jianshu.com/p/80ad7ff37744
https://dev.to/mrkaran/dns-lookups-in-kubernetes-5cm1

# edit coreDns, restart coreDns pod  
```sh
~ k edit configmap/coredns -n kube-system
~ kubectl get pods -n kube-system -oname |grep coredns |xargs kubectl delete -n kube-system
```

```yml
apiVersion: v1
data:
  Corefile: |
    .:53 {
        errors
        log {
           class all
        }
        health {
           lameduck 5s
        }
        ready
        kubernetes cluster.local in-addr.arpa ip6.arpa {
           pods insecure
           fallthrough in-addr.arpa ip6.arpa
           ttl 30
        }
        prometheus :9153
        forward . /etc/resolv.conf {
           max_concurrent 1000
        }
        cache 30
        loop
        reload
        loadbalance
    }
    consul {
      errors
      log {
         class all
      }
      cache 30
      forward . 10.111.6.189
    }
kind: ConfigMap
metadata:
  creationTimestamp: "2021-09-23T06:43:19Z"
  name: coredns
  namespace: kube-system
  resourceVersion: "174258"
  uid: 9fdd67a3-120e-437e-a353-63a8950f8d2d
```

# tool
```sh
kubectl run -i --tty --image tutum/dnsutils dnsutils --restart=Never --rm /bin/sh
```

# search

### less 5 dot，dns-search-1.pgn
```sh
~ nslookup counting.service.localdc1.consul
Server:		10.96.0.10
Address:	10.96.0.10#53

Name:	counting.service.localdc1.consul
Address: 10.1.0.70
```

### more 5 dot，dns-search-2.pgn
```sh
~ nslookup counting.service.localdc1.consul.
Server:		10.96.0.10
Address:	10.96.0.10#53

Name:	counting.service.localdc1.consul
Address: 10.1.0.70
```