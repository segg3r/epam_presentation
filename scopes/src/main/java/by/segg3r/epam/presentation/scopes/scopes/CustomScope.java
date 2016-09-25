package by.segg3r.epam.presentation.scopes.scopes;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class CustomScope implements Scope {

	private Map<String, Object> storage = new ConcurrentHashMap<String, Object>();
	
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		Object object = storage.get(name);
		if (object == null) {
			object = objectFactory.getObject();
			storage.put(name, object);
		}
		
		return object;
	}

	@Override
	public Object remove(String name) {
		return storage.remove(name);
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		// do nothing
		return;
	}

	@Override
	public Object resolveContextualObject(String key) {
		// do nothing
		return null;
	}

	@Override
	public String getConversationId() {
		return null;
	}

}
