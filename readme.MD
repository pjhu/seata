# 技术栈
1. spring boot
2. jpa
3. seata
4. consul
5. kong
6. k8s

# 本地直接启动服务测试
需要checkout local分支
[readme](https://github.com/pjhu/seata/tree/master/devops/local/readme.MD)

### 执行过程日志
[readme](https://github.com/pjhu/seata/tree/master/devops/local/readme.MD)
### seata + consul + k8s服务自动同步到consul
需要checkout synctoconsul分支
目前没有consul client加载慢的问题，不建议这么使用
[readme](https://github.com/pjhu/seata/tree/master/devops/seata/consul/readme.MD)