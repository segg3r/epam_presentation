package by.segg3r.epam.presentation.scopes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import by.segg3r.epam.presentation.scopes.config.CustomScopeConfig2;
import by.segg3r.epam.presentation.scopes.entity.Logger;

public class CustomScopeRunner11 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				CustomScopeConfig2.class);
		
		Logger logger = ctx.getBean(Logger.class);
		System.out.println(logger);
		System.out.println(logger.getClass());
	}

}
