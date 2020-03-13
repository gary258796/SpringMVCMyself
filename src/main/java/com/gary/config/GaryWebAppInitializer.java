package com.gary.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// dispatcher servlet
public class GaryWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class } ;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class } ;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" } ;
    }

}
