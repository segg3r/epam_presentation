package by.segg3r.epam.presentation.scopes.config;

import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import by.segg3r.epam.presentation.scopes.entity.Logger;
import by.segg3r.epam.presentation.scopes.entity.Service;
import by.segg3r.epam.presentation.scopes.scopes.CustomScope;
import by.segg3r.epam.presentation.scopes.scopes.ScopeWrapper;

import com.google.common.collect.Maps;

@Configuration
public class ScopeWrapperConfig {
	@Bean
	public CustomScope customScope() {
		return new CustomScope();
	}
	@Bean
	public ScopeWrapper customScopeWrapper(CustomScope customScope) {
		return new ScopeWrapper(customScope, "custom");
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
	@Scope(value = "custom", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Logger logger() {
		return new Logger();
	}
	@Bean
	@Scope(value = "custom", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Service service() {
		return new Service();
	}
}
