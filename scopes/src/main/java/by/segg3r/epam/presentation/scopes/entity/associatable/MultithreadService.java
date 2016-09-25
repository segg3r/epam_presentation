package by.segg3r.epam.presentation.scopes.entity.associatable;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MultithreadService {

	@Autowired
	private AssociatableThreadScopeExecutorFactory factory;
	
	public void executeMultipleTimes(Runnable runnable) {
		Executor executor = Executors.
	}
	
}
