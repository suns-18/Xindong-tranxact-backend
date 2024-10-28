package neu.xindong.xact.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LoggingAspect {

    @Before("execution(* neu.xindong.xact.controller..*(..))")
    public void logBeforeController(JoinPoint joinPoint) {
        log.info("Entering Controller method: {} with arguments: {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* neu.xindong.xact.controller..*(..))", returning = "result")
    public void logAfterReturningController(JoinPoint joinPoint, Object result) {
        log.info("Exiting Controller method: {} with result: {}", joinPoint.getSignature().toShortString(), result);
    }
}
