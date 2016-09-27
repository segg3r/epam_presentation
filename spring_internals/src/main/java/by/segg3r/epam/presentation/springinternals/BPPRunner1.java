package by.segg3r.epam.presentation.springinternals;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import by.segg3r.epam.presentation.springinternals.beans.Service;
import by.segg3r.epam.presentation.springinternals.spring.config.BPPConfig1;

public class BPPRunner1 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				BPPConfig1.class);
		
		Service service = ctx.getBean(Service.class);
		service.printSomething();
	}

}
