# 启动business 服务

#### 更换配置： server.port=28080

#### 启动后日志
```
2021-07-10 16:34:19.801  INFO 57439 --- [           main] com.pjhu.storage.StorageApplication      : Started StorageApplication in 8.619 seconds (JVM running for 9.474)
2021-07-10 16:35:12.553  INFO 57439 --- [eoutChecker_1_1] i.s.c.r.netty.NettyClientChannelManager  : will connect to 127.0.0.1:8091
2021-07-10 16:35:12.566  INFO 57439 --- [eoutChecker_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : NettyPool create channel to transactionRole:TMROLE,address:127.0.0.1:8091,msg:< RegisterTMRequest{applicationId='storage', transactionServiceGroup='storage-fescar-service-group'} >
2021-07-10 16:35:12.582  INFO 57439 --- [eoutChecker_1_1] i.s.c.rpc.netty.TmNettyRemotingClient    : register TM success. client version:1.4.2, server version:1.4.2,channel:[id: 0x63310dbf, L:/127.0.0.1:57419 - R:/127.0.0.1:8091]
2021-07-10 16:35:12.582  INFO 57439 --- [eoutChecker_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : register success, cost 12 ms, version:1.4.2,role:TMROLE,channel:[id: 0x63310dbf, L:/127.0.0.1:57419 - R:/127.0.0.1:8091]
```
# 启动storage 服务

#### 更换配置：server.port=18080

#### 启动后日志
```
2021-07-10 16:35:28.924  INFO 57799 --- [           main] com.pjhu.business.BusinessApplication    : Started BusinessApplication in 2.556 seconds (JVM running for 3.383)
2021-07-10 16:36:27.748  INFO 57799 --- [eoutChecker_1_1] i.s.c.r.netty.NettyClientChannelManager  : will connect to 127.0.0.1:8091
2021-07-10 16:36:27.761  INFO 57799 --- [eoutChecker_2_1] i.s.c.r.netty.NettyClientChannelManager  : will connect to 127.0.0.1:8091
2021-07-10 16:36:27.762  INFO 57799 --- [eoutChecker_2_1] i.s.core.rpc.netty.NettyPoolableFactory  : NettyPool create channel to transactionRole:RMROLE,address:127.0.0.1:8091,msg:< RegisterRMRequest{resourceIds='null', applicationId='business', transactionServiceGroup='business-fescar-service-group'} >
2021-07-10 16:36:27.958  INFO 57799 --- [eoutChecker_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : NettyPool create channel to transactionRole:TMROLE,address:127.0.0.1:8091,msg:< RegisterTMRequest{applicationId='business', transactionServiceGroup='business-fescar-service-group'} >
2021-07-10 16:36:28.057  INFO 57799 --- [eoutChecker_2_1] i.s.c.rpc.netty.RmNettyRemotingClient    : register RM success. client version:1.4.2, server version:1.4.2,channel:[id: 0xf9560585, L:/127.0.0.1:57436 - R:/127.0.0.1:8091]
2021-07-10 16:36:28.057  INFO 57799 --- [eoutChecker_1_1] i.s.c.rpc.netty.TmNettyRemotingClient    : register TM success. client version:1.4.2, server version:1.4.2,channel:[id: 0xa648812a, L:/127.0.0.1:57437 - R:/127.0.0.1:8091]
2021-07-10 16:36:28.064  INFO 57799 --- [eoutChecker_2_1] i.s.core.rpc.netty.NettyPoolableFactory  : register success, cost 57 ms, version:1.4.2,role:RMROLE,channel:[id: 0xf9560585, L:/127.0.0.1:57436 - R:/127.0.0.1:8091]
2021-07-10 16:36:28.064  INFO 57799 --- [eoutChecker_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : register success, cost 57 ms, version:1.4.2,role:TMROLE,channel:[id: 0xa648812a, L:/127.0.0.1:57437 - R:/127.0.0.1:8091]
```
# 执行buy，查看日志

