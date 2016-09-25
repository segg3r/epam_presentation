package by.segg3r.epam.presentation.scopes.config;

import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import by.segg3r.epam.presentation.scopes.entity.Logger;
import by.segg3r.epam.presentation.scopes.scopes.CustomScope;

import com.google.common.collect.Maps;

@Configuration
public class CustomScopeConfig {

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
	
	@Bean
	@Scope("custom")
	public Logger logger() {
		return new Logger();
	}
	
}
