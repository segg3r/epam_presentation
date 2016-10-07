package by.segg3r.epam.presentation.springinternals.spring.postprocessors;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import by.segg3r.epam.presentation.springinternals.beans.annotationprocessing.LogThisMethod;

public class LogThisBPP implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		try {
			for (Method method : bean.getClass().getDeclaredMethods()) {
				if (method.isAnnotationPresent(LogThisMethod.class)) {
					ProxyFactory factory = new ProxyFactory();
					factory.setSuperclass(bean.getClass());
					Class<?> clazz = factory.createClass();
					MethodHandler handler = new MethodHandler() {
						@Override
						public Object invoke(Object self, Method overridden,
								Method forwarder, Object[] args)
								throws Throwable {
							if (overridden
									.isAnnotationPresent(LogThisMethod.class)) {
								System.out.println("Logging method");
							}

							Method beanMethod = bean.getClass().getMethod(
									overridden.getName(),
									overridden.getParameterTypes());
							return beanMethod.invoke(bean, args);
						}
					};
					Object instance = clazz.newInstance();
					((ProxyObject) instance).setHandler(handler);
					return instance;
				}
			}
			return bean;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return bean;
		}
	}
}
