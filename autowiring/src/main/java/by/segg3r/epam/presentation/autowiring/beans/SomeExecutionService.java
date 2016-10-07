package by.segg3r.epam.presentation.autowiring.beans;

import java.util.concurrent.ExecutorService;

import by.segg3r.epam.presentation.autowiring.beans.annotations.Executor;
import by.segg3r.epam.presentation.autowiring.beans.annotations.ThreadScoped;

public class SomeExecutionService {

	@ThreadScoped @Executor(threads = 5)
	public ExecutorService executorService;
	
}
