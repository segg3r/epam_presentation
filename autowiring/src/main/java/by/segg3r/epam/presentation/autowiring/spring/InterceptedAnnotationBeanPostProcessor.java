package by.segg3r.epam.presentation.autowiring.spring;

import java.lang.annotation.Annotation;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeansException;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import com.google.common.collect.Lists;

public class InterceptedAnnotationBeanPostProcessor extends
		AutowiredAnnotationBeanPostProcessor {

	private List<AutowiringInterceptor> interceptors;

	public InterceptedAnnotationBeanPostProcessor(
			List<AutowiringInterceptor> interceptors) {
		super();
		this.interceptors = interceptors;
		
		Set<Class<? extends Annotation>> autowiredAnnotationTypes = interceptors.stream()
				.map(AutowiringInterceptor::getSupportedAnnotations)
				.flatMap(annotations -> annotations.stream())
				.collect(Collectors.toSet());
		autowiredAnnotationTypes.add(Autowired.class);
		this.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
		
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		BeanFactory mockProvidingFactory = new AutowiringInterceptedListableBeanFactory(
				interceptors, (ListableBeanFactory) beanFactory);
		super.setBeanFactory(mockProvidingFactory);
	}

	private static final class AutowiringInterceptedListableBeanFactory extends
			DefaultListableBeanFactory {

		private ListableBeanFactory delegate;
		private List<AutowiringInterceptor> prioritizedInterceptors;

		public AutowiringInterceptedListableBeanFactory(
				List<AutowiringInterceptor> interceptors, ListableBeanFactory delegate) {
			super(delegate);
			this.delegate = delegate;

			List<AutowiringInterceptor> prioritizedInterceptors = Lists
					.newArrayList(interceptors);
			prioritizedInterceptors.sort(Comparator
					.comparing(AutowiringInterceptor::priority));
			this.prioritizedInterceptors = prioritizedInterceptors;
		}

		@Override
		public Object resolveDependency(DependencyDescriptor descriptor,
				String beanName, Set<String> autowiredBeanNames,
				TypeConverter typeConverter) throws BeansException {
			Optional<Object> autowiringBean = resolveDelegatedDependency(
					descriptor, beanName, autowiredBeanNames, typeConverter);

			for (AutowiringInterceptor interceptor : prioritizedInterceptors) {
				if (interceptor.isSupportingField(descriptor.getField())) {
					autowiringBean = interceptor.processBean(autowiringBean,
							delegate, descriptor, beanName, autowiredBeanNames,
							typeConverter);
				}
			}
			
			if (autowiringBean.isPresent()) return autowiringBean.get();
			throw new NoSuchBeanDefinitionException("Could not autowire " + descriptor.getField().getType()
					+ " in " + beanName);
		}

		private Optional<Object> resolveDelegatedDependency(
				DependencyDescriptor descriptor, String beanName,
				Set<String> autowiredBeanNames, TypeConverter typeConverter) {
			try {
				return Optional.of(super.resolveDependency(descriptor,
						beanName, autowiredBeanNames, typeConverter));
			} catch (NoSuchBeanDefinitionException e) {
				return Optional.empty();
			}
		}

	}

}
