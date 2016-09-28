package by.segg3r.epam.presentation.scopes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import by.segg3r.epam.presentation.scopes.config.CustomScopeConfig;
import by.segg3r.epam.presentation.scopes.entity.Logger;
import by.segg3r.epam.presentation.scopes.scopes.CustomScope;

public class CustomScopeRunner1 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				CustomScopeConfig.class);
		
		Logger logger = ctx.getBean(Logger.class);
		System.out.println(logger);
	
		CustomScope customScope = ctx.getBean(CustomScope.class);
		customScope.remove("logger");
		
		logger = ctx.getBean(Logger.class);
		System.out.println(logger);
	}

}