由于seata事物执行完，会删除seata个表中的数据，如果想查看，需要dubug
#### business 日志
```
2021-07-10 16:45:19.473 DEBUG 57439 --- [io-28080-exec-7] o.s.web.servlet.DispatcherServlet        : POST "/storages/decrease", parameters={}
2021-07-10 16:45:19.474 DEBUG 57439 --- [io-28080-exec-7] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to com.pjhu.storage.StorageController#decreaseStorage(StorageDecreaseCommand)
2021-07-10 16:45:19.475 DEBUG 57439 --- [io-28080-exec-7] m.m.a.RequestResponseBodyMethodProcessor : Read "application/json;charset=UTF-8" to [com.pjhu.storage.StorageDecreaseCommand@23c6d54b]
2021-07-10 16:45:19.480 DEBUG 57439 --- [io-28080-exec-7] o.h.e.t.internal.TransactionImpl         : On TransactionImpl creation, JpaCompliance#isJpaTransactionComplianceEnabled == false
2021-07-10 16:45:19.480 DEBUG 57439 --- [io-28080-exec-7] o.h.e.t.internal.TransactionImpl         : begin
2021-07-10 16:45:19.483 DEBUG 57439 --- [io-28080-exec-7] org.hibernate.SQL                        : select storage0_.id as id1_0_0_, storage0_.count as count2_0_0_ from storage storage0_ where storage0_.id=?
Hibernate: select storage0_.id as id1_0_0_, storage0_.count as count2_0_0_ from storage storage0_ where storage0_.id=?
2021-07-10 16:45:19.491 DEBUG 57439 --- [io-28080-exec-7] l.p.e.p.i.EntityReferenceInitializerImpl : On call to EntityIdentifierReaderImpl#resolve, EntityKey was already known; should only happen on root returns with an optional identifier specified
2021-07-10 16:45:19.492 DEBUG 57439 --- [io-28080-exec-7] o.h.engine.internal.TwoPhaseLoad         : Resolving attributes for [com.pjhu.storage.Storage#1]
2021-07-10 16:45:19.492 DEBUG 57439 --- [io-28080-exec-7] o.h.engine.internal.TwoPhaseLoad         : Processing attribute `count` : value = 9997
2021-07-10 16:45:19.492 DEBUG 57439 --- [io-28080-exec-7] o.h.engine.internal.TwoPhaseLoad         : Attribute (`count`)  - enhanced for lazy-loading? - false
2021-07-10 16:45:19.492 DEBUG 57439 --- [io-28080-exec-7] o.h.engine.internal.TwoPhaseLoad         : Done materializing entity [com.pjhu.storage.Storage#1]
2021-07-10 16:45:19.492 DEBUG 57439 --- [io-28080-exec-7] .l.e.p.AbstractLoadPlanBasedEntityLoader : Done entity load : com.pjhu.storage.Storage#1
2021-07-10 16:45:19.492 DEBUG 57439 --- [io-28080-exec-7] o.h.e.t.internal.TransactionImpl         : committing
2021-07-10 16:45:19.501 DEBUG 57439 --- [io-28080-exec-7] o.h.e.t.internal.TransactionImpl         : begin
2021-07-10 16:45:19.505 DEBUG 57439 --- [io-28080-exec-7] o.h.e.t.internal.TransactionImpl         : committing
2021-07-10 16:45:19.505 DEBUG 57439 --- [io-28080-exec-7] o.h.e.i.AbstractFlushingEventListener    : Processing flush-time cascades
2021-07-10 16:45:19.505 DEBUG 57439 --- [io-28080-exec-7] o.h.e.i.AbstractFlushingEventListener    : Dirty checking collections
2021-07-10 16:45:19.505 DEBUG 57439 --- [io-28080-exec-7] o.h.e.i.AbstractFlushingEventListener    : Flushed: 0 insertions, 1 updates, 0 deletions to 1 objects
2021-07-10 16:45:19.505 DEBUG 57439 --- [io-28080-exec-7] o.h.e.i.AbstractFlushingEventListener    : Flushed: 0 (re)creations, 0 updates, 0 removals to 0 collections
2021-07-10 16:45:19.505 DEBUG 57439 --- [io-28080-exec-7] o.hibernate.internal.util.EntityPrinter  : Listing entities:
2021-07-10 16:45:19.505 DEBUG 57439 --- [io-28080-exec-7] o.hibernate.internal.util.EntityPrinter  : com.pjhu.storage.Storage{count=9996, id=1}
2021-07-10 16:45:19.505 DEBUG 57439 --- [io-28080-exec-7] org.hibernate.SQL                        : update storage set count=? where id=?
Hibernate: update storage set count=? where id=?
2021-07-10 16:45:19.551 DEBUG 57439 --- [io-28080-exec-7] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Using 'application/json', given [*/*] and supported [application/json, application/*+json, application/json, application/*+json]
2021-07-10 16:45:19.551 DEBUG 57439 --- [io-28080-exec-7] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Writing [1]
2021-07-10 16:45:19.552 DEBUG 57439 --- [io-28080-exec-7] o.s.web.servlet.DispatcherServlet        : Completed 202 ACCEPTED
2021-07-10 16:45:19.861  INFO 57439 --- [h_RMROLE_1_4_24] i.s.c.r.p.c.RmBranchCommitProcessor      : rm client handle branch commit process:xid=10.1.0.126:8091:3053594002631459104,branchId=3053594002631459106,branchType=AT,resourceId=jdbc:mysql://localhost:3306/storage,applicationData=null
2021-07-10 16:45:19.861  INFO 57439 --- [h_RMROLE_1_4_24] io.seata.rm.AbstractRMHandler            : Branch committing: 10.1.0.126:8091:3053594002631459104 3053594002631459106 jdbc:mysql://localhost:3306/storage null
2021-07-10 16:45:19.861  INFO 57439 --- [h_RMROLE_1_4_24] io.seata.rm.AbstractRMHandler            : Branch commit result: PhaseTwo_Committed
```
#### storage日志
```
2021-07-10 16:45:19.443 DEBUG 57799 --- [io-18080-exec-7] o.s.web.servlet.DispatcherServlet        : POST "/business/buy", parameters={}
2021-07-10 16:45:19.444 DEBUG 57799 --- [io-18080-exec-7] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to com.pjhu.business.BusinessController#buy(BusinessCommand)
2021-07-10 16:45:19.446 DEBUG 57799 --- [io-18080-exec-7] m.m.a.RequestResponseBodyMethodProcessor : Read "application/json;charset=UTF-8" to [com.pjhu.business.BusinessCommand@77c92d43]
2021-07-10 16:45:19.467  INFO 57799 --- [io-18080-exec-7] i.seata.tm.api.DefaultGlobalTransaction  : Begin new global transaction [10.1.0.126:8091:3053594002631459104]
2021-07-10 16:45:19.553 DEBUG 57799 --- [io-18080-exec-7] o.s.w.c.HttpMessageConverterExtractor    : Reading to [java.lang.Long]
2021-07-10 16:45:19.554  INFO 57799 --- [io-18080-exec-7] c.p.business.BusinessApplicationService  : storageDecreaseResponse: <202 ACCEPTED Accepted,1,[connection:"keep-alive", content-type:"application/json", date:"Sat, 10 Jul 2021 08:45:19 GMT", keep-alive:"timeout=60", transfer-encoding:"chunked"]>
2021-07-10 16:45:19.591  INFO 57799 --- [io-18080-exec-7] i.seata.tm.api.DefaultGlobalTransaction  : Suspending current transaction, xid = 10.1.0.126:8091:3053594002631459104
2021-07-10 16:45:19.591  INFO 57799 --- [io-18080-exec-7] i.seata.tm.api.DefaultGlobalTransaction  : [10.1.0.126:8091:3053594002631459104] commit status: Committed
2021-07-10 16:45:19.592 DEBUG 57799 --- [io-18080-exec-7] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Using 'application/json', given [*/*] and supported [application/json, application/*+json, application/json, application/*+json]
2021-07-10 16:45:19.592 DEBUG 57799 --- [io-18080-exec-7] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Writing [1]
2021-07-10 16:45:19.594 DEBUG 57799 --- [io-18080-exec-7] o.s.web.servlet.DispatcherServlet        : Completed 202 ACCEPTED
```

