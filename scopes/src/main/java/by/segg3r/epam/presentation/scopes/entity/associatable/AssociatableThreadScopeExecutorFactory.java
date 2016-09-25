package by.segg3r.epam.presentation.scopes.entity.associatable;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.segg3r.epam.presentation.scopes.scopes.AssociatableThreadScope;

@Component
public class AssociatableThreadScopeExecutorFactory {

	@Autowired
	private AssociatableThreadScope scope;
	
	public AssociatableThreadScopeExecutor create(Executor executor) {
		return new AssociatableThreadScopeExecutor(scope, executor);
	}
	
}
