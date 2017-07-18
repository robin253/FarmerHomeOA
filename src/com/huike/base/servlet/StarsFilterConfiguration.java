package com.huike.base.servlet;

import javax.servlet.Filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StarsFilterConfiguration {
	/* FilterRegistrationBean 用来配置urlpattern 来确定哪些路径触发filter */
	private static Log log = LogFactory.getLog(StarsFilterConfiguration.class);
	@Bean
	public FilterRegistrationBean someFilterRegistration() {
		log.info("StarsFilterConfiguration..........");
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(AuthFilter());
		registration.setEnabled(true); // 设置是否可用 
		registration.addUrlPatterns("/api/*/back/*"); // 需要进行过滤的url
//		registration.addUrlPatterns("/api/app/rest/*");
		registration.setOrder(1); // 设置过滤器的顺序，可选
		return registration;
	}

	/* 使用annotation tag来取代<bean></bean> */
	@Bean()
	public Filter AuthFilter() {
		return new PagesSecurityFilter();
	}
}
