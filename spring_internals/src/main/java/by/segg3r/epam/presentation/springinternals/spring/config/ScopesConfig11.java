package by.segg3r.epam.presentation.springinternals.spring.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import by.segg3r.epam.presentation.springinternals.beans.FirstService;
import by.segg3r.epam.presentation.springinternals.beans.Prototype;
import by.segg3r.epam.presentation.springinternals.beans.SecondService;
import by.segg3r.epam.presentation.springinternals.beans.Singleton;

@Configuration
public class ScopesConfig11 {

	@Bean
	public FirstService firstService() {
		return new FirstService();
	}
	
	@Bean
	public SecondService secondService() {
		return new SecondService();
	}
	
	@Bean
	public Singleton singleton() {
		return new Singleton();
	}
	
	@Bean
	@Scope(BeanDefinition.SCOPE_PROTOTYPE)
	public Prototype prototype() {
		return new Prototype();
	}
	
}
