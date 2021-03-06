package com.playtomic.tests.wallet.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	
	private Logger log = LoggerFactory.getLogger(LogAspect.class);
	
	@Before("execution(* com.playtomic.tests.wallet.repository.*.save(..)) && args(entity)")
	public void myadvice(JoinPoint jp, Object entity) {
		log.info("Saving {}", entity.toString());
	}

}
