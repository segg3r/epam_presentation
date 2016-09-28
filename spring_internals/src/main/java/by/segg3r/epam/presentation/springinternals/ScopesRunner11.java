package by.segg3r.epam.presentation.springinternals;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import by.segg3r.epam.presentation.springinternals.beans.FirstService;
import by.segg3r.epam.presentation.springinternals.beans.SecondService;
import by.segg3r.epam.presentation.springinternals.spring.config.ScopesConfig11;

public class ScopesRunner11 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				ScopesConfig11.class);
		
		FirstService firstService = ctx.getBean(FirstService.class);
		SecondService secondService = ctx.getBean(SecondService.class);
		
		System.out.println("First service");
		System.out.println(firstService.singleton);
		System.out.println(firstService.prototype);
		
		System.out.println("Second service");
		System.out.println(secondService.singleton);
		System.out.println(secondService.prototype);
	}

}
