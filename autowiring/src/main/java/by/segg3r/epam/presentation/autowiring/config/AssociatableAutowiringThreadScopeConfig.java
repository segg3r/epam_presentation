package by.segg3r.epam.presentation.autowiring.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import by.segg3r.epam.presentation.autowiring.beans.SomeExecutionService;
import by.segg3r.epam.presentation.autowiring.spring.AutowiringInterceptor;
import by.segg3r.epam.presentation.autowiring.spring.InterceptedAnnotationBeanPostProcessor;
import by.segg3r.epam.presentation.autowiring.spring.interceptors.ThreadScopedExecutorAutowiringInterceptor;
import by.segg3r.epam.presentation.scopes.entity.associatable.AssociatableExecutorFactory;
import by.segg3r.epam.presentation.scopes.scopes.AssociatableThreadScope;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Configuration
public class AssociatableAutowiringThreadScopeConfig {

	@Bean(name = "org.springframework.context.annotation.internalAutowiredAnnotationProcessor")
	public InterceptedAnnotationBeanPostProcessor interceptedAnnotationBeanPostProcessor(
			ThreadScopedExecutorAutowiringInterceptor interceptor) {
		List<AutowiringInterceptor> autowiringInterceptor = Lists
				.newArrayList(interceptor);
		return new InterceptedAnnotationBeanPostProcessor(autowiringInterceptor);
	}
	
	@Bean
	public SomeExecutionService someExecutionService() {
		return new SomeExecutionService();
	}
	
	@Bean
	public AssociatableThreadScope associatableThreadScope() {
		return new AssociatableThreadScope();
	}
	@Bean
	public AssociatableExecutorFactory associatableExecutorFactory(
			AssociatableThreadScope scope) {
		return new AssociatableExecutorFactory(scope);
	}
	@Bean
	public CustomScopeConfigurer simpleThreadScopeConfigurer(
			AssociatableThreadScope scope) {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();

		Map<String, Object> scopes = Maps.newHashMap();
		scopes.put("thread", scope);
		configurer.setScopes(scopes);

		return configurer;
	}
	@Bean
	public ThreadScopedExecutorAutowiringInterceptor interceptor(
			AssociatableExecutorFactory associatableExecutorFactory) {
		return new ThreadScopedExecutorAutowiringInterceptor(associatableExecutorFactory);
	}

}
