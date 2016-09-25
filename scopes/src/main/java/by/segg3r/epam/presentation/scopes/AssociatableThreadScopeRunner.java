package by.segg3r.epam.presentation.scopes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import by.segg3r.epam.presentation.scopes.config.AssociatableThreadScopeConfig;
import by.segg3r.epam.presentation.scopes.entity.Logger;

public class AssociatableThreadScopeRunner {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				AssociatableThreadScopeConfig.class);
		
		Runnable r = () -> {
			Logger logger = ctx.getBean(Logger.class);
			logger.addEvent("Hello, i'm thread " + Thread.currentThread().getId());
			
			System.out.println(logger.getEvents());
		};
		
		new Thread(r).start();
		// but what if we are using thread pool???
	}

}
