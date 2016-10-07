package by.segg3r.epam.presentation.springinternals.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import by.segg3r.epam.presentation.springinternals.beans.annotationprocessing.DashboardService;
import by.segg3r.epam.presentation.springinternals.beans.annotationprocessing.SendEmailAspect;
import by.segg3r.epam.presentation.springinternals.spring.postprocessors.LogThisBPP;

@Configuration
@EnableAspectJAutoProxy
public class BPPConfig4 {

	@Bean
	public SendEmailAspect sendEmailAspect() {
		return new SendEmailAspect();
	}

	@Bean
	public LogThisBPP logThisBPP() {
		return new LogThisBPP();
	}

	@Bean
	public DashboardService dashboardService() {
		return new DashboardService();
	}

}
