package by.segg3r.epam.presentation.springinternals.spring.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import by.segg3r.epam.presentation.springinternals.beans.userservice.UserAction;
import by.segg3r.epam.presentation.springinternals.beans.userservice.UserService;

@Configuration
public class ScopesConfig21 {

	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	@Scope(BeanDefinition.SCOPE_PROTOTYPE)
	public UserAction userAction() {
		return new UserAction();
	}
	
}
