package by.segg3r.epam.presentation.springinternals.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import by.segg3r.epam.presentation.springinternals.beans.annotationprocessing.DashboardService;
import by.segg3r.epam.presentation.springinternals.spring.postprocessors.LogThisBPP;

@Configuration
public class BPPConfig3 {

	@Bean
	public LogThisBPP logThisBPP() {
		return new LogThisBPP();
	}

	@Bean
	public DashboardService dashboardService() {
		return new DashboardService();
	}

}
