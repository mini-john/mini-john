package com.niddah.initializer;

import com.niddah.configuration.AppConfigCore;
import com.niddah.configuration.AppConfigMobile;
import com.niddah.configuration.AppConfigWeb;
import com.niddah.configuration.HibernateConfiguration;
import com.niddah.configuration.SecurityConfiguration;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfigCore.class, AppConfigMobile.class, AppConfigWeb.class, HibernateConfiguration.class,SecurityConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void registerDispatcherServlet(ServletContext servletContext) {
        super.registerDispatcherServlet(servletContext);
        FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter",
                new CharacterEncodingFilter());
        fr.setInitParameter("encoding", "UTF-8");
        fr.setInitParameter("forceEncoding", "true");
        fr.addMappingForUrlPatterns(null, true, "/*");
        servletContext.addListener(new RequestContextListener());
    }

    

}
