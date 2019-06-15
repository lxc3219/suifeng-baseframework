package org.suifeng.baseframework.admin.syslog;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * 系统日志，切面处理类
 * @createTime 2019/6/2 10:57
 * @author luoxc
 */
@Aspect
@Component
public class SysLogAspect {
	
	@Pointcut("@annotation(org.suifeng.baseframework.admin.common.annotation.SysLog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		//保存日志
		// TODO
		return result;
	}

}
