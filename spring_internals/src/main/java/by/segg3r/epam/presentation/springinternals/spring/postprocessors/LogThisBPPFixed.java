package by.segg3r.epam.presentation.springinternals.spring.postprocessors;

import java.lang.reflect.Method;
import java.util.Map;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.google.common.collect.Maps;

import by.segg3r.epam.presentation.springinternals.beans.annotationprocessing.LogThisMethod;

public class LogThisBPPFixed implements BeanPostProcessor {
	private Map<String, Object> beansForLogging = Maps.newHashMap();
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		for (Method method : bean.getClass().getDeclaredMethods()) {
			if (method.isAnnotationPresent(LogThisMethod.class)) {
				beansForLogging.put(beanName, bean);
			}
		}
		return bean;
	}
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		try {
			Object originalBean = beansForLogging.get(beanName);
			if (originalBean == null)
				return bean;
			ProxyFactory factory = new ProxyFactory();
			factory.setSuperclass(originalBean.getClass());
			Class<?> clazz = factory.createClass();

			MethodHandler handler = new MethodHandler() {
				@Override
				public Object invoke(Object self, Method originalBeanMethod,
						Method proxyMethod, Object[] args) throws Throwable {
					if (originalBeanMethod.isAnnotationPresent(LogThisMethod.class)) {
						System.out.println("Logging method");
					}
					Method beanMethod = bean.getClass()
							.getMethod(originalBeanMethod.getName(),
									originalBeanMethod.getParameterTypes());
					return beanMethod.invoke(bean, args);
				}
			};
			Object instance = clazz.newInstance();
			((ProxyObject) instance).setHandler(handler);
			return instance;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return bean;
		}
	}
}
