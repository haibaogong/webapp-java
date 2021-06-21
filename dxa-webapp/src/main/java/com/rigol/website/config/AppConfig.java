package com.rigol.website.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * spring的配置类
 *
 * @author chen.nie
 * @date 2018/6/24
 **/
@Configuration //表明此类是配置类
@ComponentScan // 扫描自定义的组件(repository service component controller)
//@PropertySource("classpath:application.properties") // 读取application.properties
@MapperScan("com.rigol.website.dao") //扫描Mybatis的Mapper接口
@EnableTransactionManagement //开启事务管理
public class AppConfig {
    /**
     * 配置数据源
     *
     * @date 2018/6/24
     **/
    @Bean
    public DataSource dataSource(PropertiesConfig propertiesConfig) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(propertiesConfig.getUser());
        dataSource.setPassword(propertiesConfig.getPassword());
        dataSource.setUrl(propertiesConfig.getUrl());
        dataSource.setDriverClassName(propertiesConfig.getDriver());
        return dataSource;
    }

    /**
     * 配置mybatis的SqlSessionFactoryBean
     *
     * @param dataSource
     * @param propertiesConfig
     * @return
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource, PropertiesConfig propertiesConfig) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(propertiesConfig.getMybatisTypeAliasPackage());
        // 动态获取SqlMapper
        PathMatchingResourcePatternResolver classPathResource = new PathMatchingResourcePatternResolver();
//        sqlSessionFactoryBean.setMapperLocations(classPathResource.getResources(propertiesConfig.getMapperLocations()));

        return sqlSessionFactoryBean;
    }

    /**
     * 配置spring的声明式事务
     *
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        return dataSourceTransactionManager;

    }
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
//        return propertySourcesPlaceholderConfigurer;
//    }
}
