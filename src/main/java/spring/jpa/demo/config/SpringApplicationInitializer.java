package spring.jpa.demo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author ngoc on 24/03/2018
 * @project spring-data-jpa
 */

public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
    // Load database and spring security configuration
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class[]{ApplicationContextConfig.class};
    }

    // Load spring web configuration
    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return new Class[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings()
    {
        return new String[]{"/"};
    }
}
