package org.meruvian.resteasy.plugins.spring;

import javax.servlet.ServletContext;

import org.jboss.resteasy.plugins.spring.SpringContextLoaderSupport;
import org.springframework.web.context.ConfigurableWebApplicationContext;

public class SpringContextLoaderListener extends org.jboss.resteasy.plugins.spring.SpringContextLoaderListener {
	 
	 private SpringContextLoaderSupport springContextLoaderSupport = new SpringContextLoaderSupport();
	 
	 @Override
	 protected void customizeContext(ServletContext servletContext, ConfigurableWebApplicationContext configurableWebApplicationContext) {
	 
	   super.customizeContext(servletContext, configurableWebApplicationContext);
	   this.springContextLoaderSupport.customizeContext(servletContext,
	   configurableWebApplicationContext);
	 }
}
