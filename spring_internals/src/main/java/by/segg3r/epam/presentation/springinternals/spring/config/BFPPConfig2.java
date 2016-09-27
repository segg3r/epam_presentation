package by.segg3r.epam.presentation.springinternals.spring.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import by.segg3r.epam.presentation.springinternals.beans.ExtensionDeployer;
import by.segg3r.epam.presentation.springinternals.beans.Service;
import by.segg3r.epam.presentation.springinternals.beans.ServiceImpl;
import by.segg3r.epam.presentation.springinternals.spring.postprocessors.ExtensionBFPP;

@Configuration
public class BFPPConfig2 {

	@Bean
	public static BeanFactoryPostProcessor helloWorldBFPP() {
		return new ExtensionBFPP();
	}

	@Bean
	public ExtensionDeployer extensionDeployer() {
		return new ExtensionDeployer();
	}

	@Bean
	public Service printService() {
		return new ServiceImpl();
	}

}
