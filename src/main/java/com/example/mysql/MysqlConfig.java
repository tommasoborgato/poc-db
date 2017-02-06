package com.example.mysql;

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
        entityManagerFactoryRef = "mysqlEntityManager",
        transactionManagerRef = "mysqlTransactionManager"
)
@EnableTransactionManagement
@PropertySource({ "classpath:mysql.properties" })
public class MysqlConfig {

    private static final boolean USE_PESISTENCE_FILE = false;

    @Autowired
    private Environment env;

    @Bean("mySqlDataSource")
    @ConfigurationProperties(prefix="mysql.datasource")
    public DataSource mySqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean mysqlEntityManager() {
        if (USE_PESISTENCE_FILE) {
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setPersistenceUnitName("mysql-pu");
            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            em.setJpaVendorAdapter(vendorAdapter);
            return em;
        } else {
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(mySqlDataSource());
            em.setPackagesToScan(new String[] { env.getProperty("mysql.hibernate.packages_to_scan") });
            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            em.setJpaVendorAdapter(vendorAdapter);
            HashMap<String, Object> properties = new HashMap<String, Object>();
            properties.put("hibernate.archive.autodetection", env.getProperty("mysql.hibernate.archive.autodetection"));
            properties.put("hibernate.hbm2ddl.auto", env.getProperty("mysql.hibernate.hbm2ddl.auto"));
            properties.put("hibernate.dialect", env.getProperty("mysql.hibernate.dialect"));
            properties.put("hibernate.default_schema", env.getProperty("mysql.hibernate.default_schema"));
            em.setPersistenceUnitName( env.getProperty("mysql.persistence_unit_name") );
            em.setJpaPropertyMap(properties);
            return em;
        }
    }

    @Bean
    public PlatformTransactionManager mysqlTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mysqlEntityManager().getObject());
        return transactionManager;
    }

}
