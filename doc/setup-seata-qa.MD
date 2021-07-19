# RM无法注册到nacos，链接不到seata， nacos注册的ip需要改变

https://github.com/seata/seata/issues/2522

# 调用api后， seata 表为空，undo_log为空
seata在执行完事务后，会删除表中数据，如果需要查看，进入debug模式，打断点

# seata server 链接mysql 8.0没有驱动，默认是v5的驱动
```
could not be instantiated: Failed to load 
driver class com.mysql.cj.jdbc.Driver in either of HikariConfig class loader or Thread 
context classloader

Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.

Reason: Failed to determine a suitable driver class
```

```
Exception in thread "main" io.seata.common.loader.EnhancedServiceNotFoundException: not found service provider for : io.seata.server.session.SessionManager caused by java.lang.IllegalStateException: Extension instance(definition: io.seata.common.loader.ExtensionDefinition@1d61ea96, class: interface io.seata.server.session.SessionManager)  could not be instantiated: the name of service provider for [io.seata.core.store.db.DataSourceProvid er] name is null
	at io.seata.common.loader.EnhancedServiceLoader$InnerEnhancedServiceLoader.createNewExtension(EnhancedServiceLoader.java:399)
	at io.seata.common.loader.EnhancedServiceLoader$InnerEnhancedServiceLoader.getExtensionInstance(EnhancedServiceLoader.java:388)
	at io.seata.common.loader.EnhancedServiceLoader$InnerEnhancedServiceLoader.loadExtension(EnhancedServiceLoader.java:356)
	at io.seata.common.loader.EnhancedServiceLoader$InnerEnhancedServiceLoader.load(EnhancedServiceLoader.java:245)
	at io.seata.common.loader.EnhancedServiceLoader$InnerEnhancedServiceLoader.access$200(EnhancedServiceLoader.java:190)
	at io.seata.common.loader.EnhancedServiceLoader.load(EnhancedServiceLoader.java:82)
	at io.seata.server.session.SessionHolder.init(SessionHolder.java:97)
	at io.seata.server.Server.main(Server.java:80)
Caused by: java.lang.IllegalArgumentException: the name of service provider for [io.seata.core.store.db.DataSourceProvider] name is null
	at io.seata.common.loader.EnhancedServiceLoader$InnerEnhancedServiceLoader.loadExtension(EnhancedServiceLoader.java:351)
	at io.seata.common.loader.EnhancedServiceLoader$InnerEnhancedServiceLoader.load(EnhancedServiceLoader.java:245)
	at io.seata.common.loader.EnhancedServiceLoader$InnerEnhancedServiceLoader.access$200(EnhancedServiceLoader.java:190)
	at io.seata.common.loader.EnhancedServiceLoader.load(EnhancedServiceLoader.java:82)
	at io.seata.server.storage.db.store.DataBaseTransactionStoreManager.<init>(DataBaseTransactionStoreManager.java:95)
	at io.seata.server.storage.db.store.DataBaseTransactionStoreManager.getInstance(DataBaseTransactionStoreManager.java:81)
	at io.seata.server.storage.db.session.DataBaseSessionManager.init(DataBaseSessionManager.java:77)
	at io.seata.common.loader.EnhancedServiceLoader$InnerEnhancedServiceLoader.initInstance(EnhancedServiceLoader.java:564)
	at io.seata.common.loader.EnhancedServiceLoader$InnerEnhancedServiceLoader.createNewExtension(EnhancedServiceLoader.java:395)
	... 7 more
```

# 其它问题
https://github.com/seata/seata/issues/2521
no available service 'default' found, please make sure registry config correct

https://blog.csdn.net/qq_37495786/article/details/103488402

# nacos没有启动，
00:40:58.422 ERROR --- [                     main] io.seata.server.Server                   : nettyServer init error:ErrCode:500, ErrMsg:failed to req API:/nacos/v1/ns/instance after all servers([nacos-headless.nacos.svc.cluster.local:8848]) tried: ErrCode:503, ErrMsg:server is DOWNnow, detailed error message: Optional[Distro protocol is not initialized]
==>
java.lang.RuntimeException: ErrCode:500, ErrMsg:failed to req API:/nacos/v1/ns/instance after all servers([nacos-headless.nacos.svc.cluster.local:8848]) tried: ErrCode:503, ErrMsg:server is DOWNnow, detailed error message: Optional[Distro protocol is not initialized]
	at io.seata.core.rpc.netty.NettyServerBootstrap.start(NettyServerBootstrap.java:157) ~[seata-core-1.4.2.jar:na]
	at io.seata.core.rpc.netty.AbstractNettyRemotingServer.init(AbstractNettyRemotingServer.java:55) ~[seata-core-1.4.2.jar:na]
	at io.seata.core.rpc.netty.NettyRemotingServer.init(NettyRemotingServer.java:52) ~[seata-core-1.4.2.jar:na]
	at io.seata.server.Server.main(Server.java:98) ~[classes/:na]
Caused by: com.alibaba.nacos.api.exception.NacosException: failed to req API:/nacos/v1/ns/instance after all servers([nacos-headless.nacos.svc.cluster.local:8848]) tried: ErrCode:503, ErrMsg:server is DOWNnow, detailed error message: Optional[Distro protocol is not initialized]
	at com.alibaba.nacos.client.naming.net.NamingProxy.reqApi(NamingProxy.java:552) ~[nacos-client-1.3.3.jar:na]
	at com.alibaba.nacos.client.naming.net.NamingProxy.reqApi(NamingProxy.java:491) ~[nacos-client-1.3.3.jar:na]
	at com.alibaba.nacos.client.naming.net.NamingProxy.reqApi(NamingProxy.java:486) ~[nacos-client-1.3.3.jar:na]
	at com.alibaba.nacos.client.naming.net.NamingProxy.registerService(NamingProxy.java:239) ~[nacos-client-1.3.3.jar:na]
	at com.alibaba.nacos.client.naming.NacosNamingService.registerInstance(NacosNamingService.java:200) ~[nacos-client-1.3.3.jar:na]
	at com.alibaba.nacos.client.naming.NacosNamingService.registerInstance(NacosNamingService.java:185) ~[nacos-client-1.3.3.jar:na]
	at io.seata.discovery.registry.nacos.NacosRegistryServiceImpl.register(NacosRegistryServiceImpl.java:86) ~[seata-discovery-nacos-1.4.2.jar:na]
	at io.seata.core.rpc.netty.NettyServerBootstrap.start(NettyServerBootstrap.java:153) ~[seata-core-1.4.2.jar:na]
	... 3 common frames omitted
<==
