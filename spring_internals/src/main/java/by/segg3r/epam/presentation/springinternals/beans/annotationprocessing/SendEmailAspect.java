package by.segg3r.epam.presentation.springinternals.beans.annotationprocessing;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SendEmailAspect {

	@Before("@annotation(SendEmail)")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("Sending email");
	}

}
