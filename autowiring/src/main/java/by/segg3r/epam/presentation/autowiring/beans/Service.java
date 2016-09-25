package by.segg3r.epam.presentation.autowiring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service {

	@Autowired
	public MockedDAO dao;
	
}
