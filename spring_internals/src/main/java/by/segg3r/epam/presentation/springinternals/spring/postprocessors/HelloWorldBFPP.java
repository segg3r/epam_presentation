package by.segg3r.epam.presentation.springinternals.spring.postprocessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class HelloWorldBFPP implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
			throws BeansException {
		for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
			System.out.println("BFPP : " + beanDefinitionName);
		}
	}

}
