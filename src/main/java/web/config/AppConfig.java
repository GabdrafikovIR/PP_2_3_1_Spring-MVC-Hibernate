package web.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class AppConfig {
   @Bean
   public DataSource dataSource() {
      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://localhost:3306/mydbtest1");
      dataSource.setUsername("root");
      dataSource.setPassword("root");
      return dataSource;
   }

   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean emf =
              new LocalContainerEntityManagerFactoryBean();
      emf.setDataSource(dataSource());
      emf.setPackagesToScan("com.example.crud.model");

      Properties jpaProperties = new Properties();
      jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
      jpaProperties.put("hibernate.show_sql", "true");
      emf.setJpaProperties(jpaProperties);

      return emf;
   }

   @Bean
   public PlatformTransactionManager transactionManager() {
      JpaTransactionManager transactionManager =
              new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(
              entityManagerFactory().getObject());
      return transactionManager;
   }
}
