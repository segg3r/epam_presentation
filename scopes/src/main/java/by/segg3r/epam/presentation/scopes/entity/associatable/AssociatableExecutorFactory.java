package by.segg3r.epam.presentation.scopes.entity.associatable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import by.segg3r.epam.presentation.scopes.scopes.AssociatableThreadScope;

public class AssociatableExecutorFactory {

	private AssociatableThreadScope scope;

	public AssociatableExecutorFactory(AssociatableThreadScope scope) {
		this.scope = scope;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public ExecutorService wrap(ExecutorService executor) {
		return (ExecutorService) Proxy.newProxyInstance(
				ExecutorService.class.getClassLoader(),
				new Class[] { ExecutorService.class }, new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						if (args != null) {
							for (int i = 0; i < args.length; i++) {
								Object arg = args[i];
								
								if (List.class.isAssignableFrom(arg.getClass())) {
									args[i] = ((List) arg).stream().map((listItem) -> {
										return tryToWrapSingleObject(listItem);
									})
									.collect(Collectors.toList());
								} else {
									args[i] = tryToWrapSingleObject(arg);
								}
							}
						}
						
						return method.invoke(executor, args);
					}

					private Object tryToWrapSingleObject(Object arg) {
						if (Callable.class.isAssignableFrom(arg.getClass())) {
							return wrapCallable((Callable) arg);
						} else if (Runnable.class.isAssignableFrom(arg.getClass())) {
							return wrapRunnable((Runnable) arg);
						} else {
							return arg;
						}
					}

					private Callable<?> wrapCallable(Callable<?> callable) {
						Map<String, Object> callingThreadContext = scope.getCurrentThreadContext();
						
						return () -> {
							scope.replaceContext(callingThreadContext);
							
							try {
								return callable.call();
							} finally {
								scope.removeContext();
							}
						};
					}
					
					private Runnable wrapRunnable(Runnable runnable) {
						Map<String, Object> callingThreadContext = scope.getCurrentThreadContext();
						
						return () -> {
							scope.replaceContext(callingThreadContext);
							
							try {
								runnable.run();
							} finally {
								scope.removeContext();
							}
						};
					}
					
				});

	}
	
}
