# 启动business 服务

#### 更换配置： server.port=28080

#### 启动后日志
```
2021-08-07 19:55:51.167  INFO 79960 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 28080 (http) with context path ''
2021-08-07 19:55:51.195  INFO 79960 --- [           main] com.pjhu.business.BusinessApplication    : Started BusinessApplication in 3.281 seconds (JVM running for 3.907)
2021-08-07 19:55:51.629  INFO 79960 --- [on(6)-127.0.0.1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2021-08-07 19:55:51.630  INFO 79960 --- [on(6)-127.0.0.1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2021-08-07 19:55:51.630 DEBUG 79960 --- [on(6)-127.0.0.1] o.s.web.servlet.DispatcherServlet        : Detected StandardServletMultipartResolver
2021-08-07 19:55:51.635 DEBUG 79960 --- [on(6)-127.0.0.1] o.s.web.servlet.DispatcherServlet        : enableLoggingRequestDetails='false': request parameters and headers will be masked to prevent unsafe logging of potentially sensitive data
2021-08-07 19:55:51.635  INFO 79960 --- [on(6)-127.0.0.1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 5 ms
2021-08-07 19:56:50.088  INFO 79960 --- [eoutChecker_2_1] i.s.c.r.netty.NettyClientChannelManager  : will connect to 192.168.0.103:8091
2021-08-07 19:56:50.093  INFO 79960 --- [eoutChecker_2_1] i.s.core.rpc.netty.NettyPoolableFactory  : NettyPool create channel to transactionRole:RMROLE,address:192.168.0.103:8091,msg:< RegisterRMRequest{resourceIds='null', applicationId='business-service', transactionServiceGroup='business-tx-group'} >
2021-08-07 19:56:50.177  INFO 79960 --- [eoutChecker_1_1] i.s.c.r.netty.NettyClientChannelManager  : will connect to 192.168.0.103:8091
2021-08-07 19:56:50.190  INFO 79960 --- [eoutChecker_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : NettyPool create channel to transactionRole:TMROLE,address:192.168.0.103:8091,msg:< RegisterTMRequest{applicationId='business-service', transactionServiceGroup='business-tx-group'} >
2021-08-07 19:56:50.505 DEBUG 79960 --- [ctor_TMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : io.seata.core.rpc.netty.TmNettyRemotingClient@4b1221de msgId:1, body:version=1.4.2,extraData=null,identified=true,resultCode=null,msg=null
2021-08-07 19:56:50.505 DEBUG 79960 --- [ctor_RMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : io.seata.core.rpc.netty.RmNettyRemotingClient@79398d71 msgId:1, body:version=1.4.2,extraData=null,identified=true,resultCode=null,msg=null
2021-08-07 19:56:50.506  INFO 79960 --- [eoutChecker_2_1] i.s.c.rpc.netty.RmNettyRemotingClient    : register RM success. client version:1.4.2, server version:1.4.2,channel:[id: 0x91688ec4, L:/192.168.0.103:51114 - R:/192.168.0.103:8091]
2021-08-07 19:56:50.506  INFO 79960 --- [eoutChecker_1_1] i.s.c.rpc.netty.TmNettyRemotingClient    : register TM success. client version:1.4.2, server version:1.4.2,channel:[id: 0x8861d3df, L:/192.168.0.103:51115 - R:/192.168.0.103:8091]
2021-08-07 19:56:50.513  INFO 79960 --- [eoutChecker_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : register success, cost 321 ms, version:1.4.2,role:TMROLE,channel:[id: 0x8861d3df, L:/192.168.0.103:51115 - R:/192.168.0.103:8091]
2021-08-07 19:56:50.513  INFO 79960 --- [eoutChecker_2_1] i.s.core.rpc.netty.NettyPoolableFactory  : register success, cost 330 ms, version:1.4.2,role:RMROLE,channel:[id: 0x91688ec4, L:/192.168.0.103:51114 - R:/192.168.0.103:8091]
2021-08-07 19:57:00.310 DEBUG 79960 --- [ctor_TMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : will send ping msg,channel [id: 0x8861d3df, L:/192.168.0.103:51115 - R:/192.168.0.103:8091]
2021-08-07 19:57:00.310 DEBUG 79960 --- [ctor_RMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : will send ping msg,channel [id: 0x91688ec4, L:/192.168.0.103:51114 - R:/192.168.0.103:8091]
2021-08-07 19:57:00.311 DEBUG 79960 --- [ctor_RMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : write message:services ping, channel:[id: 0x91688ec4, L:/192.168.0.103:51114 - R:/192.168.0.103:8091],active?true,writable?true,isopen?true
2021-08-07 19:57:00.311 DEBUG 79960 --- [ctor_TMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : write message:services ping, channel:[id: 0x8861d3df, L:/192.168.0.103:51115 - R:/192.168.0.103:8091],active?true,writable?true,isopen?true
2021-08-07 19:57:00.314 DEBUG 79960 --- [ctor_RMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : io.seata.core.rpc.netty.RmNettyRemotingClient@79398d71 msgId:2, body:services pong
2021-08-07 19:57:00.314 DEBUG 79960 --- [ctor_TMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : io.seata.core.rpc.netty.TmNettyRemotingClient@4b1221de msgId:2, body:services pong
2021-08-07 19:57:00.314 DEBUG 79960 --- [ctor_TMROLE_1_1] i.s.c.r.p.c.ClientHeartbeatProcessor     : received PONG from /192.168.0.103:8091
2021-08-07 19:57:00.314 DEBUG 79960 --- [ctor_RMROLE_1_1] i.s.c.r.p.c.ClientHeartbeatProcessor     : received PONG from /192.168.0.103:8091
```
# 启动storage 服务

#### 更换配置：server.port=18080

