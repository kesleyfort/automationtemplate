package br.com.template.configuration;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class PageObjectBeanPostProcessor implements BeanPostProcessor {
		
	private List<Object> lsObjects = new LinkedList<>();
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		
		if (bean.getClass().isAnnotationPresent(PageObject.class)) {
			   lsObjects.add(bean);
		}
		
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}
	
	public List<Object> getPageObjects() {
		return lsObjects;
	}

}