package by.segg3r.epam.presentation.autowiring.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import by.segg3r.epam.presentation.autowiring.spring.AutowiringInterceptor;
import by.segg3r.epam.presentation.autowiring.spring.InterceptedAnnotationBeanPostProcessor;
import by.segg3r.epam.presentation.autowiring.spring.interceptors.MockingAutowiringInterceptor;

import com.google.common.collect.Lists;

@Configuration
@ComponentScan("by.segg3r.epam.presentation.autowiring.beans")
public class MockAutowiringInterceptorConfig {

	@Bean(name = "org.springframework.context.annotation.internalAutowiredAnnotationProcessor")
	public InterceptedAnnotationBeanPostProcessor interceptedAnnotationBeanPostProcessor() {
		List<AutowiringInterceptor> autowiringInterceptor = Lists.newArrayList(
				new MockingAutowiringInterceptor()
				);
		return new InterceptedAnnotationBeanPostProcessor(autowiringInterceptor);
	}
	
}