#### 启动后日志
```
SeataDataSourceAutoConfiguration] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-08-07 19:55:25.597  INFO 79952 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 18080 (http)
2021-08-07 19:55:25.610  INFO 79952 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2021-08-07 19:55:25.611  INFO 79952 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.45]
2021-08-07 19:55:25.715  INFO 79952 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2021-08-07 19:55:25.715  INFO 79952 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1835 ms
2021-08-07 19:55:25.787  INFO 79952 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2021-08-07 19:55:26.122  INFO 79952 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2021-08-07 19:55:26.126  INFO 79952 --- [           main] .s.s.a.d.SeataAutoDataSourceProxyCreator : Auto proxy of [masterDataSource]
2021-08-07 19:55:26.369  INFO 79952 --- [           main] i.s.c.r.netty.NettyClientChannelManager  : will connect to 192.168.0.103:8091
2021-08-07 19:55:26.370  INFO 79952 --- [           main] i.s.c.rpc.netty.RmNettyRemotingClient    : RM will register :jdbc:mysql://localhost:3306/storage
2021-08-07 19:55:26.372  INFO 79952 --- [           main] i.s.core.rpc.netty.NettyPoolableFactory  : NettyPool create channel to transactionRole:RMROLE,address:192.168.0.103:8091,msg:< RegisterRMRequest{resourceIds='jdbc:mysql://localhost:3306/storage', applicationId='storage-service', transactionServiceGroup='business-tx-group'} >
2021-08-07 19:55:26.494 DEBUG 79952 --- [ctor_RMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : io.seata.core.rpc.netty.RmNettyRemotingClient@1a9c4cf6 msgId:1, body:version=1.4.2,extraData=null,identified=true,resultCode=null,msg=null
2021-08-07 19:55:26.495  INFO 79952 --- [           main] i.s.c.rpc.netty.RmNettyRemotingClient    : register RM success. client version:1.4.2, server version:1.4.2,channel:[id: 0xbbf25086, L:/192.168.0.103:51077 - R:/192.168.0.103:8091]
2021-08-07 19:55:26.501  INFO 79952 --- [           main] i.s.core.rpc.netty.NettyPoolableFactory  : register success, cost 70 ms, version:1.4.2,role:RMROLE,channel:[id: 0xbbf25086, L:/192.168.0.103:51077 - R:/192.168.0.103:8091]
2021-08-07 19:55:26.504  INFO 79952 --- [           main] .s.s.a.d.SeataAutoDataSourceProxyCreator : Auto proxy of [dataSourceProxy]
2021-08-07 19:55:26.512  INFO 79952 --- [           main] s.s.a.d.SeataDataSourceBeanPostProcessor : Unwrap the bean of the data source, and return the original data source to replace the data source proxy.
2021-08-07 19:55:26.515  INFO 79952 --- [           main] i.s.c.rpc.netty.RmNettyRemotingClient    : will register resourceId:jdbc:mysql://localhost:3306/storage
2021-08-07 19:55:26.515 DEBUG 79952 --- [           main] i.s.c.rpc.netty.AbstractNettyRemoting    : write message:RegisterRMRequest{resourceIds='jdbc:mysql://localhost:3306/storage', applicationId='storage-service', transactionServiceGroup='business-tx-group'}, channel:[id: 0xbbf25086, L:/192.168.0.103:51077 - R:/192.168.0.103:8091],active?true,writable?true,isopen?true
2021-08-07 19:55:26.519 DEBUG 79952 --- [ctor_RMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : io.seata.core.rpc.netty.RmNettyRemotingClient@1a9c4cf6 msgId:2, body:version=1.4.2,extraData=null,identified=true,resultCode=null,msg=null
2021-08-07 19:55:26.520  INFO 79952 --- [ctor_RMROLE_1_1] io.seata.rm.AbstractRMHandler            : the rm client received response msg [version=1.4.2,extraData=null,identified=true,resultCode=null,msg=null] from tc server.
2021-08-07 19:55:26.530  INFO 79952 --- [           main] o.f.c.internal.license.VersionPrinter    : Flyway Community Edition 6.4.4 by Redgate
2021-08-07 19:55:26.558  INFO 79952 --- [           main] o.f.c.internal.database.DatabaseFactory  : Database: jdbc:mysql://localhost:3306/storage (MySQL 8.0)
2021-08-07 19:55:26.681  INFO 79952 --- [           main] o.f.core.internal.command.DbValidate     : Successfully validated 1 migration (execution time 00:00.062s)
2021-08-07 19:55:26.720  INFO 79952 --- [           main] o.f.core.internal.command.DbMigrate      : Current version of schema `storage`: 20210507140412
2021-08-07 19:55:26.722  INFO 79952 --- [           main] o.f.core.internal.command.DbMigrate      : Schema `storage` is up to date. No migration necessary.
```
# 执行buy，查看日志

