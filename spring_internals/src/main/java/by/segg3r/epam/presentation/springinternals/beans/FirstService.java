package by.segg3r.epam.presentation.springinternals.beans;

import org.springframework.beans.factory.annotation.Autowired;

public class FirstService {

	@Autowired
	public Singleton singleton;
	@Autowired
	public Prototype prototype;
	
}
