# nacos启动，需要初始化数据库，nacocs服务不会初始化, 见init.sql

https://github.com/nacos-group/nacos-docker/issues/121
# nacos启动报错
需要在mysql参数加上 serverTimezone=UTC, 或者是因为mysql驱动引起，目前对mysql 8支持不好. 官方使用的是 nacos/nacos-mysql:8.0

MYSQL_SERVICE_DB_PARAM=characterEncoding=utf8&connectTimeout=10000&socketTimeout=3000&autoReconnect=true&useSSL=false&serverTimezone=UTC
nacos    | org.springframework.jdbc.CannotGetJdbcConnectionException: Failed to obtain JDBC Connection; nested exception is java.sql.SQLException: The connection property 'autoReconnect' acceptable values are: 'TRUE', 'FALSE', 'YES' or 'NO'. The value 'true?serverTimezone=UTC' is not acceptable.
nacos    | Caused by: java.lang.IllegalArgumentException: No enum constant com.mysql.cj.conf.BooleanPropertyDefinition.AllowableValues.TRUE?SERVERTIMEZONE=UTC
nacos    | No DataSource set
'externalDumpService': Invocation of init method failed; nested exception is ErrCode:500, ErrMsg:Nacos Server did not start because dumpservice bean construction failure :
nacos    | No DataSource set
nacos    | Caused by: com.alibaba.nacos.api.exception.NacosException: Nacos Server did not start because dumpservice bean construction failure :
nacos    | No DataSource set
nacos    | Caused by: java.lang.IllegalStateException: No DataSource set

# k8s部署报错nested exception is ErrCode:500, ErrMsg:jmenv.tbsite.net
没有配置NACOS_SERVERS
https://github.com/alibaba/nacos/issues/3597

# nacos web节点列表中服务有2个是down的现象
节点启动加入集群问题，重启nacos-1, nacos-2可解决

- 第一种现象
https://gitmemory.com/issue/alibaba/nacos/6134/865997315
```
(nacos-1.nacos-headless.default.svc.cluster.local:7848)] Failed to resolve name
```

- 第二种现象
```
curl GET "http://localhost:8848/nacos/v1/ns/raft/state"

curl: (6) Could not resolve host: GET; Unknown error
```

- 第三种现象，可以查看 protocol-distro.log， protocol-raft.log， naming-raft.log， start.out

```
可以查看 protocol-distro.log， protocol-raft.log， naming-raft.log， start.out

2021-07-19 11:47:20,486 ERROR error while sending vote to server: nacos-1.nacos-headless.nacos.svc.cluster.local:8848
        at sun.nio.ch.SocketChannelImpl.checkConnect(Native Method)
        at sun.nio.ch.SocketChannelImpl.finishConnect(SocketChannelImpl.java:716)
        at org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor.processEvent(DefaultConnectingIOReactor.java:174)
        at org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor.processEvents(DefaultConnectingIOReactor.java:148)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor.execute(AbstractMultiworkerIOReactor.java:351)
        at org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager.execute(PoolingNHttpClientConnectionManager.java:194)
        at org.apache.http.impl.nio.client.CloseableHttpAsyncClientBase$1.run(CloseableHttpAsyncClientBase.java:64)
        at java.lang.Thread.run(Thread.java:748)
2021-07-19 11:47:29,956 WARN [IS LEADER] no leader is available now!

2021-07-19 11:47:39,867 ERROR error while sending vote to server: nacos-2.nacos-headless.nacos.svc.cluster.local:8848

java.net.ConnectException: Connection refused
        at sun.nio.ch.SocketChannelImpl.checkConnect(Native Method)
        at sun.nio.ch.SocketChannelImpl.finishConnect(SocketChannelImpl.java:716)
```

- 第四种现象
```
可以查看 protocol-distro.log， protocol-raft.log， naming-raft.log， start.out
日志中 leader选举失败
```

# pod 启动一直  pending状态

注释文件中的affinity, 或者延长时间
```
affinity:
  podAntiAffinity:
    requiredDuringSchedulingIgnoredDuringExecution:
      - labelSelector:
          matchExpressions:
            - key: "app"
              operator: In
              values:
                - nacos
        topologyKey: "kubernetes.io/hostname"
```