package com.pjhu.account.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Slf4j
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public Properties masterProperties() {
        return new Properties();
    }


    @Bean(name = "masterDataSource")
    public DataSource master() {
        HikariConfig configuration = new HikariConfig(masterProperties());
        return new HikariDataSource(configuration);
    }

    /**
     * 需要将 DataSourceProxy 设置为主数据源，否则事务无法回滚
     *
     * @return The default datasource
     */
    @DependsOn({"masterDataSource"})
    @Bean(name = "dataSourceProxy")
    @Primary
    public DataSource dataSource() {
        return new DataSourceProxy(master());
    }

    @Bean
    @Primary
    public JpaTransactionManager transactionManager() {
        log.debug("transaction start");
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
}
