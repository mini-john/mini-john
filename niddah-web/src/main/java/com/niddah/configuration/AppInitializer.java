package com.niddah.configuration;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//public class AppInitializer implements WebApplicationInitializer {
//
//	public void onStartup(ServletContext container) throws ServletException {
//		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//		ctx.register(AppConfigWeb.class);
//		ctx.setServletContext(container);
//
//		ServletRegistration.Dynamic servlet = container.addServlet(
//				"dispatcher", new TilesDispatchServlet());
//
//		servlet.setLoadOnStartup(1);
//		servlet.addMapping("/*");
//                
//	}
//
//}
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfigWeb.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

//    @Override
//    protected void registerContextLoaderListener(ServletContext servletContext) {
//        
//        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
//
//        encodingFilter.setForceEncoding(true);
//
//        encodingFilter.setEncoding("UTF - 8");
//        servletContext.addFilter("encoding", encodingFilter);//To change body of generated methods, choose Tools | Templates.
//        super.registerContextLoaderListener(servletContext);
//        
//    }
     
}
