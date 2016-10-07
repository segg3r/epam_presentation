package by.segg3r.epam.presentation.springinternals.spring.postprocessors;

import java.lang.reflect.Method;
import java.util.List;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import static java.util.stream.Collectors.*;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.core.type.MethodMetadata;

import com.google.common.collect.Lists;

public class LookupFixBFPP implements BeanPostProcessor,
		BeanFactoryPostProcessor {

	private ConfigurableListableBeanFactory beanFactory;
	private List<MethodOverride> methodOverrides;

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;

		try {
			populateClassNamesUnderJavaConfigBeanDefinitions(beanFactory);
			this.methodOverrides = findLookupOverrides(beanFactory);
		} catch (Exception e) {
			throw new FatalBeanException("Could not fix lookup.", e);
		}
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		try {
			List<MethodOverride> beanMethodOverrides = methodOverrides.stream()
					.filter(methodOverride -> {
						return beanName.equals(methodOverride.beanName);
					}).collect(toList());
			if (beanMethodOverrides.isEmpty())
				return bean;
			return createProxy(bean, beanMethodOverrides);
		} catch (Exception e) {
			throw new FatalBeanException("Could not fix lookup.", e);
		}
	}

	private Object createProxy(Object bean,
			List<MethodOverride> beanMethodOverrides) throws Exception {
		ProxyFactory factory = new ProxyFactory();
		factory.setSuperclass(bean.getClass());
		Class<?> clazz = factory.createClass();
		MethodHandler handler = new MethodHandler() {
			@Override
			public Object invoke(Object self, Method overridden,
					Method forwarder, Object[] args) throws Throwable {
				for (MethodOverride methodOverride : beanMethodOverrides) {
					if (methodOverride.methodSignature
							.equals(getMethodSignature(overridden))) {
						return beanFactory.getBean(methodOverride.resultBeanName);
					}
				}
				return forwarder.invoke(self, args);
			}
		};
		Object instance = clazz.newInstance();
		((ProxyObject) instance).setHandler(handler);
		return instance;
	}

	private void populateClassNamesUnderJavaConfigBeanDefinitions(
			ConfigurableListableBeanFactory beanFactory) throws Exception {
		for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
			BeanDefinition beanDefinition = beanFactory
					.getBeanDefinition(beanDefinitionName);

			if (isCreatedByJavaConfig(beanDefinition)) {
				Class<?> beanType = getJavaConfigBeanType(beanDefinition);
				((AbstractBeanDefinition) beanDefinition)
						.setBeanClass(beanType);
			}
		}
	}

	private List<MethodOverride> findLookupOverrides(
			ConfigurableListableBeanFactory beanFactory) throws Exception {
		List<MethodOverride> result = Lists.newArrayList();

		for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
			BeanDefinition beanDefinition = beanFactory
					.getBeanDefinition(beanDefinitionName);

			if (isCreatedByJavaConfig(beanDefinition)) {
				Class<?> beanType = getJavaConfigBeanType(beanDefinition);

				Method[] methods = beanType.getDeclaredMethods();
				for (Method method : methods) {
					if (method.isAnnotationPresent(Lookup.class)) {
						Class<?> type = method.getReturnType();

						String[] beanNames = beanFactory
								.getBeanNamesForType(type);
						if (beanNames == null || beanNames.length != 1) {
							throw new FatalBeanException(
									"There should be only one bean for resolving @Lookup.");
						}

						result.add(new MethodOverride(beanDefinitionName,
								getMethodSignature(method), beanNames[0]));
					}
				}
			}
		}

		return result;
	}

	private boolean isCreatedByJavaConfig(BeanDefinition beanDefinition)
			throws Exception {
		return getJavaConfigBeanDefinitionClass().isAssignableFrom(
				beanDefinition.getClass());
	}

	private Class<?> getJavaConfigBeanType(BeanDefinition beanDefinition)
			throws Exception {
		MethodMetadata methodMetadata = MethodMetadata.class.cast(PropertyUtils
				.getProperty(beanDefinition, "factoryMethodMetadata"));
		Class<?> beanClass = Class.forName(methodMetadata.getReturnTypeName());
		return beanClass;
	}

	private Class<?> getJavaConfigBeanDefinitionClass() throws Exception {
		return Class
		.forName("org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader$ConfigurationClassBeanDefinition");
	}
	
	private static String getMethodSignature(Method method) {
		StringBuilder result = new StringBuilder();
		result.append(method.getReturnType().getCanonicalName());
		result.append(" ");
		result.append(method.getName());
		for (Class<?> parameterType : method.getParameterTypes()) {
			result.append(" ");
			result.append(parameterType.getCanonicalName());
		}
		return result.toString();
	}

	private static final class MethodOverride {
		private String beanName;
		private String methodSignature;
		private String resultBeanName;

		public MethodOverride(String beanName, String methodSignature,
				String resultBeanName) {
			super();
			this.beanName = beanName;
			this.methodSignature = methodSignature;
			this.resultBeanName = resultBeanName;
		}
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

}
