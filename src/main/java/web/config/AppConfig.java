package web.config;


import dao.UserDao;
import dao.UserDaoEntityImpl;
import dao.UserDaoIml;
import model.Car;
//import model.User;
import model.User;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.EntityManagerFactoryAccessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import service.UserService;
//import service.UserServiceEntityImpl;
import service.UserServiceImpl;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableJpaRepositories(basePackages = "java")

@EnableTransactionManagement
@ComponentScan(value = "java")
public class AppConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        System.out.println("Бин данных создан");
        return dataSource;
    }
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());

        Properties props=new Properties();
        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(User.class);
        System.out.println("Бин sessionFactory создан");
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public UserDao getUserDao() {
        return new UserDaoIml();
    }

    @Bean
    public UserService getUserService() {
        return new UserServiceImpl();
    }


//    @Bean
//    public LocalContainerEntityManagerFactoryBean getEntityManager() {
//        LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
//        emFactory.setDataSource(getDataSource());
//        emFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//        emFactory.setPackagesToScan("java.model");
//        emFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        Properties props = new Properties();
//        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//        emFactory.setJpaProperties(props);
//
//
//        System.out.println("Бин энтити создан " + emFactory.toString());
//        return emFactory;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(getEntityManager().getObject());
//        System.out.println("Бин транзакшен манагер создан");
//        return transactionManager;
//    }
//
//    @Bean
//    public UserServiceEntityImpl getUserService() {
//        return new UserServiceEntityImpl();
//    }
//
//    @Bean
//    public UserDaoEntityImpl getUserDao() {
//        return new UserDaoEntityImpl();
//    }

}

