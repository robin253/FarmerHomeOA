package com.huike.boot;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:/spring/applicationContext.xml")
// @Configuration
@EnableAutoConfiguration
@ComponentScan("com.huike.mapper")
// @EnableEurekaServer
public class StartApplication extends SpringBootServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		log.info("服务开始启动了");
		super.onStartup(servletContext);

	}

	private static final Logger log = LoggerFactory.getLogger(StartApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(StartApplication.class);
	}

	public static void main(String[] args) {
		log.info("服务开始启动了");
		SpringApplication app = new SpringApplication(StartApplication.class);
		// app.addListeners(new StartApplicationListener());
		app.run(args);
	}
}
