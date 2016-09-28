package by.segg3r.epam.presentation.springinternals;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.segg3r.epam.presentation.springinternals.beans.FactoryBean;
import by.segg3r.epam.presentation.springinternals.beans.FirstService;
import by.segg3r.epam.presentation.springinternals.beans.SecondService;

public class ScopesRunner10 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config-10.xml");
		
		FirstService firstService = ctx.getBean(FirstService.class);
		SecondService secondService = ctx.getBean(SecondService.class);
		FactoryBean factoryBean = ctx.getBean(FactoryBean.class);
		
		System.out.println("First service");
		System.out.println(firstService.singleton);
		System.out.println(firstService.prototype);
		
		System.out.println("Second service");
		System.out.println(secondService.singleton);
		System.out.println(secondService.prototype);
	
		System.out.println("Factory bean");
		System.out.println(factoryBean);
	}

}
