package by.segg3r.epam.presentation.springinternals;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import by.segg3r.epam.presentation.springinternals.beans.ExtensionDeployer;
import by.segg3r.epam.presentation.springinternals.spring.config.BFPPConfig2;

public class BFPPRunner2 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				BFPPConfig2.class);
		
		ExtensionDeployer extensionDeployer = ctx.getBean(ExtensionDeployer.class);
		extensionDeployer.deployExtensions();
	}

}