由于seata事物执行完，会删除seata个表中的数据，如果想查看，需要dubug
#### business 日志
```
2021-08-07 20:24:02.934 DEBUG 79960 --- [io-28080-exec-1] o.s.web.servlet.DispatcherServlet        : POST "/business/buy", parameters={}
2021-08-07 20:24:02.958 DEBUG 79960 --- [io-28080-exec-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to com.pjhu.business.BusinessController#buy(BusinessCommand)
2021-08-07 20:24:02.970 DEBUG 79960 --- [io-28080-exec-1] .s.i.h.TransactionPropagationInterceptor : xid in RootContext[null] xid in HttpContext[null]
2021-08-07 20:24:03.063 DEBUG 79960 --- [io-28080-exec-1] m.m.a.RequestResponseBodyMethodProcessor : Read "application/json;charset=UTF-8" to [com.pjhu.business.BusinessCommand@1a0eae3d]
2021-08-07 20:24:03.113  INFO 79960 --- [io-28080-exec-1] io.seata.tm.TransactionManagerHolder     : TransactionManager Singleton io.seata.tm.DefaultTransactionManager@4a1df473
2021-08-07 20:24:03.130 DEBUG 79960 --- [io-28080-exec-1] i.s.c.r.n.AbstractNettyRemotingClient    : offer message: timeout=300000,transactionName=gts-seata-buy
2021-08-07 20:24:03.132 DEBUG 79960 --- [Send_TMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : write message:SeataMergeMessage timeout=300000,transactionName=gts-seata-buy
, channel:[id: 0x8861d3df, L:/192.168.0.103:51115 - R:/192.168.0.103:8091],active?true,writable?true,isopen?true
2021-08-07 20:24:03.316 DEBUG 79960 --- [ctor_TMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : io.seata.core.rpc.netty.TmNettyRemotingClient@4b1221de msgId:166, body:MergeResultMessage xid=192.168.0.103:8091:36192235892649985;extraData=;

2021-08-07 20:24:03.317 DEBUG 79960 --- [io-28080-exec-1] io.seata.core.context.RootContext        : bind 192.168.0.103:8091:36192235892649985
2021-08-07 20:24:03.317  INFO 79960 --- [io-28080-exec-1] i.seata.tm.api.DefaultGlobalTransaction  : Begin new global transaction [192.168.0.103:8091:36192235892649985]
2021-08-07 20:24:04.887 DEBUG 79960 --- [io-28080-exec-1] o.s.w.c.HttpMessageConverterExtractor    : Reading to [java.lang.Long]
2021-08-07 20:24:04.888  INFO 79960 --- [io-28080-exec-1] c.p.business.BusinessApplicationService  : storageDecreaseResponse: <202 ACCEPTED Accepted,1,[connection:"keep-alive", content-type:"application/json", date:"Sat, 07 Aug 2021 12:24:04 GMT", keep-alive:"timeout=60", transfer-encoding:"chunked"]>
2021-08-07 20:24:04.889 DEBUG 79960 --- [io-28080-exec-1] i.s.c.r.n.AbstractNettyRemotingClient    : offer message: xid=192.168.0.103:8091:36192235892649985,extraData=null
2021-08-07 20:24:04.890 DEBUG 79960 --- [Send_TMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : write message:SeataMergeMessage xid=192.168.0.103:8091:36192235892649985,extraData=null
, channel:[id: 0x8861d3df, L:/192.168.0.103:51115 - R:/192.168.0.103:8091],active?true,writable?true,isopen?true
2021-08-07 20:24:04.922 DEBUG 79960 --- [ctor_TMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : io.seata.core.rpc.netty.TmNettyRemotingClient@4b1221de msgId:168, body:MergeResultMessage globalStatus=Committed,ResultCode=Success,Msg=null

2021-08-07 20:24:04.922  INFO 79960 --- [io-28080-exec-1] i.seata.tm.api.DefaultGlobalTransaction  : Suspending current transaction, xid = 192.168.0.103:8091:36192235892649985
2021-08-07 20:24:04.922 DEBUG 79960 --- [io-28080-exec-1] io.seata.core.context.RootContext        : unbind 192.168.0.103:8091:36192235892649985 
2021-08-07 20:24:04.922  INFO 79960 --- [io-28080-exec-1] i.seata.tm.api.DefaultGlobalTransaction  : [192.168.0.103:8091:36192235892649985] commit status: Committed
2021-08-07 20:24:04.930 DEBUG 79960 --- [io-28080-exec-1] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Using 'application/json', given [*/*] and supported [application/json, application/*+json, application/json, application/*+json]
2021-08-07 20:24:04.930 DEBUG 79960 --- [io-28080-exec-1] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Writing [1]
2021-08-07 20:24:04.940 DEBUG 79960 --- [io-28080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed 202 ACCEPTED
```
#### storage日志
```
Completed initialization in 91 ms
2021-08-07 20:24:04.024 DEBUG 79952 --- [io-18080-exec-1] o.s.web.servlet.DispatcherServlet        : POST "/storages/decrease", parameters={}
2021-08-07 20:24:04.035 DEBUG 79952 --- [io-18080-exec-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to com.pjhu.storage.StorageController#decreaseStorage(StorageDecreaseCommand)
2021-08-07 20:24:04.040 DEBUG 79952 --- [io-18080-exec-1] io.seata.core.context.RootContext        : bind 192.168.0.103:8091:36192235892649985
2021-08-07 20:24:04.040 DEBUG 79952 --- [io-18080-exec-1] .s.i.h.TransactionPropagationInterceptor : xid in RootContext[192.168.0.103:8091:36192235892649985] xid in HttpContext[192.168.0.103:8091:36192235892649985]
2021-08-07 20:24:04.154 DEBUG 79952 --- [io-18080-exec-1] m.m.a.RequestResponseBodyMethodProcessor : Read "application/json;charset=UTF-8" to [com.pjhu.storage.StorageDecreaseCommand@19d3fc90]
2021-08-07 20:24:04.186 DEBUG 79952 --- [io-18080-exec-1] o.h.e.t.internal.TransactionImpl         : On TransactionImpl creation, JpaCompliance#isJpaTransactionComplianceEnabled == false
2021-08-07 20:24:04.187 DEBUG 79952 --- [io-18080-exec-1] o.h.e.t.internal.TransactionImpl         : begin
2021-08-07 20:24:04.309 DEBUG 79952 --- [io-18080-exec-1] org.hibernate.SQL                        : select storage0_.id as id1_0_0_, storage0_.count as count2_0_0_ from storage storage0_ where storage0_.id=?
Hibernate: select storage0_.id as id1_0_0_, storage0_.count as count2_0_0_ from storage storage0_ where storage0_.id=?
2021-08-07 20:24:04.424 DEBUG 79952 --- [io-18080-exec-1] ServiceLoader$InnerEnhancedServiceLoader : The same extension io.seata.sqlparser.druid.mysql.MySQLOperateRecognizerHolder has already been loaded, skipped
2021-08-07 20:24:04.425 DEBUG 79952 --- [io-18080-exec-1] ServiceLoader$InnerEnhancedServiceLoader : The same extension io.seata.sqlparser.druid.oracle.OracleOperateRecognizerHolder has already been loaded, skipped
2021-08-07 20:24:04.425 DEBUG 79952 --- [io-18080-exec-1] ServiceLoader$InnerEnhancedServiceLoader : The same extension io.seata.sqlparser.druid.postgresql.PostgresqlOperateRecognizerHolder has already been loaded, skipped
2021-08-07 20:24:04.462 DEBUG 79952 --- [io-18080-exec-1] l.p.e.p.i.EntityReferenceInitializerImpl : On call to EntityIdentifierReaderImpl#resolve, EntityKey was already known; should only happen on root returns with an optional identifier specified
2021-08-07 20:24:04.491 DEBUG 79952 --- [io-18080-exec-1] o.h.engine.internal.TwoPhaseLoad         : Resolving attributes for [com.pjhu.storage.Storage#1]
2021-08-07 20:24:04.492 DEBUG 79952 --- [io-18080-exec-1] o.h.engine.internal.TwoPhaseLoad         : Processing attribute `count` : value = 10000
2021-08-07 20:24:04.492 DEBUG 79952 --- [io-18080-exec-1] o.h.engine.internal.TwoPhaseLoad         : Attribute (`count`)  - enhanced for lazy-loading? - false
2021-08-07 20:24:04.495 DEBUG 79952 --- [io-18080-exec-1] o.h.engine.internal.TwoPhaseLoad         : Done materializing entity [com.pjhu.storage.Storage#1]
2021-08-07 20:24:04.496 DEBUG 79952 --- [io-18080-exec-1] .l.e.p.AbstractLoadPlanBasedEntityLoader : Done entity load : com.pjhu.storage.Storage#1
2021-08-07 20:24:04.507 DEBUG 79952 --- [io-18080-exec-1] h.e.i.EntityCopyObserverFactoryInitiator : Configured EntityCopyObserver strategy: disallow
2021-08-07 20:24:04.517 DEBUG 79952 --- [io-18080-exec-1] o.h.e.t.internal.TransactionImpl         : committing
2021-08-07 20:24:04.519 DEBUG 79952 --- [io-18080-exec-1] o.h.e.i.AbstractFlushingEventListener    : Processing flush-time cascades
2021-08-07 20:24:04.519 DEBUG 79952 --- [io-18080-exec-1] o.h.e.i.AbstractFlushingEventListener    : Dirty checking collections
2021-08-07 20:24:04.530 DEBUG 79952 --- [io-18080-exec-1] o.h.e.i.AbstractFlushingEventListener    : Flushed: 0 insertions, 1 updates, 0 deletions to 1 objects
2021-08-07 20:24:04.530 DEBUG 79952 --- [io-18080-exec-1] o.h.e.i.AbstractFlushingEventListener    : Flushed: 0 (re)creations, 0 updates, 0 removals to 0 collections
2021-08-07 20:24:04.532 DEBUG 79952 --- [io-18080-exec-1] o.hibernate.internal.util.EntityPrinter  : Listing entities:
2021-08-07 20:24:04.533 DEBUG 79952 --- [io-18080-exec-1] o.hibernate.internal.util.EntityPrinter  : com.pjhu.storage.Storage{count=9999, id=1}
2021-08-07 20:24:04.535 DEBUG 79952 --- [io-18080-exec-1] org.hibernate.SQL                        : update storage set count=? where id=?
Hibernate: update storage set count=? where id=?
2021-08-07 20:24:04.756 DEBUG 79952 --- [io-18080-exec-1] i.s.c.r.n.AbstractNettyRemotingClient    : offer message: xid=192.168.0.103:8091:36192235892649985,branchType=AT,resourceId=jdbc:mysql://localhost:3306/storage,lockKey=storage:1
2021-08-07 20:24:04.757 DEBUG 79952 --- [Send_RMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : write message:SeataMergeMessage xid=192.168.0.103:8091:36192235892649985,branchType=AT,resourceId=jdbc:mysql://localhost:3306/storage,lockKey=storage:1
, channel:[id: 0xbbf25086, L:/192.168.0.103:51077 - R:/192.168.0.103:8091],active?true,writable?true,isopen?true
2021-08-07 20:24:04.809 DEBUG 79952 --- [ctor_RMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : io.seata.core.rpc.netty.RmNettyRemotingClient@1a9c4cf6 msgId:175, body:MergeResultMessage BranchRegisterResponse: branchId=36192235892649988,result code =Success,getMsg =null

2021-08-07 20:24:04.848 DEBUG 79952 --- [io-18080-exec-1] i.s.r.d.undo.AbstractUndoLogManager      : Flushing UNDO LOG: {"@class":"io.seata.rm.datasource.undo.BranchUndoLog","xid":"192.168.0.103:8091:36192235892649985","branchId":36192235892649988,"sqlUndoLogs":["java.util.ArrayList",[{"@class":"io.seata.rm.datasource.undo.SQLUndoLog","sqlType":"UPDATE","tableName":"storage","beforeImage":{"@class":"io.seata.rm.datasource.sql.struct.TableRecords","tableName":"storage","rows":["java.util.ArrayList",[{"@class":"io.seata.rm.datasource.sql.struct.Row","fields":["java.util.ArrayList",[{"@class":"io.seata.rm.datasource.sql.struct.Field","name":"id","keyType":"PRIMARY_KEY","type":-5,"value":["java.lang.Long",1]},{"@class":"io.seata.rm.datasource.sql.struct.Field","name":"count","keyType":"NULL","type":4,"value":10000}]]}]]},"afterImage":{"@class":"io.seata.rm.datasource.sql.struct.TableRecords","tableName":"storage","rows":["java.util.ArrayList",[{"@class":"io.seata.rm.datasource.sql.struct.Row","fields":["java.util.ArrayList",[{"@class":"io.seata.rm.datasource.sql.struct.Field","name":"id","keyType":"PRIMARY_KEY","type":-5,"value":["java.lang.Long",1]},{"@class":"io.seata.rm.datasource.sql.struct.Field","name":"count","keyType":"NULL","type":4,"value":9999}]]}]]}}]]}
2021-08-07 20:24:04.871 DEBUG 79952 --- [io-18080-exec-1] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Using 'application/json', given [*/*] and supported [application/json, application/*+json, application/json, application/*+json]
2021-08-07 20:24:04.871 DEBUG 79952 --- [io-18080-exec-1] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Writing [1]
2021-08-07 20:24:04.882 DEBUG 79952 --- [io-18080-exec-1] io.seata.core.context.RootContext        : unbind 192.168.0.103:8091:36192235892649985 
2021-08-07 20:24:04.882 DEBUG 79952 --- [io-18080-exec-1] io.seata.integration.http.XidResource    : unbind[192.168.0.103:8091:36192235892649985] from RootContext
2021-08-07 20:24:04.883 DEBUG 79952 --- [io-18080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed 202 ACCEPTED
2021-08-07 20:24:05.179 DEBUG 79952 --- [ctor_RMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : io.seata.core.rpc.netty.RmNettyRemotingClient@1a9c4cf6 msgId:2, body:xid=192.168.0.103:8091:36192235892649985,branchId=36192235892649988,branchType=AT,resourceId=jdbc:mysql://localhost:3306/storage,applicationData=null
2021-08-07 20:24:05.180  INFO 79952 --- [h_RMROLE_1_2_24] i.s.c.r.p.c.RmBranchCommitProcessor      : rm client handle branch commit process:xid=192.168.0.103:8091:36192235892649985,branchId=36192235892649988,branchType=AT,resourceId=jdbc:mysql://localhost:3306/storage,applicationData=null
2021-08-07 20:24:05.184  INFO 79952 --- [h_RMROLE_1_2_24] io.seata.rm.AbstractRMHandler            : Branch committing: 192.168.0.103:8091:36192235892649985 36192235892649988 jdbc:mysql://localhost:3306/storage null
2021-08-07 20:24:05.185  INFO 79952 --- [h_RMROLE_1_2_24] io.seata.rm.AbstractRMHandler            : Branch commit result: PhaseTwo_Committed
2021-08-07 20:24:05.185 DEBUG 79952 --- [h_RMROLE_1_2_24] i.s.c.r.p.c.RmBranchCommitProcessor      : branch commit result:xid=192.168.0.103:8091:36192235892649985,branchId=36192235892649988,branchStatus=PhaseTwo_Committed,result code =Success,getMsg =null
2021-08-07 20:24:05.185 DEBUG 79952 --- [h_RMROLE_1_2_24] i.s.c.rpc.netty.AbstractNettyRemoting    : write message:xid=192.168.0.103:8091:36192235892649985,branchId=36192235892649988,branchStatus=PhaseTwo_Committed,result code =Success,getMsg =null, channel:[id: 0xbbf25086, L:/192.168.0.103:51077 - R:/192.168.0.103:8091],active?true,writable?true,isopen?true
2021-08-07 20:24:05.381 DEBUG 79952 --- [yncWorker_1_2_2] i.s.r.d.undo.AbstractUndoLogManager      : batch delete undo log size 1
```

