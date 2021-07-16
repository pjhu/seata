# technical stack
1. spring boot
2. jpa
3. seata
4. consul
5. nacos
6. kong
7. k8s

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
   - local [setup local test enviroment]
      - consul [setup consul test enviroment]
      - nacos [setup nacos test enviroment]

# set up local test enviroment

[consul readme](https://github.com/pjhu/seata/tree/master/devops/local/consul/readme.MD)

[nacos readme](https://github.com/pjhu/seata/tree/master/devops/local/nacos/readme.MD)

# seata + consul + k8s sync to consul

目前有consul client加载慢的问题，不建议这么使用

[consul方式setup & 问题](https://github.com/pjhu/seata/tree/master/devops/k8s-seata/consul/readme.MD)

# seata + nacos