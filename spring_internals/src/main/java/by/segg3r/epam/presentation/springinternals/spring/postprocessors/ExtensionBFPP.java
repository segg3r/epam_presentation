package by.segg3r.epam.presentation.springinternals.spring.postprocessors;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import by.segg3r.epam.presentation.springinternals.beans.Extension;

import com.google.common.collect.Lists;

public class ExtensionBFPP implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
			throws BeansException {
		List<Class<? extends Extension>> extensionClasses = parseClasspath();
		for (Class<? extends Extension> extensionClass : extensionClasses) {
			try {
				beanFactory.registerSingleton(extensionClass.getName(), extensionClass.newInstance());
			} catch (Exception e) {
				System.out.println("Failed to register extension " + extensionClass.getName());
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<Class<? extends Extension>> parseClasspath() {
		return Lists.newArrayList(SimpleExtension.class);
	}

	public static class SimpleExtension extends Extension {
	}
	
}
