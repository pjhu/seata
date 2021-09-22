# AT

### 测试条件

- 使用到的服务 Business + Storage
- Business的 BusinessApplicationService 上加注解 @GlobalTransactional
- Business服务无本地事务
- Business服务使用Feign 调用Storage服务
- Storage服务有本地事务

### 服务启动
初始化 TM / RM 
GlobalTransactionScanner::initClient

### debug client端执行流程

1. 执行curl
```
curl --location --request POST 'http://localhost:28080/business/buy' \
--header 'Content-Type: application/json' \
--data-raw '{
	"userId": 1,
	"productId": 1,
  "count": 1
}'
```

2. 【Business服务】 @GlobalTransactional client端执行 
```
-> BusinessController调用BusinessApplicationService.buy
-> 开启事务，拦截注解，GlobalTransactionalInterceptor::invoke 通过spring autoconfig 加载bean
-> handleGlobalTransaction
-> TransactionalTemplate.execute
-> beginTransaction 开启事务，<b>请求SeataServer 生成xid</b>
-> 执行业务，BusinessApplicationService.buy函数
-> 通过Feign调用storage, storageClient.decreaseStorage
-> 拦截feign, 调用ReflectiveFeign::invoke, 执行dispatch.get(method).invoke(args)，其中执行dispatch是 Map<Method, MethodHandler>，执行SynchronousMethodHandler::invoke
-> SynchronousMethodHandler::executeAndDecode
执行 client.execute，其中client是SeataFeignClient，替换了默认的client
-> SeataFeignClient::execute
-> SeataFeignClient::getModifyRequest <b>修改请求的header, 加上xid</b>
```

3. 【Storage 服务】@Transactional  client端执行 
```
-> Spring MVC 执行 SeataHandlerInterceptor，将xid 放入RootContext中
-> StorageController::decreaseStorage调用storageApplicationService.decreaseStorage
-> 拦截事务注解TransactionInterceptor.invoke
-> TransactionInterceptor.invoke
-> TransactionInterceptor.invokeWithinTransaction 开启事务, 调用实际的函数StorageApplicationService::decreaseStorage，函数调用结束后，执行事务提交
-> TransactionAspectSupport::commitTransactionAfterReturning
-> AbstractPlatformTransactionManager::commit
-> AbstractPlatformTransactionManager::processCommit
-> AbstractPlatformTransactionManager::doCommit
-> JpaTransactionManager::doCommit
-> TransactionImpl::commit
-> JdbcResourceLocalTransactionCoordinatorImpl::commit
-> AbstractLogicalConnectionImplementor::commit
-> ConnectionProxy::commit  进入seata lib
-> ConnectionProxy::doCommit
-> ConnectionProxy::processGlobalTransactionCommit, <b>插入undo log </b>, 然后继续提交事务
-> ConnectionImpl::commit 跳出seata lib, 执行事务提交
-> 执行成功，提交事务，持久化本服务的业务数据和undo log
-> 执行失败，回滚事务, 回滚本服务的业务数据和undo log
```
4. 【Business服务】 @GlobalTransactional client端执行 
```
-> 如果下游服务执行失败，进行回滚TransactionalTemplate::completeTransactionAfterThrowing, 通知下游服务回滚
-> AbstractNettyRemoting::processMessage (pair.getFirst().process(ctx, rpcMessage);)
-> RmBranchCommitProcessor::process
-> RmBranchCommitProcessor::handleBranchCommit (handler.onRequest(branchCommitRequest, null);)
-> AbstractRMHandler::onRequest
-> BranchRollbackRequest::handle
-> DefaultRMHandler::handle(BranchRollbackRequest request) 
-> AbstractRMHandler::handle(BranchRollbackRequest request)
-> AbstractRMHandler::doBranchRollback
-> DataSourceManager::branchRollback
-> AbstractUndoLogManager::undo

-> 如果执行成功，进行事务提交 TransactionalTemplate::commitTransaction, 通知下游服务提交，删除undo log
-> AbstractNettyRemoting::processMessage (pair.getFirst().process(ctx, rpcMessage);)
-> RmBranchCommitProcessor::process
-> RmBranchCommitProcessor::handleBranchCommit (handler.onRequest(branchCommitRequest, null);)
-> AbstractRMHandler::onRequest
-> BranchCommitRequest::handle
-> DefaultRMHandler::handle(BranchCommitRequest request)
-> AbstractRMHandler::handle(BranchCommitRequest request)
-> AbstractRMHandler::doBranchCommit
-> DataSourceManager::branchCommit
-> AsyncWorker::branchCommit, addToCommitQueue, doBranchCommitSafely, doBranchCommit, dealWithGroupedContexts
-> AsyncWorker::deleteUndoLog
```