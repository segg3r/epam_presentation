package by.segg3r.epam.presentation.springinternals.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import by.segg3r.epam.presentation.springinternals.beans.annotationprocessing.DashboardService;
import by.segg3r.epam.presentation.springinternals.beans.annotationprocessing.SendEmailAspect;
import by.segg3r.epam.presentation.springinternals.spring.postprocessors.LogThisBPPFixed;

@Configuration
@EnableAspectJAutoProxy
public class BPPConfig5 {

	@Bean
	public SendEmailAspect sendEmailAspect() {
		return new SendEmailAspect();
	}

	@Bean
	public LogThisBPPFixed logThisBPP() {
		return new LogThisBPPFixed();
	}

	@Bean
	public DashboardService dashboardService() {
		return new DashboardService();
	}

}
