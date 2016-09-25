package by.segg3r.epam.presentation.autowiring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import by.segg3r.epam.presentation.autowiring.beans.Service;
import by.segg3r.epam.presentation.autowiring.config.MockAutowiringInterceptorConfig;

public class MockAutowiringInterceptorRunner {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(
				MockAutowiringInterceptorConfig.class);

		Service service = ctx.getBean(Service.class);
		System.out.println(service.dao);
	}

}
