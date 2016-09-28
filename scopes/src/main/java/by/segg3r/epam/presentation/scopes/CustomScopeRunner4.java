package by.segg3r.epam.presentation.scopes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import by.segg3r.epam.presentation.scopes.config.SimpleThreadScopeConfig;
import by.segg3r.epam.presentation.scopes.entity.Logger;

public class CustomScopeRunner4 {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SimpleThreadScopeConfig.class);
		
		Runnable r = () -> {
			Logger logger = ctx.getBean(Logger.class);
			logger.addEvent("Hello, i'm thread " + Thread.currentThread().getId());
		};
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		executor.execute(r);
		executor.shutdown();
		executor.awaitTermination(10, TimeUnit.SECONDS);
		
		System.out.println(ctx.getBean(Logger.class).getEvents());
	}

}
