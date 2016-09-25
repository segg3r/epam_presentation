package by.segg3r.epam.presentation.autowiring.spring.interceptors;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import by.segg3r.epam.presentation.autowiring.annotations.CustomScoped;
import by.segg3r.epam.presentation.autowiring.spring.AutowiringInterceptor;

import com.google.common.collect.Sets;

@Component
public class CustomScopeAutowiringInterceptor implements AutowiringInterceptor,
		BeanDefinitionRegistryPostProcessor {

	private BeanDefinitionRegistry registry;

	@SuppressWarnings("unchecked")
	@Override
	public Set<Class<? extends Annotation>> getSupportedAnnotations() {
		return Sets.newHashSet(CustomScoped.class);
	}

	@Override
	public Optional<Object> processBean(Optional<Object> autowiringBean,
			ListableBeanFactory delegate, DependencyDescriptor descriptor,
			String beanName, Set<String> autowiredBeanNames,
			TypeConverter typeConverter) {
		String candidateName = BeanFactoryUtils
				.beanNamesForTypeIncludingAncestors(delegate, descriptor
						.getField().getType(), true, descriptor.isEager())[0];

		if ("custom".equals(registry.getBeanDefinition(candidateName)
				.getScope())) {
			return autowiringBean;
		}
		throw new RuntimeException("Could not autowire bean " + candidateName
				+ " under @CustomScoped annotated field.");
	}

	@Override
	public void postProcessBeanDefinitionRegistry(
			BeanDefinitionRegistry registry) throws BeansException {
		this.registry = registry;
	}

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		return;
	}

}
