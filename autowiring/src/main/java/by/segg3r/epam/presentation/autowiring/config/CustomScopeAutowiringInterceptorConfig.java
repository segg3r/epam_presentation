package by.segg3r.epam.presentation.autowiring.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import by.segg3r.epam.presentation.autowiring.spring.AutowiringInterceptor;
import by.segg3r.epam.presentation.autowiring.spring.InterceptedAnnotationBeanPostProcessor;
import by.segg3r.epam.presentation.autowiring.spring.interceptors.CustomScopeAutowiringInterceptor;
import by.segg3r.epam.presentation.autowiring.spring.interceptors.MockingAutowiringInterceptor;
import by.segg3r.epam.presentation.scopes.scopes.CustomScope;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Configuration
@ComponentScan("by.segg3r.epam.presentation.autowiring")
public class CustomScopeAutowiringInterceptorConfig {

	@Bean
	public CustomScope simpleThreadScope() {
		return new CustomScope();
	}
	
	@Bean
	public CustomScopeConfigurer simpleThreadScopeConfigurer(CustomScope customScope) {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		
		Map<String, Object> scopes = Maps.newHashMap();
		scopes.put("custom", customScope);
		configurer.setScopes(scopes);

		return configurer;
	}
	
	@Bean(name = "org.springframework.context.annotation.internalAutowiredAnnotationProcessor")
	public InterceptedAnnotationBeanPostProcessor interceptedAnnotationBeanPostProcessor(
			CustomScopeAutowiringInterceptor customScopeInterceptor) {
		List<AutowiringInterceptor> autowiringInterceptor = Lists.newArrayList(
				new MockingAutowiringInterceptor(),
				customScopeInterceptor
				);
		return new InterceptedAnnotationBeanPostProcessor(autowiringInterceptor);
	}
	
}
