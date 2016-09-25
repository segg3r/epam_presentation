package by.segg3r.epam.presentation.autowiring.spring;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.DependencyDescriptor;

import com.google.common.collect.Lists;

public interface AutowiringInterceptor {

	Set<Class<? extends Annotation>> getSupportedAnnotations();

	Optional<Object> processBean(Optional<Object> autowiringBean,
			ListableBeanFactory delegate, DependencyDescriptor descriptor, String beanName,
			Set<String> autowiredBeanNames, TypeConverter typeConverter);

	default int priority() {
		return 0;
	};
	
	default boolean isSupportingField(Field field) {
		List<Annotation> fieldAnnotations = Lists.newArrayList(field.getAnnotations());
		Set<Class<? extends Annotation>> supportedAnnotations = getSupportedAnnotations();
		
		for (Annotation fieldAnnotation : fieldAnnotations) {
			for (Class<? extends Annotation> supportedAnnotationClass : supportedAnnotations) {
				if (supportedAnnotationClass.isAssignableFrom(fieldAnnotation.getClass())) {
					return true;
				}
			}
		}
		return false;
	};

}
