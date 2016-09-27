package by.segg3r.epam.presentation.springinternals.spring.config;


import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import by.segg3r.epam.presentation.springinternals.beans.Service;
import by.segg3r.epam.presentation.springinternals.beans.ServiceImpl;
import by.segg3r.epam.presentation.springinternals.spring.postprocessors.HelloWorldBFPP;
import by.segg3r.epam.presentation.springinternals.spring.postprocessors.HelloWorldBPP;

@Configuration
public class BFPPConfig1 {
	
	@Bean
	public static BeanFactoryPostProcessor helloWorldBFPP() {
		return new HelloWorldBFPP();
	}
	
	@Bean
	public BeanPostProcessor helloWorldBPP() {
		return new HelloWorldBPP();
	}
	
	@Bean
	public Service printService() {
		return new ServiceImpl();
	}
	
}
