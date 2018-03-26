package spring.jpa.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

/**
 * @author ngoc on 24/03/2018
 * @project spring-data-jpa
 */
@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages = {"spring.jpa.demo.controller"})
public class WebMvcConfig implements WebMvcConfigurer
{
    @Autowired
    private ApplicationContextConfig applicationContextConfig;

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry)
    {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(applicationContextConfig.templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        registry.viewResolver(resolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(applicationContextConfig.localeChangeInterceptor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/img/**", "/css/**", "/js/**")
                .addResourceLocations(
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
    }
}
