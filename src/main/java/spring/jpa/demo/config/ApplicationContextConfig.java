package spring.jpa.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;

/**
 * @author ngoc on 24/03/2018
 * @project spring-data-jpa
 */
@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@EnableJpaRepositories("spring.jpa.demo.repository")
@ComponentScans(value = {@ComponentScan("spring.jpa.demo.repository"), @ComponentScan("spring.jpa.demo.service")})
public class ApplicationContextConfig
{
    @Autowired
    private Environment env;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(getDataSource());
        Properties jpaProperties = new Properties();
        //Specifies the action that is invoked to the database when the Hibernate SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        //Configures the naming strategy that is used when Hibernate creates new database objects and schema elements
        jpaProperties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("hibernate.ejb.naming_strategy"));
        //If the value of this property is true, Hibernate writes all SQL statements to the console.
        jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        //If the value of this property is true, Hibernate will format the SQL that is written to the console.
        jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("spring.jpa.demo.model");
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager()
    {
        JpaTransactionManager transaction = new JpaTransactionManager();
        transaction.setEntityManagerFactory(entityManagerFactory().getObject());
        return transaction;
    }

    @Bean
    public DataSource getDataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("mysql.driver"));
        dataSource.setUrl(env.getProperty("mysql.jdbcUrl"));
        dataSource.setUsername(env.getProperty("mysql.username"));
        dataSource.setPassword(env.getProperty("mysql.password"));
        return dataSource;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver()
    {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine()
    {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource()
    {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("/i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver()
    {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ENGLISH);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor()
    {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
}
