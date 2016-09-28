package by.segg3r.epam.presentation.scopes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import by.segg3r.epam.presentation.scopes.config.ScopeWrapperConfig;
import by.segg3r.epam.presentation.scopes.entity.Logger;
import by.segg3r.epam.presentation.scopes.entity.Service;
import by.segg3r.epam.presentation.scopes.scopes.ScopeWrapper;


public class CustomScopeRunner2 {

	@SuppressWarnings({ "resource",  })
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				ScopeWrapperConfig.class);
		
		Logger logger = ctx.getBean(Logger.class);
		System.out.println(logger);
		Service service = ctx.getBean(Service.class);
		System.out.println(service);

		ScopeWrapper scopeWrapper = ctx.getBean(ScopeWrapper.class);
		scopeWrapper.clear();
		
		logger = ctx.getBean(Logger.class);
		System.out.println(logger);
		service = ctx.getBean(Service.class);
		System.out.println(service);
	}

}
