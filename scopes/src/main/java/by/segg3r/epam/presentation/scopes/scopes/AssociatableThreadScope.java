package by.segg3r.epam.presentation.scopes.scopes;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

public class AssociatableThreadScope implements Scope {

	private final ThreadLocal<Map<String, Object>> threadScope = new NamedThreadLocal<Map<String, Object>>(
			"AssociatableSimpleThreadScope") {
		@Override
		protected Map<String, Object> initialValue() {
			return new HashMap<String, Object>();
		}
	};

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		Map<String, Object> scope = this.threadScope.get();
		Object object = scope.get(name);
		if (object == null) {
			object = objectFactory.getObject();
			scope.put(name, object);
		}
		return object;
	}

	public void replaceContext(Map<String, Object> context) {
		this.threadScope.set(context);
	}

	public void removeContext() {
		this.threadScope.remove();
	}
	
	public Map<String, Object> getCurrentThreadContext() {
		return this.threadScope.get();
	}

	@Override
	public Object remove(String name) {
		Map<String, Object> scope = this.threadScope.get();
		return scope.remove(name);
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		// do nothing;
	}

	@Override
	public Object resolveContextualObject(String key) {
		return null;
	}

	@Override
	public String getConversationId() {
		return Thread.currentThread().getName();
	}

}
