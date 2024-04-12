package com.udemi.corso.spring.guru.spring5WebApp.multiDataSourceConfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.udemi.corso.spring.guru.spring5WebApp.library"},
        entityManagerFactoryRef = "TwoEntityManagerFactory",
        transactionManagerRef = "TwoTransactionManager")
public class TwoDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.two")
    public DataSourceProperties TwoDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource TwodataSource() {
        return TwoDataSourceProperties().initializeDataSourceBuilder().build();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean TwoEntityManagerFactory(
            @Qualifier("OnedataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create");
        return builder
                .dataSource(dataSource)
                .packages("com.udemi.corso.spring.guru.spring5WebApp.library")
                .persistenceUnit("Two")
                .properties(properties)
                .build();
    }

    @Bean
    public PlatformTransactionManager TwoTransactionManager(
            @Qualifier("TwoEntityManagerFactory") LocalContainerEntityManagerFactoryBean TwoEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(TwoEntityManagerFactory.getObject()));
    }

}
