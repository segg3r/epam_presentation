package by.segg3r.epam.presentation.autowiring.beans;

import org.springframework.stereotype.Component;

import by.segg3r.epam.presentation.autowiring.annotations.CustomScoped;

@Component
public class ServiceTwo {

	@CustomScoped
	public CustomScopedDAO dao;
	
}
