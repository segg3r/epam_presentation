package by.segg3r.epam.presentation.autowiring.spring.interceptors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.DependencyDescriptor;

import by.segg3r.epam.presentation.autowiring.beans.annotations.Executor;
import by.segg3r.epam.presentation.autowiring.beans.annotations.ThreadScoped;
import by.segg3r.epam.presentation.autowiring.spring.AutowiringInterceptor;
import by.segg3r.epam.presentation.scopes.entity.associatable.AssociatableExecutorFactory;

import com.google.common.collect.Sets;

public class ThreadScopedExecutorAutowiringInterceptor implements AutowiringInterceptor {

	@Autowired
	private AssociatableExecutorFactory executorFactory;
	
	public ThreadScopedExecutorAutowiringInterceptor(
			AssociatableExecutorFactory associatableExecutorFactory) {
		this.executorFactory = associatableExecutorFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Class<? extends Annotation>> getSupportedAnnotations() {
		return Sets.newHashSet(ThreadScoped.class);
	}

	@Override
	public Optional<Object> processBean(Optional<Object> autowiringBean,
			ListableBeanFactory delegate, DependencyDescriptor descriptor,
			String beanName, Set<String> autowiredBeanNames,
			TypeConverter typeConverter) {
		Field autowiringField = descriptor.getField();
		if (autowiringField.isAnnotationPresent(Executor.class)
				&& ExecutorService.class.isAssignableFrom(autowiringField.getType())) {
			int threads = autowiringField.getAnnotation(Executor.class).threads();
			return Optional.of(executorFactory.wrap(Executors.newFixedThreadPool(threads)));
		}
		
		return Optional.empty();
	}

}
