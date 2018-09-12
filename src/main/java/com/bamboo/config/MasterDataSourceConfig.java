package com.bamboo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

//@Configuration
// 扫描 Mapper 接口并容器管理
//@MapperScan("cn.ibona.distribution.mapper")
//@EnableTransactionManagement
public class MasterDataSourceConfig {
    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "cn.ibona.distribution.mapper";
    static final String MAPPER_LOCATION = "classpath:/mapper/*.xml";



    @ConfigurationProperties(value = "datasource")
    @Bean(name = "datasource")
    public DruidDataSource datasource() {
        return new DruidDataSource();
    }
    /**
     * 创建SessionFactory
     *
             * @param datasource
     * @return
             */
    //    @Primary
    @Bean(name = "sessionFactory")
    @DependsOn(value = {"datasource"})
    @ConfigurationProperties(prefix = "mybatis-plus")
    @ConfigurationPropertiesBinding()
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier(value = "datasource") DataSource datasource) throws SQLException, IOException {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(datasource);
        mybatisSqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MasterDataSourceConfig.MAPPER_LOCATION));
        return mybatisSqlSessionFactoryBean;
    }
}