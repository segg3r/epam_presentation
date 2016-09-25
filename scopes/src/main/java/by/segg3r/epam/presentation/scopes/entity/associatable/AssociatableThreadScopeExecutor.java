package by.segg3r.epam.presentation.scopes.entity.associatable;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import by.segg3r.epam.presentation.scopes.scopes.AssociatableThreadScope;

public class AssociatableThreadScopeExecutor implements Executor {

	private Executor delegateExecutor;
	private AssociatableThreadScope associatableScope;
	
	public AssociatableThreadScopeExecutor(AssociatableThreadScope scope, ExecutorService delegateExecutor) {
		this.associatableScope = scope;
		this.delegateExecutor = delegateExecutor;
	}
	
	@Override
	public void execute(Runnable runnable) {
		Map<String, Object> callingThreadContext = associatableScope.getCurrentThreadContext();
		
		Runnable wrapper = () -> {
			associatableScope.replaceContext(callingThreadContext);
			
			try {
				runnable.run();
			} finally {
				associatableScope.removeContext();
			}
		};
		
		delegateExecutor.execute(wrapper);
	}

}
