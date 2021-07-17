# 服务的appliycation.yaml中 config选择 consul，出现如下错误, 目前没有解决，不建议这么使用

```
ERROR 1 --- [nio-8080-exec-1] io.seata.config.ConfigFuture: config operation timeout,cost:5001 ms,op:GET,dataId:client.xxxxxxxxxxx
```
原因【此问题没有修复】：
https://github.com/seata/seata/issues/3777
https://github.com/seata/seata/issues/3778

![alt text](https://github.com/pjhu/seata/tree/master/devops/seata/consul/doc/consul-bean.png

ConsulConfiguration::getLatestConfig 中会创建consul客户端，有5s的超时，超时后，执行configFuture.get()会报此错误
```
ConfigFuture configFuture = new ConfigFuture(dataId, defaultValue, ConfigFuture.ConfigOperation.GET,
                timeoutMills);
consulNotifierExecutor.execute(() -> complete(getConsulClient().getKVValue(dataId, getAclToken()), configFuture));
return (String) configFuture.get();
```

调用链:
```
ConsulConfiguration::getConsulClient 调用 FILE_CONFIG.getConfig(FILE_CONFIG_KEY_PREFIX + SERVER_ADDR_KEY)
->
SpringBootConfigurationProvider::provide -> result = get(convertDataId(rawDataId));
-> 
SpringBootConfigurationProvider::get
Class<?> propertyClass = PROPERTY_BEAN_MAP.get(propertyPrefix); 是从application.yaml中获取配置数据
Object propertyBean = applicationContext.getBean(propertyClass); 此步骤非常的耗时
io.seata.spring.boot.autoconfigure.properties.config.ConfigProperties
```

![服务加载consul bean非常慢](https://github.com/pjhu/seata/tree/master/connect-consul-slow.png) 

### 遇到的问题

#### 没有consul客户端
```
Bean 'configConsulProperties' of type [io.seata.spring.boot.autoconfigure.properties.config.ConfigConsulProperties] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
Exception in thread "consul-config-executor_1_1" java.lang.NoClassDefFoundError: com/ecwid/consul/v1/ConsulClient
```

#### seata server 配置问题，导致business 不能注册到TC
```
cluster = "consul"
dc = "localdc1"
namespace = "seata"
```

```
seata.tx-service-group=${group_a} 
service.vgroup-mapping.${group_a}=${default}
grouplist.default: seata-server.seata:8091

另外consul中需要配置 service.vgroup-mapping.${group_a}=${default}
```

注册成功会有如下消息
```
register RM success. client version:1.4.2, server version:1.4.2,channel:[id: 0x6f2af96f, L:/10.1.0.73:58076 - R:/10.1.0.62:8091]
register TM success. client version:1.4.2, server version:1.4.2,channel:[id: 0x339ed68f, L:/10.1.0.73:57906 - R:/10.1.0.62:8091]
register success, cost 310 ms, version:1.4.2,role:RMROLE,channel:[id: 0x6f2af96f, L:/10.1.0.73:58076 - R:/10.1.0.62:8091]
register success, cost 10142 ms, version:1.4.2,role:TMROLE,channel:[id: 0x339ed68f, L:/10.1.0.73:57906 - R:/10.1.0.62:8091]
```

#### 当删除 business 或 storage服务，会出现如下问题
```
no available service found in cluster 'default', please make sure registry config correct and keep your seata server running
```