package com.huike.base.tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextTools implements ApplicationContextAware {
	private static final Logger log = LoggerFactory.getLogger(ApplicationContextTools.class);

	private ApplicationContext applicationContext;

	static ApplicationContext staticApplicationContext;

	/**
	 * SPRING AUTO INVOKE
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		if (this.applicationContext == null) {
			this.applicationContext = applicationContext;
			if (staticApplicationContext == null) {
				staticApplicationContext = this.applicationContext;
			}
		}
	}

	/**
	 * TOMCAT startup invoke
	 * 
	 * @param applicationContext
	 */
	public static void initApplicationContext(
			ApplicationContext applicationContext) {

		staticApplicationContext = applicationContext;
	}

	/**
	 * GET VALUE METHOD
	 * 
	 * @param beanName
	 * @return
	 */
	public Object getBean(String beanName) {
		Object obj = this.applicationContext.getBean(beanName);
		if (obj == null) {
			throw new java.lang.IllegalArgumentException(
					" The bean's name of  " + beanName
							+ " can not be found in spring container!");
		}
		return obj;
	}

	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	 * 
	 * @param name
	 * @return boolean
	 */
	public static boolean containsBean(String name) {

		return staticApplicationContext.containsBean(name);
	}

	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 * 
	 * @param name
	 * @return boolean
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isSingleton(String name) throws Exception {

		return staticApplicationContext.isSingleton(name);
	}

	/***
	 * 类似于getBean(String name)只是在参数中提供了需要返回到的类型。
	 * 
	 * @param name
	 * @param requiredType
	 * @return
	 * @throws BeansException
	 */
	public static <T> T getBean(String name, Class<T> requiredType)
			throws BeansException {
		return staticApplicationContext.getBean(name, requiredType);
	}

	/**
	 * 
	 * @return
	 */
	public static ApplicationContext getStaticApplicationContext() {

		return staticApplicationContext;
	}

}
