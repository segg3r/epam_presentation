package by.segg3r.epam.presentation.scopes.config;

import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import by.segg3r.epam.presentation.scopes.entity.Logger;
import by.segg3r.epam.presentation.scopes.entity.associatable.AssociatableExecutorFactory;
import by.segg3r.epam.presentation.scopes.scopes.AssociatableThreadScope;

import com.google.common.collect.Maps;

@Configuration
public class AssociatableThreadScopeConfig {

	@Bean
	public AssociatableThreadScope associatableThreadScope() {
		return new AssociatableThreadScope();
	}
	
	@Bean
	public AssociatableExecutorFactory associatableExecutorFactory(AssociatableThreadScope scope) {
		return new AssociatableExecutorFactory(scope);
	}
	
	@Bean
	public CustomScopeConfigurer simpleThreadScopeConfigurer(AssociatableThreadScope scope) {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		
		Map<String, Object> scopes = Maps.newHashMap();
		scopes.put("thread", scope);
		configurer.setScopes(scopes);

		return configurer;
	}
	
	@Bean
	@Scope(value = "thread", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Logger logger() {
		return new Logger();
	}
	
}
