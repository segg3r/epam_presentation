package by.segg3r.epam.presentation.springinternals.spring.config;


import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import by.segg3r.epam.presentation.springinternals.beans.Service;
import by.segg3r.epam.presentation.springinternals.beans.ServiceImpl;
import by.segg3r.epam.presentation.springinternals.spring.postprocessors.HelloWorldBPP;
import by.segg3r.epam.presentation.springinternals.spring.postprocessors.ProxyBPP;

@Configuration
public class BPPConfig2 {

	@Bean
	public BeanPostProcessor proxyBPP() {
		return new ProxyBPP();
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
