package william.eshop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author zhangshenao
 * @Date 2019-11-25
 * @Description 打印Service方法日志的切面
 */
@Aspect
@Component
@Slf4j
public class ServiceLogAspect {
    @Around("execution(* william.eshop.service.impl..*.*(..))")
    public Object logExecutionTimeCosts(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("====== 开始执行 {}.{} ======", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());

        //记录开始时间
        long begin = System.currentTimeMillis();

        //执行目标方法
        Object result = joinPoint.proceed();

        //记录结束时间
        long end = System.currentTimeMillis();
        long costs = end - begin;

        log.error("====== 执行结束，耗时：{} 毫秒 ======", costs);
        return result;
    }
}
