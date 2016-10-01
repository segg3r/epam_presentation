package by.segg3r.epam.presentation.springinternals;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.segg3r.epam.presentation.springinternals.beans.userservice.UserService;

public class ScopesRunner20 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config-20.xml");
		
		UserService userService = ctx.getBean(UserService.class);
		
		userService.doSomething("admin@admin.com");
		userService.doSomething("user@user.com");
		
		System.out.println(userService.getClass());
	}

}
