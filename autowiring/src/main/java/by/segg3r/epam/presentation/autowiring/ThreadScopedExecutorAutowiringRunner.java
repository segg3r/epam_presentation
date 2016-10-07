package by.segg3r.epam.presentation.autowiring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import by.segg3r.epam.presentation.autowiring.beans.SomeExecutionService;
import by.segg3r.epam.presentation.autowiring.config.AssociatableAutowiringThreadScopeConfig;

public class ThreadScopedExecutorAutowiringRunner {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(
				AssociatableAutowiringThreadScopeConfig.class);

		SomeExecutionService service = ctx.getBean(SomeExecutionService.class);
		System.out.println(service.executorService);
		System.out.println(service.executorService.getClass());
	}

}
