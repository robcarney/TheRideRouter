package cs4500.group202.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Class for configuring DB connection properties.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DataConfig {

  @Autowired
  private Environment env;

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean sessionFactory =
        new LocalContainerEntityManagerFactoryBean();
    sessionFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan("cs4500.group202.data.model");
    sessionFactory.setJpaProperties(additionalProperties());
    return sessionFactory;
  }

  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    transactionManager.setDataSource(dataSource());
    return transactionManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
    return new PersistenceExceptionTranslationPostProcessor();
  }

  @Bean
  @Profile("dev")
  public DataSource dataSource() {
    BasicDataSource ds = new BasicDataSource();

    // DB Driver
    ds.setDriverClassName(env.getProperty("theride.db.driver"));

    // DB URL
    ds.setUrl(env.getProperty("theride.db.url"));

    // DB Login Credentials
    ds.setUsername(env.getProperty("theride.db.username"));
    ds.setPassword(env.getProperty("theride.db.password"));

    return ds;
  }

  @Bean(name = "dataSource")
  @Profile("prod")
  public DataSource jndiDataSource() {
    return new JndiDataSourceLookup().getDataSource(env.getProperty("theride.db.jndi"));
  }

  private Properties additionalProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
    properties.put("hibernate.implicit_naming_strategy",env.getProperty("hibernate.implicit_naming_strategy"));
    properties.put("hibernate.hbm2ddl.auto", "none");
    return properties;
  }

}