# 执行buy-rollback，查看日志
#### business 服务日志
```
2021-07-10 16:46:19.761 DEBUG 57799 --- [io-18080-exec-8] o.s.web.servlet.DispatcherServlet        : POST "/business/buy-error", parameters={}
2021-07-10 16:46:19.764 DEBUG 57799 --- [io-18080-exec-8] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to com.pjhu.business.BusinessController#buyError(BusinessCommand)
2021-07-10 16:46:19.770 DEBUG 57799 --- [io-18080-exec-8] m.m.a.RequestResponseBodyMethodProcessor : Read "application/json;charset=UTF-8" to [com.pjhu.business.BusinessCommand@34587356]
2021-07-10 16:46:19.805  INFO 57799 --- [io-18080-exec-8] i.seata.tm.api.DefaultGlobalTransaction  : Begin new global transaction [10.1.0.126:8091:3053594002631459110]
2021-07-10 16:46:19.935 DEBUG 57799 --- [io-18080-exec-8] o.s.w.c.HttpMessageConverterExtractor    : Reading to [java.lang.Long]
2021-07-10 16:46:46.437  INFO 57799 --- [ctor_TMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : channel [id: 0x45b8c78f, L:/127.0.0.1:57523 - R:/127.0.0.1:8091] read idle.
2021-07-10 16:46:46.437  INFO 57799 --- [ctor_RMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : channel [id: 0xec2de0ff, L:/127.0.0.1:57529 - R:/127.0.0.1:8091] read idle.
2021-07-10 16:46:46.437  INFO 57799 --- [io-18080-exec-8] c.p.business.BusinessApplicationService  : storageDecreaseResponse: <202 ACCEPTED Accepted,1,[connection:"keep-alive", content-type:"application/json", date:"Sat, 10 Jul 2021 08:46:19 GMT", keep-alive:"timeout=60", transfer-encoding:"chunked"]>
2021-07-10 16:46:46.438  INFO 57799 --- [ctor_TMROLE_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : will destroy channel:[id: 0x45b8c78f, L:/127.0.0.1:57523 - R:/127.0.0.1:8091]
2021-07-10 16:46:46.438  INFO 57799 --- [ctor_RMROLE_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : will destroy channel:[id: 0xec2de0ff, L:/127.0.0.1:57529 - R:/127.0.0.1:8091]
2021-07-10 16:46:46.438  INFO 57799 --- [ctor_RMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : ChannelHandlerContext(AbstractNettyRemotingClient$ClientHandler#0, [id: 0xec2de0ff, L:/127.0.0.1:57529 - R:/127.0.0.1:8091]) will closed
2021-07-10 16:46:46.438  INFO 57799 --- [ctor_TMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : ChannelHandlerContext(AbstractNettyRemotingClient$ClientHandler#0, [id: 0x45b8c78f, L:/127.0.0.1:57523 - R:/127.0.0.1:8091]) will closed
2021-07-10 16:46:46.438  INFO 57799 --- [ctor_TMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : ChannelHandlerContext(AbstractNettyRemotingClient$ClientHandler#0, [id: 0x45b8c78f, L:/127.0.0.1:57523 ! R:/127.0.0.1:8091]) will closed
2021-07-10 16:46:46.438  INFO 57799 --- [ctor_RMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : ChannelHandlerContext(AbstractNettyRemotingClient$ClientHandler#0, [id: 0xec2de0ff, L:/127.0.0.1:57529 ! R:/127.0.0.1:8091]) will closed
2021-07-10 16:46:46.439  INFO 57799 --- [ctor_TMROLE_1_1] i.s.c.r.netty.NettyClientChannelManager  : return to pool, rm channel:[id: 0x45b8c78f, L:/127.0.0.1:57523 ! R:/127.0.0.1:8091]
2021-07-10 16:46:46.439  INFO 57799 --- [ctor_RMROLE_1_1] i.s.c.r.netty.NettyClientChannelManager  : return to pool, rm channel:[id: 0xec2de0ff, L:/127.0.0.1:57529 ! R:/127.0.0.1:8091]
2021-07-10 16:46:46.439  INFO 57799 --- [ctor_TMROLE_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : channel valid false,channel:[id: 0x45b8c78f, L:/127.0.0.1:57523 ! R:/127.0.0.1:8091]
2021-07-10 16:46:46.439  INFO 57799 --- [ctor_RMROLE_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : channel valid false,channel:[id: 0xec2de0ff, L:/127.0.0.1:57529 ! R:/127.0.0.1:8091]
2021-07-10 16:46:46.439  INFO 57799 --- [ctor_RMROLE_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : will destroy channel:[id: 0xec2de0ff, L:/127.0.0.1:57529 ! R:/127.0.0.1:8091]
2021-07-10 16:46:46.439  INFO 57799 --- [ctor_TMROLE_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : will destroy channel:[id: 0x45b8c78f, L:/127.0.0.1:57523 ! R:/127.0.0.1:8091]
2021-07-10 16:46:46.439  INFO 57799 --- [ctor_RMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : ChannelHandlerContext(AbstractNettyRemotingClient$ClientHandler#0, [id: 0xec2de0ff, L:/127.0.0.1:57529 ! R:/127.0.0.1:8091]) will closed
2021-07-10 16:46:46.439  INFO 57799 --- [ctor_TMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : ChannelHandlerContext(AbstractNettyRemotingClient$ClientHandler#0, [id: 0x45b8c78f, L:/127.0.0.1:57523 ! R:/127.0.0.1:8091]) will closed
2021-07-10 16:46:46.440  INFO 57799 --- [ctor_RMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : ChannelHandlerContext(AbstractNettyRemotingClient$ClientHandler#0, [id: 0xec2de0ff, L:/127.0.0.1:57529 ! R:/127.0.0.1:8091]) will closed
2021-07-10 16:46:46.440  INFO 57799 --- [ctor_TMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : ChannelHandlerContext(AbstractNettyRemotingClient$ClientHandler#0, [id: 0x45b8c78f, L:/127.0.0.1:57523 ! R:/127.0.0.1:8091]) will closed
2021-07-10 16:46:46.440  INFO 57799 --- [ctor_TMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : channel inactive: [id: 0x45b8c78f, L:/127.0.0.1:57523 ! R:/127.0.0.1:8091]
2021-07-10 16:46:46.440  INFO 57799 --- [ctor_RMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : channel inactive: [id: 0xec2de0ff, L:/127.0.0.1:57529 ! R:/127.0.0.1:8091]
2021-07-10 16:46:46.441  INFO 57799 --- [ctor_RMROLE_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : channel valid false,channel:[id: 0xec2de0ff, L:/127.0.0.1:57529 ! R:/127.0.0.1:8091]
2021-07-10 16:46:46.442  INFO 57799 --- [ctor_TMROLE_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : channel valid false,channel:[id: 0x45b8c78f, L:/127.0.0.1:57523 ! R:/127.0.0.1:8091]
2021-07-10 16:46:46.442  INFO 57799 --- [ctor_RMROLE_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : will destroy channel:[id: 0xec2de0ff, L:/127.0.0.1:57529 ! R:/127.0.0.1:8091]
2021-07-10 16:46:46.442  INFO 57799 --- [ctor_TMROLE_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : will destroy channel:[id: 0x45b8c78f, L:/127.0.0.1:57523 ! R:/127.0.0.1:8091]
2021-07-10 16:46:46.443  INFO 57799 --- [ctor_RMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : ChannelHandlerContext(AbstractNettyRemotingClient$ClientHandler#0, [id: 0xec2de0ff, L:/127.0.0.1:57529 ! R:/127.0.0.1:8091]) will closed
2021-07-10 16:46:46.443  INFO 57799 --- [ctor_TMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : ChannelHandlerContext(AbstractNettyRemotingClient$ClientHandler#0, [id: 0x45b8c78f, L:/127.0.0.1:57523 ! R:/127.0.0.1:8091]) will closed
2021-07-10 16:46:46.443  INFO 57799 --- [ctor_TMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : ChannelHandlerContext(AbstractNettyRemotingClient$ClientHandler#0, [id: 0x45b8c78f, L:/127.0.0.1:57523 ! R:/127.0.0.1:8091]) will closed
2021-07-10 16:46:46.443  INFO 57799 --- [ctor_RMROLE_1_1] i.s.c.r.n.AbstractNettyRemotingClient    : ChannelHandlerContext(AbstractNettyRemotingClient$ClientHandler#0, [id: 0xec2de0ff, L:/127.0.0.1:57529 ! R:/127.0.0.1:8091]) will closed
2021-07-10 16:46:47.762  INFO 57799 --- [eoutChecker_1_1] i.s.c.r.netty.NettyClientChannelManager  : will connect to 127.0.0.1:8091
2021-07-10 16:46:47.763  INFO 57799 --- [eoutChecker_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : NettyPool create channel to transactionRole:TMROLE,address:127.0.0.1:8091,msg:< RegisterTMRequest{applicationId='business', transactionServiceGroup='business-fescar-service-group'} >
2021-07-10 16:46:47.769  INFO 57799 --- [eoutChecker_1_1] i.s.c.rpc.netty.TmNettyRemotingClient    : register TM success. client version:1.4.2, server version:1.4.2,channel:[id: 0xc355231b, L:/127.0.0.1:57566 - R:/127.0.0.1:8091]
2021-07-10 16:46:47.769  INFO 57799 --- [eoutChecker_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : register success, cost 5 ms, version:1.4.2,role:TMROLE,channel:[id: 0xc355231b, L:/127.0.0.1:57566 - R:/127.0.0.1:8091]
2021-07-10 16:46:47.781  INFO 57799 --- [eoutChecker_2_1] i.s.c.r.netty.NettyClientChannelManager  : will connect to 127.0.0.1:8091
2021-07-10 16:46:47.782  INFO 57799 --- [eoutChecker_2_1] i.s.core.rpc.netty.NettyPoolableFactory  : NettyPool create channel to transactionRole:RMROLE,address:127.0.0.1:8091,msg:< RegisterRMRequest{resourceIds='null', applicationId='business', transactionServiceGroup='business-fescar-service-group'} >
2021-07-10 16:46:47.801  INFO 57799 --- [eoutChecker_2_1] i.s.c.rpc.netty.RmNettyRemotingClient    : register RM success. client version:1.4.2, server version:1.4.2,channel:[id: 0x66e1a113, L:/127.0.0.1:57567 - R:/127.0.0.1:8091]
2021-07-10 16:46:47.801  INFO 57799 --- [eoutChecker_2_1] i.s.core.rpc.netty.NettyPoolableFactory  : register success, cost 18 ms, version:1.4.2,role:RMROLE,channel:[id: 0x66e1a113, L:/127.0.0.1:57567 - R:/127.0.0.1:8091]
2021-07-10 16:46:48.111  INFO 57799 --- [io-18080-exec-8] i.seata.tm.api.DefaultGlobalTransaction  : Suspending current transaction, xid = 10.1.0.126:8091:3053594002631459110
2021-07-10 16:46:48.111  INFO 57799 --- [io-18080-exec-8] i.seata.tm.api.DefaultGlobalTransaction  : [10.1.0.126:8091:3053594002631459110] rollback status: Rollbacked
2021-07-10 16:46:48.139 DEBUG 57799 --- [io-18080-exec-8] o.s.web.servlet.DispatcherServlet        : Failed to complete request: java.lang.RuntimeException: 分布式事务回滚
2021-07-10 16:46:48.151 ERROR 57799 --- [io-18080-exec-8] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is java.lang.RuntimeException: 分布式事务回滚] with root cause

java.lang.RuntimeException: 分布式事务回滚
	at com.pjhu.business.BusinessApplicationService.buyError(BusinessApplicationService.java:47) ~[main/:na]
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
	at com.pjhu.business.BusinessApplicationService$$EnhancerBySpringCGLIB$$660306ae.buyError(<generated>) ~[main/:na]
	at com.pjhu.business.BusinessController.buyError(BusinessController.java:26) ~[main/:na]
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
	at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]

2021-07-10 16:46:48.177 DEBUG 57799 --- [io-18080-exec-8] o.s.web.servlet.DispatcherServlet        : "ERROR" dispatch for POST "/error", parameters={}
2021-07-10 16:46:48.181 DEBUG 57799 --- [io-18080-exec-8] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController#error(HttpServletRequest)
2021-07-10 16:46:48.222 DEBUG 57799 --- [io-18080-exec-8] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Using 'application/json', given [*/*] and supported [application/json, application/*+json, application/json, application/*+json]
2021-07-10 16:46:48.223 DEBUG 57799 --- [io-18080-exec-8] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Writing [{timestamp=Sat Jul 10 16:46:48 CST 2021, status=500, error=Internal Server Error, message=, path=/bu (truncated)...]
2021-07-10 16:46:48.248 DEBUG 57799 --- [io-18080-exec-8] o.s.web.servlet.DispatcherServlet        : Exiting from "ERROR" dispatch, status 500
```
#### storage 服务日志
```
2021-07-10 16:46:19.814 DEBUG 57439 --- [io-28080-exec-9] o.s.web.servlet.DispatcherServlet        : POST "/storages/decrease", parameters={}
2021-07-10 16:46:19.814 DEBUG 57439 --- [io-28080-exec-9] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to com.pjhu.storage.StorageController#decreaseStorage(StorageDecreaseCommand)
2021-07-10 16:46:19.815 DEBUG 57439 --- [io-28080-exec-9] m.m.a.RequestResponseBodyMethodProcessor : Read "application/json;charset=UTF-8" to [com.pjhu.storage.StorageDecreaseCommand@746b236b]
2021-07-10 16:46:19.824 DEBUG 57439 --- [io-28080-exec-9] o.h.e.t.internal.TransactionImpl         : On TransactionImpl creation, JpaCompliance#isJpaTransactionComplianceEnabled == false
2021-07-10 16:46:19.825 DEBUG 57439 --- [io-28080-exec-9] o.h.e.t.internal.TransactionImpl         : begin
2021-07-10 16:46:19.829 DEBUG 57439 --- [io-28080-exec-9] org.hibernate.SQL                        : select storage0_.id as id1_0_0_, storage0_.count as count2_0_0_ from storage storage0_ where storage0_.id=?
Hibernate: select storage0_.id as id1_0_0_, storage0_.count as count2_0_0_ from storage storage0_ where storage0_.id=?
2021-07-10 16:46:19.834 DEBUG 57439 --- [io-28080-exec-9] l.p.e.p.i.EntityReferenceInitializerImpl : On call to EntityIdentifierReaderImpl#resolve, EntityKey was already known; should only happen on root returns with an optional identifier specified
2021-07-10 16:46:19.835 DEBUG 57439 --- [io-28080-exec-9] o.h.engine.internal.TwoPhaseLoad         : Resolving attributes for [com.pjhu.storage.Storage#1]
2021-07-10 16:46:19.835 DEBUG 57439 --- [io-28080-exec-9] o.h.engine.internal.TwoPhaseLoad         : Processing attribute `count` : value = 9996
2021-07-10 16:46:19.835 DEBUG 57439 --- [io-28080-exec-9] o.h.engine.internal.TwoPhaseLoad         : Attribute (`count`)  - enhanced for lazy-loading? - false
2021-07-10 16:46:19.835 DEBUG 57439 --- [io-28080-exec-9] o.h.engine.internal.TwoPhaseLoad         : Done materializing entity [com.pjhu.storage.Storage#1]
2021-07-10 16:46:19.835 DEBUG 57439 --- [io-28080-exec-9] .l.e.p.AbstractLoadPlanBasedEntityLoader : Done entity load : com.pjhu.storage.Storage#1
2021-07-10 16:46:19.835 DEBUG 57439 --- [io-28080-exec-9] o.h.e.t.internal.TransactionImpl         : committing
2021-07-10 16:46:19.846 DEBUG 57439 --- [io-28080-exec-9] o.h.e.t.internal.TransactionImpl         : begin
2021-07-10 16:46:19.850 DEBUG 57439 --- [io-28080-exec-9] o.h.e.t.internal.TransactionImpl         : committing
2021-07-10 16:46:19.850 DEBUG 57439 --- [io-28080-exec-9] o.h.e.i.AbstractFlushingEventListener    : Processing flush-time cascades
2021-07-10 16:46:19.850 DEBUG 57439 --- [io-28080-exec-9] o.h.e.i.AbstractFlushingEventListener    : Dirty checking collections
2021-07-10 16:46:19.850 DEBUG 57439 --- [io-28080-exec-9] o.h.e.i.AbstractFlushingEventListener    : Flushed: 0 insertions, 1 updates, 0 deletions to 1 objects
2021-07-10 16:46:19.850 DEBUG 57439 --- [io-28080-exec-9] o.h.e.i.AbstractFlushingEventListener    : Flushed: 0 (re)creations, 0 updates, 0 removals to 0 collections
2021-07-10 16:46:19.850 DEBUG 57439 --- [io-28080-exec-9] o.hibernate.internal.util.EntityPrinter  : Listing entities:
2021-07-10 16:46:19.851 DEBUG 57439 --- [io-28080-exec-9] o.hibernate.internal.util.EntityPrinter  : com.pjhu.storage.Storage{count=9995, id=1}
2021-07-10 16:46:19.851 DEBUG 57439 --- [io-28080-exec-9] org.hibernate.SQL                        : update storage set count=? where id=?
Hibernate: update storage set count=? where id=?
2021-07-10 16:46:19.932 DEBUG 57439 --- [io-28080-exec-9] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Using 'application/json', given [*/*] and supported [application/json, application/*+json, application/json, application/*+json]
2021-07-10 16:46:19.933 DEBUG 57439 --- [io-28080-exec-9] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Writing [1]
2021-07-10 16:46:19.934 DEBUG 57439 --- [io-28080-exec-9] o.s.web.servlet.DispatcherServlet        : Completed 202 ACCEPTED
2021-07-10 16:46:47.807  INFO 57439 --- [h_RMROLE_1_5_24] i.s.c.r.p.c.RmBranchRollbackProcessor    : rm handle branch rollback process:xid=10.1.0.126:8091:3053594002631459110,branchId=3053594002631459112,branchType=AT,resourceId=jdbc:mysql://localhost:3306/storage,applicationData=null
2021-07-10 16:46:47.812  INFO 57439 --- [h_RMROLE_1_5_24] io.seata.rm.AbstractRMHandler            : Branch Rollbacking: 10.1.0.126:8091:3053594002631459110 3053594002631459112 jdbc:mysql://localhost:3306/storage
2021-07-10 16:46:48.028  INFO 57439 --- [h_RMROLE_1_5_24] i.s.r.d.undo.AbstractUndoLogManager      : xid 10.1.0.126:8091:3053594002631459110 branch 3053594002631459112, undo_log deleted with GlobalFinished
2021-07-10 16:46:48.031  INFO 57439 --- [h_RMROLE_1_5_24] io.seata.rm.AbstractRMHandler            : Branch Rollbacked result: PhaseTwo_Rollbacked
```