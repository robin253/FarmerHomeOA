package com.huike.base.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.huike.app.system.service.impl.SysConfigService;
import com.huike.base.tools.CacheHelp;

@Component
public class StartApplicationListener implements ApplicationListener<ContextRefreshedEvent>{

	protected Logger log = LoggerFactory.getLogger(StartApplicationListener.class);
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		log.info("加载系统配置...");
		
		SysConfigService sysConfigService = event.getApplicationContext().getBean(SysConfigService.class);
		
		CacheHelp.SYS_CONFIG_MAP = sysConfigService.selectKeyValueByGroupCode(null);
		
		log.info("系统配置加载完成...");
	}

}
