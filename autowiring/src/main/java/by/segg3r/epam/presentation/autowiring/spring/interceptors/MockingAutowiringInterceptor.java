package by.segg3r.epam.presentation.autowiring.spring.interceptors;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.Set;

import org.mockito.Mockito;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.DependencyDescriptor;

import by.segg3r.epam.presentation.autowiring.spring.AutowiringInterceptor;

import com.google.common.collect.Sets;

public class MockingAutowiringInterceptor implements AutowiringInterceptor {
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<Class<? extends Annotation>> getSupportedAnnotations() {
		return Sets.newHashSet(Autowired.class);
	}

	@Override
	public Optional<Object> processBean(Optional<Object> autowiringBean,
			ListableBeanFactory delegate,
			DependencyDescriptor descriptor, String beanName,
			Set<String> autowiredBeanNames, TypeConverter typeConverter) {
		if (autowiringBean.isPresent()) return autowiringBean;
		
		Object mock = Mockito.mock(descriptor.getField().getType());
		return Optional.of(mock);
	}

}
