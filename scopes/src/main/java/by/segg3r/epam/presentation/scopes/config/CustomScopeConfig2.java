package by.segg3r.epam.presentation.scopes.config;

import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import by.segg3r.epam.presentation.scopes.entity.Logger;
import by.segg3r.epam.presentation.scopes.scopes.CustomScope;

import com.google.common.collect.Maps;

@Configuration
public class CustomScopeConfig2 {

	@Bean
	public CustomScope customScope() {
		return new CustomScope();
	}
	
	@Bean
	public CustomScopeConfigurer customScopeConfigurer(CustomScope customScope) {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		
		Map<String, Object> scopes = Maps.newHashMap();
		scopes.put("custom", customScope);
		configurer.setScopes(scopes);

		return configurer;
	}
	
	@Bean
	@Scope(value = "custom", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Logger logger() {
		return new Logger();
	}
	
}
