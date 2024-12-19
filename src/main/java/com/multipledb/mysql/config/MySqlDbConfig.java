package com.multipledb.mysql.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "entityManagerFactoryBean",
		basePackages = {"com.multipledb.mysql.repo"},
		transactionManagerRef = "transactionManager"
)
public class MySqlDbConfig {
	
	@Autowired
	Environment environment;
	
//	datasource
	@Bean
	@Primary
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setUrl(environment.getProperty("primary.datasource.url"));
		dataSource.setUsername(environment.getProperty("primary.datasource.username"));
		dataSource.setPassword(environment.getProperty("primary.datasource.password"));
//		dataSource.setDriverClassName(environment.getProperty("primary.datasource.driver-class-name"));
		
		return dataSource;
	}
	
//	entityManageFactory
	@Bean(name = "entityManagerFactoryBean")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		
		bean.setDataSource(dataSource());
		
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		bean.setJpaVendorAdapter(adapter);
		
		Map<String, String> props = new HashMap<>();
//		props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.put("hibernate.jpa.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "update");
		bean.setJpaPropertyMap(props);
		
		bean.setPackagesToScan("com.multipledb.mysql.entities");
		
		return bean;
	}
	
//	platformTransactionManager
	@Bean(name = "transactionManager")
	@Primary
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return manager;
	}

}
