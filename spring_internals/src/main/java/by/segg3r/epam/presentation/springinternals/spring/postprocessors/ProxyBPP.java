package by.segg3r.epam.presentation.springinternals.spring.postprocessors;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import by.segg3r.epam.presentation.springinternals.beans.Service;

public class ProxyBPP implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("pre : " + beanName + " is " + bean.getClass());
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("Post : " + beanName + " is " + bean.getClass());
		
		if (Service.class.isAssignableFrom(bean.getClass())) {
			return (Service) Proxy.newProxyInstance(
					Service.class.getClassLoader(),
					new Class[] { Service.class }, new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method,
								Object[] args) throws Throwable {
							System.out.println("Calling method " + method.getName());
							return method.invoke(bean, args);
						}
					});
		}

		return bean;
	}
	
}
