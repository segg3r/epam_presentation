package by.segg3r.epam.presentation.scopes.scopes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import com.google.common.collect.Lists;

public class ScopeWrapper implements BeanDefinitionRegistryPostProcessor {

	private Scope scope;
	private String scopeName;
	
	private List<String> scopeBeanDefinitions;
	
	public ScopeWrapper(Scope scope, String scopeName) {
		this.scope = scope;
		this.scopeName = scopeName;
	}

	@Override
	public void postProcessBeanDefinitionRegistry(
			BeanDefinitionRegistry registry) throws BeansException {
		this.scopeBeanDefinitions = Lists.newArrayList(registry.getBeanDefinitionNames()).stream()
			.filter(beanDefinitionName -> {
				BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);
				return scopeName.equals(beanDefinition.getScope());
			})
			.collect(Collectors.toList());
	}
	
	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// do nothing
		return;
	}

	public void clear() {
		for (String beanDefinitionName : scopeBeanDefinitions) {
			scope.remove(beanDefinitionName);
		}
	}
	
}