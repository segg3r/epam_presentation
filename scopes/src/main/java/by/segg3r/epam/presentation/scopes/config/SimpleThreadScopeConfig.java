package by.segg3r.epam.presentation.scopes.config;

import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.SimpleThreadScope;

import by.segg3r.epam.presentation.scopes.entity.Logger;

import com.google.common.collect.Maps;

@Configuration
public class SimpleThreadScopeConfig {

	@Bean
	public SimpleThreadScope simpleThreadScope() {
		return new SimpleThreadScope();
	}
	
	@Bean
	public CustomScopeConfigurer simpleThreadScopeConfigurer(SimpleThreadScope simpleThreadScope) {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		
		Map<String, Object> scopes = Maps.newHashMap();
		scopes.put("thread", simpleThreadScope);
		configurer.setScopes(scopes);

		return configurer;
	}
	
	@Bean
	@Scope("thread")
	public Logger logger() {
		return new Logger();
	}
	
}