# 执行buy-rollback，查看日志
#### business 服务日志
```
2021-08-07 20:35:22.450 DEBUG 79960 --- [io-28080-exec-2] o.s.web.servlet.DispatcherServlet        : POST "/business/buy-rollback", parameters={}
2021-08-07 20:35:22.466 DEBUG 79960 --- [io-28080-exec-2] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to com.pjhu.business.BusinessController#buyRollBack(BusinessCommand)
2021-08-07 20:35:22.469 DEBUG 79960 --- [io-28080-exec-2] .s.i.h.TransactionPropagationInterceptor : xid in RootContext[null] xid in HttpContext[null]
2021-08-07 20:35:22.503 DEBUG 79960 --- [io-28080-exec-2] m.m.a.RequestResponseBodyMethodProcessor : Read "application/json;charset=UTF-8" to [com.pjhu.business.BusinessCommand@209cf360]
2021-08-07 20:35:22.518 DEBUG 79960 --- [io-28080-exec-2] i.s.c.r.n.AbstractNettyRemotingClient    : offer message: timeout=300000,transactionName=gts-seata-buy
2021-08-07 20:35:22.518 DEBUG 79960 --- [Send_TMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : write message:SeataMergeMessage timeout=300000,transactionName=gts-seata-buy
, channel:[id: 0x8861d3df, L:/192.168.0.103:51115 - R:/192.168.0.103:8091],active?true,writable?true,isopen?true
2021-08-07 20:35:22.534 DEBUG 79960 --- [ctor_TMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : io.seata.core.rpc.netty.TmNettyRemotingClient@4b1221de msgId:237, body:MergeResultMessage xid=192.168.0.103:8091:36192235892649992;extraData=;

2021-08-07 20:35:22.535 DEBUG 79960 --- [io-28080-exec-2] io.seata.core.context.RootContext        : bind 192.168.0.103:8091:36192235892649992
2021-08-07 20:35:22.535  INFO 79960 --- [io-28080-exec-2] i.seata.tm.api.DefaultGlobalTransaction  : Begin new global transaction [192.168.0.103:8091:36192235892649992]
2021-08-07 20:35:23.072 DEBUG 79960 --- [io-28080-exec-2] o.s.w.c.HttpMessageConverterExtractor    : Reading to [java.lang.Long]
2021-08-07 20:35:23.073  INFO 79960 --- [io-28080-exec-2] c.p.business.BusinessApplicationService  : storageDecreaseResponse: <202 ACCEPTED Accepted,1,[connection:"keep-alive", content-type:"application/json", date:"Sat, 07 Aug 2021 12:35:23 GMT", keep-alive:"timeout=60", transfer-encoding:"chunked"]>
2021-08-07 20:35:23.079 DEBUG 79960 --- [io-28080-exec-2] i.s.c.r.n.AbstractNettyRemotingClient    : offer message: xid=192.168.0.103:8091:36192235892649992,extraData=null
2021-08-07 20:35:23.079 DEBUG 79960 --- [Send_TMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : write message:SeataMergeMessage xid=192.168.0.103:8091:36192235892649992,extraData=null
, channel:[id: 0x8861d3df, L:/192.168.0.103:51115 - R:/192.168.0.103:8091],active?true,writable?true,isopen?true
2021-08-07 20:35:23.370 DEBUG 79960 --- [ctor_TMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : io.seata.core.rpc.netty.TmNettyRemotingClient@4b1221de msgId:239, body:MergeResultMessage globalStatus=Rollbacked,ResultCode=Success,Msg=null

2021-08-07 20:35:23.370  INFO 79960 --- [io-28080-exec-2] i.seata.tm.api.DefaultGlobalTransaction  : Suspending current transaction, xid = 192.168.0.103:8091:36192235892649992
2021-08-07 20:35:23.370 DEBUG 79960 --- [io-28080-exec-2] io.seata.core.context.RootContext        : unbind 192.168.0.103:8091:36192235892649992 
2021-08-07 20:35:23.372  INFO 79960 --- [io-28080-exec-2] i.seata.tm.api.DefaultGlobalTransaction  : [192.168.0.103:8091:36192235892649992] rollback status: Rollbacked
2021-08-07 20:35:23.442 DEBUG 79960 --- [io-28080-exec-2] o.s.web.servlet.DispatcherServlet        : Failed to complete request: java.lang.RuntimeException: 分布式事务回滚
2021-08-07 20:35:23.509 ERROR 79960 --- [io-28080-exec-2] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is java.lang.RuntimeException: 分布式事务回滚] with root cause

java.lang.RuntimeException: 分布式事务回滚
	at com.pjhu.business.BusinessApplicationService.buyRollBack(BusinessApplicationService.java:47) ~[main/:na]
	at com.pjhu.business.BusinessApplicationService$$FastClassBySpringCGLIB$$b4091e19.invoke(<generated>) ~[main/:na]
	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218) ~[spring-core-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:771) ~[spring-aop-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163) ~[spring-aop-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749) ~[spring-aop-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at io.seata.spring.annotation.GlobalTransactionalInterceptor$2.execute(GlobalTransactionalInterceptor.java:184) ~[seata-all-1.4.2.jar:1.4.2]
	at io.seata.tm.api.TransactionalTemplate.execute(TransactionalTemplate.java:127) ~[seata-all-1.4.2.jar:1.4.2]
	at io.seata.spring.annotation.GlobalTransactionalInterceptor.handleGlobalTransaction(GlobalTransactionalInterceptor.java:181) ~[seata-all-1.4.2.jar:1.4.2]
	at io.seata.spring.annotation.GlobalTransactionalInterceptor.invoke(GlobalTransactionalInterceptor.java:150) ~[seata-all-1.4.2.jar:1.4.2]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749) ~[spring-aop-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:691) ~[spring-aop-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at com.pjhu.business.BusinessApplicationService$$EnhancerBySpringCGLIB$$6666c0bc.buyRollBack(<generated>) ~[main/:na]
	at com.pjhu.business.BusinessController.buyRollBack(BusinessController.java:26) ~[main/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190) ~[spring-web-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138) ~[spring-web-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:105) ~[spring-webmvc-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:878) ~[spring-webmvc-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:792) ~[spring-webmvc-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87) ~[spring-webmvc-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1040) ~[spring-webmvc-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943) ~[spring-webmvc-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006) ~[spring-webmvc-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909) ~[spring-webmvc-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:652) ~[tomcat-embed-core-9.0.45.jar:4.0.FR]
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883) ~[spring-webmvc-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:733) ~[tomcat-embed-core-9.0.45.jar:4.0.FR]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:227) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53) ~[tomcat-embed-websocket-9.0.45.jar:9.0.45]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) ~[spring-web-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119) ~[spring-web-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93) ~[spring-web-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119) ~[spring-web-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.springframework.boot.actuate.metrics.web.servlet.WebMvcMetricsFilter.doFilterInternal(WebMvcMetricsFilter.java:93) ~[spring-boot-actuator-2.3.10.RELEASE.jar:2.3.10.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119) ~[spring-web-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201) ~[spring-web-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119) ~[spring-web-5.2.14.RELEASE.jar:5.2.14.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:357) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:893) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1707) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628) ~[na:na]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) ~[tomcat-embed-core-9.0.45.jar:9.0.45]
	at java.base/java.lang.Thread.run(Thread.java:829) ~[na:na]

2021-08-07 20:35:23.533 DEBUG 79960 --- [io-28080-exec-2] o.s.web.servlet.DispatcherServlet        : "ERROR" dispatch for POST "/error", parameters={}
2021-08-07 20:35:23.547 DEBUG 79960 --- [io-28080-exec-2] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController#error(HttpServletRequest)
2021-08-07 20:35:23.547 DEBUG 79960 --- [io-28080-exec-2] .s.i.h.TransactionPropagationInterceptor : xid in RootContext[null] xid in HttpContext[null]
2021-08-07 20:35:23.577 DEBUG 79960 --- [io-28080-exec-2] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Using 'application/json', given [*/*] and supported [application/json, application/*+json, application/json, application/*+json]
2021-08-07 20:35:23.578 DEBUG 79960 --- [io-28080-exec-2] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Writing [{timestamp=Sat Aug 07 20:35:23 CST 2021, status=500, error=Internal Server Error, message=, path=/bu (truncated)...]
2021-08-07 20:35:23.601 DEBUG 79960 --- [io-28080-exec-2] o.s.web.servlet.DispatcherServlet        : Exiting from "ERROR" dispatch, status 500
```
#### storage 服务日志
```
2021-08-07 20:35:22.635 DEBUG 79952 --- [io-18080-exec-3] o.s.web.servlet.DispatcherServlet        : POST "/storages/decrease", parameters={}
2021-08-07 20:35:22.669 DEBUG 79952 --- [io-18080-exec-3] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to com.pjhu.storage.StorageController#decreaseStorage(StorageDecreaseCommand)
2021-08-07 20:35:22.676 DEBUG 79952 --- [io-18080-exec-3] io.seata.core.context.RootContext        : bind 192.168.0.103:8091:36192235892649992
2021-08-07 20:35:22.676 DEBUG 79952 --- [io-18080-exec-3] .s.i.h.TransactionPropagationInterceptor : xid in RootContext[192.168.0.103:8091:36192235892649992] xid in HttpContext[192.168.0.103:8091:36192235892649992]
2021-08-07 20:35:22.738 DEBUG 79952 --- [io-18080-exec-3] m.m.a.RequestResponseBodyMethodProcessor : Read "application/json;charset=UTF-8" to [com.pjhu.storage.StorageDecreaseCommand@66da8c82]
2021-08-07 20:35:22.822 DEBUG 79952 --- [io-18080-exec-3] o.h.e.t.internal.TransactionImpl         : On TransactionImpl creation, JpaCompliance#isJpaTransactionComplianceEnabled == false
2021-08-07 20:35:22.825 DEBUG 79952 --- [io-18080-exec-3] o.h.e.t.internal.TransactionImpl         : begin
2021-08-07 20:35:22.903 DEBUG 79952 --- [io-18080-exec-3] org.hibernate.SQL                        : select storage0_.id as id1_0_0_, storage0_.count as count2_0_0_ from storage storage0_ where storage0_.id=?
Hibernate: select storage0_.id as id1_0_0_, storage0_.count as count2_0_0_ from storage storage0_ where storage0_.id=?
2021-08-07 20:35:22.940 DEBUG 79952 --- [io-18080-exec-3] l.p.e.p.i.EntityReferenceInitializerImpl : On call to EntityIdentifierReaderImpl#resolve, EntityKey was already known; should only happen on root returns with an optional identifier specified
2021-08-07 20:35:22.947 DEBUG 79952 --- [io-18080-exec-3] o.h.engine.internal.TwoPhaseLoad         : Resolving attributes for [com.pjhu.storage.Storage#1]
2021-08-07 20:35:22.947 DEBUG 79952 --- [io-18080-exec-3] o.h.engine.internal.TwoPhaseLoad         : Processing attribute `count` : value = 9999
2021-08-07 20:35:22.947 DEBUG 79952 --- [io-18080-exec-3] o.h.engine.internal.TwoPhaseLoad         : Attribute (`count`)  - enhanced for lazy-loading? - false
2021-08-07 20:35:22.948 DEBUG 79952 --- [io-18080-exec-3] o.h.engine.internal.TwoPhaseLoad         : Done materializing entity [com.pjhu.storage.Storage#1]
2021-08-07 20:35:22.950 DEBUG 79952 --- [io-18080-exec-3] .l.e.p.AbstractLoadPlanBasedEntityLoader : Done entity load : com.pjhu.storage.Storage#1
2021-08-07 20:35:22.955 DEBUG 79952 --- [io-18080-exec-3] o.h.e.t.internal.TransactionImpl         : committing
2021-08-07 20:35:22.956 DEBUG 79952 --- [io-18080-exec-3] o.h.e.i.AbstractFlushingEventListener    : Processing flush-time cascades
2021-08-07 20:35:22.956 DEBUG 79952 --- [io-18080-exec-3] o.h.e.i.AbstractFlushingEventListener    : Dirty checking collections
2021-08-07 20:35:22.960 DEBUG 79952 --- [io-18080-exec-3] o.h.e.i.AbstractFlushingEventListener    : Flushed: 0 insertions, 1 updates, 0 deletions to 1 objects
2021-08-07 20:35:22.960 DEBUG 79952 --- [io-18080-exec-3] o.h.e.i.AbstractFlushingEventListener    : Flushed: 0 (re)creations, 0 updates, 0 removals to 0 collections
2021-08-07 20:35:22.961 DEBUG 79952 --- [io-18080-exec-3] o.hibernate.internal.util.EntityPrinter  : Listing entities:
2021-08-07 20:35:22.962 DEBUG 79952 --- [io-18080-exec-3] o.hibernate.internal.util.EntityPrinter  : com.pjhu.storage.Storage{count=9998, id=1}
2021-08-07 20:35:22.963 DEBUG 79952 --- [io-18080-exec-3] org.hibernate.SQL                        : update storage set count=? where id=?
Hibernate: update storage set count=? where id=?
2021-08-07 20:35:23.030 DEBUG 79952 --- [io-18080-exec-3] i.s.c.r.n.AbstractNettyRemotingClient    : offer message: xid=192.168.0.103:8091:36192235892649992,branchType=AT,resourceId=jdbc:mysql://localhost:3306/storage,lockKey=storage:1
2021-08-07 20:35:23.031 DEBUG 79952 --- [Send_RMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : write message:SeataMergeMessage xid=192.168.0.103:8091:36192235892649992,branchType=AT,resourceId=jdbc:mysql://localhost:3306/storage,lockKey=storage:1
, channel:[id: 0xbbf25086, L:/192.168.0.103:51077 - R:/192.168.0.103:8091],active?true,writable?true,isopen?true
2021-08-07 20:35:23.047 DEBUG 79952 --- [ctor_RMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : io.seata.core.rpc.netty.RmNettyRemotingClient@1a9c4cf6 msgId:244, body:MergeResultMessage BranchRegisterResponse: branchId=36192235892649995,result code =Success,getMsg =null

2021-08-07 20:35:23.053 DEBUG 79952 --- [io-18080-exec-3] i.s.r.d.undo.AbstractUndoLogManager      : Flushing UNDO LOG: {"@class":"io.seata.rm.datasource.undo.BranchUndoLog","xid":"192.168.0.103:8091:36192235892649992","branchId":36192235892649995,"sqlUndoLogs":["java.util.ArrayList",[{"@class":"io.seata.rm.datasource.undo.SQLUndoLog","sqlType":"UPDATE","tableName":"storage","beforeImage":{"@class":"io.seata.rm.datasource.sql.struct.TableRecords","tableName":"storage","rows":["java.util.ArrayList",[{"@class":"io.seata.rm.datasource.sql.struct.Row","fields":["java.util.ArrayList",[{"@class":"io.seata.rm.datasource.sql.struct.Field","name":"id","keyType":"PRIMARY_KEY","type":-5,"value":["java.lang.Long",1]},{"@class":"io.seata.rm.datasource.sql.struct.Field","name":"count","keyType":"NULL","type":4,"value":9999}]]}]]},"afterImage":{"@class":"io.seata.rm.datasource.sql.struct.TableRecords","tableName":"storage","rows":["java.util.ArrayList",[{"@class":"io.seata.rm.datasource.sql.struct.Row","fields":["java.util.ArrayList",[{"@class":"io.seata.rm.datasource.sql.struct.Field","name":"id","keyType":"PRIMARY_KEY","type":-5,"value":["java.lang.Long",1]},{"@class":"io.seata.rm.datasource.sql.struct.Field","name":"count","keyType":"NULL","type":4,"value":9998}]]}]]}}]]}
2021-08-07 20:35:23.065 DEBUG 79952 --- [io-18080-exec-3] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Using 'application/json', given [*/*] and supported [application/json, application/*+json, application/json, application/*+json]
2021-08-07 20:35:23.065 DEBUG 79952 --- [io-18080-exec-3] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Writing [1]
2021-08-07 20:35:23.070 DEBUG 79952 --- [io-18080-exec-3] io.seata.core.context.RootContext        : unbind 192.168.0.103:8091:36192235892649992 
2021-08-07 20:35:23.070 DEBUG 79952 --- [io-18080-exec-3] io.seata.integration.http.XidResource    : unbind[192.168.0.103:8091:36192235892649992] from RootContext
2021-08-07 20:35:23.071 DEBUG 79952 --- [io-18080-exec-3] o.s.web.servlet.DispatcherServlet        : Completed 202 ACCEPTED
2021-08-07 20:35:23.115 DEBUG 79952 --- [ctor_RMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : io.seata.core.rpc.netty.RmNettyRemotingClient@1a9c4cf6 msgId:3, body:xid=192.168.0.103:8091:36192235892649992,branchId=36192235892649995,branchType=AT,resourceId=jdbc:mysql://localhost:3306/storage,applicationData=null
2021-08-07 20:35:23.118  INFO 79952 --- [h_RMROLE_1_3_24] i.s.c.r.p.c.RmBranchRollbackProcessor    : rm handle branch rollback process:xid=192.168.0.103:8091:36192235892649992,branchId=36192235892649995,branchType=AT,resourceId=jdbc:mysql://localhost:3306/storage,applicationData=null
2021-08-07 20:35:23.123  INFO 79952 --- [h_RMROLE_1_3_24] io.seata.rm.AbstractRMHandler            : Branch Rollbacking: 192.168.0.103:8091:36192235892649992 36192235892649995 jdbc:mysql://localhost:3306/storage
2021-08-07 20:35:23.334  INFO 79952 --- [h_RMROLE_1_3_24] i.s.r.d.undo.AbstractUndoLogManager      : xid 192.168.0.103:8091:36192235892649992 branch 36192235892649995, undo_log deleted with GlobalFinished
2021-08-07 20:35:23.338  INFO 79952 --- [h_RMROLE_1_3_24] io.seata.rm.AbstractRMHandler            : Branch Rollbacked result: PhaseTwo_Rollbacked
2021-08-07 20:35:23.338 DEBUG 79952 --- [h_RMROLE_1_3_24] i.s.c.r.p.c.RmBranchRollbackProcessor    : branch rollback result:xid=192.168.0.103:8091:36192235892649992,branchId=36192235892649995,branchStatus=PhaseTwo_Rollbacked,result code =Success,getMsg =null
2021-08-07 20:35:23.338 DEBUG 79952 --- [h_RMROLE_1_3_24] i.s.c.rpc.netty.AbstractNettyRemoting    : write message:xid=192.168.0.103:8091:36192235892649992,branchId=36192235892649995,branchStatus=PhaseTwo_Rollbacked,result code =Success,getMsg =null, channel:[id: 0xbbf25086, L:/192.168.0.103:51077 - R:/192.168.0.103:8091],active?true,writable?true,isopen?true
2021-08-07 20:35:26.873 DEBUG 79952 --- [ctor_TMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : will send ping msg,channel [id: 0x72722749, L:/192.168.0.103:51109 - R:/192.168.0.103:8091]
2021-08-07 20:35:26.873 DEBUG 79952 --- [ctor_TMROLE_1_1] i.s.c.rpc.netty.AbstractNettyRemoting    : write message:services ping, channel:[id: 0x72722749, L:/192.168.0.103:51109 - R:/192.168.0.103:8091],active?true,writable?true,isopen?true
```