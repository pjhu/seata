# technical stack
- spring boot
- jpa
- seata
- consul
- nacos
- kong
- k8s

# directory structure
- accout [test service]
- business [test service]
- order [test service]
- storage [test service]
- devops
   - k8s-cicd [deployment test services]
   - k8s-consul [setup consul]
   - k8s-seata [setup seata]
      - consul 
      - nacos
   - k8s-kong [setup kong]
   - k8s-nacos [setup nacos]
   - local [setup local test enviroment]
      - consul [setup consul test enviroment]
      - nacos [setup nacos test enviroment]

# seata + consul + dockercompose

[setup](https://github.com/pjhu/seata/tree/master/devops/local/consul/readme.MD)
# seata + nacos + dockercompose

[setup](https://github.com/pjhu/seata/tree/master/devops/local/nacos/readme.MD)

# seata + consul + k8s sync to consul

目前有consul client加载慢的问题，不建议这么使用

[setup](https://github.com/pjhu/seata/tree/master/devops/k8s-seata/consul/readme.MD)

# seata + nacos + k8s

[setup](https://github.com/pjhu/seata/tree/master/devops/k8s-seata/nacos/readme.MD)

# 目前使用consul作为配置中心有问题
https://github.com/seata/seata/issues/3777
https://juejin.cn/post/6976191318305275912
