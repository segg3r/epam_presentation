package by.segg3r.epam.presentation.springinternals;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import by.segg3r.epam.presentation.springinternals.beans.annotationprocessing.DashboardService;
import by.segg3r.epam.presentation.springinternals.spring.config.BPPConfig3;

public class BPPRunner3 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				BPPConfig3.class);
		
		DashboardService service = ctx.getBean(DashboardService.class);
		service.saveDashboard();
	}

}
