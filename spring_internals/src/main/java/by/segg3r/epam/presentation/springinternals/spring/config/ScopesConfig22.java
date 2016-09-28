package by.segg3r.epam.presentation.springinternals.spring.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import by.segg3r.epam.presentation.springinternals.beans.userservice.UserAction;

@Configuration
@ComponentScan("by.segg3r.epam.presentation.springinternals.beans.userservice")
public class ScopesConfig22 {

	@Bean
	@Scope(BeanDefinition.SCOPE_PROTOTYPE)
	public UserAction userAction() {
		return new UserAction();
	}
	
}
