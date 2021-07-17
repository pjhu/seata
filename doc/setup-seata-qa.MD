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

