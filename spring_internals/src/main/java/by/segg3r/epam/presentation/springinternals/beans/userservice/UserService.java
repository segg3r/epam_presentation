package by.segg3r.epam.presentation.springinternals.beans.userservice;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class UserService {
	
	public void doSomething(String email) {
		UserAction action = getUserAction(email);
		System.out.println("Doing something with user " + action.email + " by action" + action);
	}
	
	private UserAction getUserAction(String email) {
		UserAction result = buildUserAction();
		result.email = email;
		return result;
	}
	
	@Lookup
	public UserAction buildUserAction() { return null; }
	
}
