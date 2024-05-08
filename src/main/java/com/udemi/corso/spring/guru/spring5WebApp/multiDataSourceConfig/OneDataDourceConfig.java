package com.udemi.corso.spring.guru.spring5WebApp.multiDataSourceConfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        basePackages={"com.udemi.corso.spring.guru.spring5WebApp.appuser","com.udemi.corso.spring.guru.spring5WebApp.registration.token","com.udemi.corso.spring.guru.spring5WebApp.mail"},
        entityManagerFactoryRef = "OneEntityManagerFactory",
        transactionManagerRef = "OneTransactionManager")
public class OneDataDourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.one")
    public DataSourceProperties OneDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource OnedataSource() {
        return OneDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean OneEntityManagerFactory(
            @Qualifier("OnedataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create");
        return builder
                .dataSource(dataSource)
                .packages("com.udemi.corso.spring.guru.spring5WebApp.appuser","com.udemi.corso.spring.guru.spring5WebApp.registration.token","com.udemi.corso.spring.guru.spring5WebApp.mail")
                .persistenceUnit("One")
                .properties(properties)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager OneTransactionManager(
            @Qualifier("OneEntityManagerFactory") LocalContainerEntityManagerFactoryBean OneEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(OneEntityManagerFactory.getObject()));
    }

}
