package by.segg3r.epam.presentation.autowiring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import by.segg3r.epam.presentation.autowiring.beans.ServiceTwo;
import by.segg3r.epam.presentation.autowiring.config.CustomScopeAutowiringInterceptorConfig;

public class CustomScopeAutowiringInterceptorRunner {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(
				CustomScopeAutowiringInterceptorConfig.class);

		ServiceTwo service = ctx.getBean(ServiceTwo.class);
		System.out.println(service.dao);
	}

}
