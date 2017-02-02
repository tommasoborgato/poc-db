package com.example.postgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Created by icttb0 on 24/01/2017.
 */

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgresEntityManager",
        transactionManagerRef = "postgresTransactionManager"
)
@EnableTransactionManagement
@PropertySource({ "classpath:postgres.properties" })
public class PostgresConfig {

    private static final boolean USE_PESISTENCE_FILE = false;

    @Autowired
    private Environment env;

    @Bean("postgresDataSource")
    @ConfigurationProperties(prefix="postgres.datasource")
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManager() {
        if (USE_PESISTENCE_FILE) {
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setPersistenceUnitName("postgres-pu");
            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            em.setJpaVendorAdapter(vendorAdapter);
            return em;
        }else{

            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(postgresDataSource());
            em.setPackagesToScan(new String[] { env.getProperty("postgres.hibernate.packages_to_scan") });

            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            em.setJpaVendorAdapter(vendorAdapter);
            HashMap<String, Object> properties = new HashMap<String, Object>();
            properties.put("hibernate.archive.autodetection", env.getProperty("postgres.hibernate.archive.autodetection"));
            properties.put("hibernate.hbm2ddl.auto", env.getProperty("postgres.hibernate.hbm2ddl.auto"));
            properties.put("hibernate.dialect", env.getProperty("postgres.hibernate.dialect"));
            properties.put("hibernate.default_schema", env.getProperty("postgres.hibernate.default_schema"));
            em.setJpaPropertyMap(properties);

            return em;
        }
    }

    @Bean
    public PlatformTransactionManager postgresTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(postgresEntityManager().getObject());
        return transactionManager;
    }

}
