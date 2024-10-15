package org.example.possystembackendspring.config;

import jakarta.persistence.EntityManagerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "org.example.possystembackendspring")
@EnableJpaRepositories(basePackages = "org.example.possystembackendspring.dao")
@EnableTransactionManagement

public class WebAppRootConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
   public DataSource dataSource() {
      DriverManagerDataSource dmbs = new DriverManagerDataSource();
      dmbs.setDriverClassName("com.mysql.cj.jdbc.Driver");
      dmbs.setUrl("jdbc:mysql://localhost:3306/posspring?createDatabaseIfNotExist=true");
      dmbs.setUsername("root");
      dmbs.setPassword("loshani@123");
      return dmbs;
  }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("org.example.possystembackendspring");
        factory.setDataSource(dataSource());
        return factory;
    }
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
