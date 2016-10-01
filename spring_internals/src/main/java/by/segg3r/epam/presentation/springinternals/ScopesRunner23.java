package by.segg3r.epam.presentation.springinternals;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import by.segg3r.epam.presentation.springinternals.beans.userservice.UserService;
import by.segg3r.epam.presentation.springinternals.spring.config.ScopesConfig23;

public class ScopesRunner23 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				ScopesConfig23.class);
		
		UserService userService = ctx.getBean(UserService.class);
		
		userService.doSomething("admin@admin.com");
		userService.doSomething("user@user.com");
	}

}
