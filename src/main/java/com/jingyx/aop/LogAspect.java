package com.jingyx.aop;

import com.jingyx.annotation.SystemLogAnno;
import com.jingyx.entity.SystemLog;
import com.jingyx.enums.ReturnCodeEnum;
import com.jingyx.service.ISystemLogService;
import com.jingyx.utils.ReturnMsg;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;


/**
 * TODO
 * 日志切面类
 * @author Jingyx
 * @version 1.0
 * @date 2020/11/6 16:38
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

	@Autowired
	private ISystemLogService systemLogService;

	/**
	 * 切入点配置
	 */
	@Pointcut(value = "execution(* com.jingyx.controller.*.*(..))")
	public void pointCutOne(){}

	/**
	 * 前置通知
	 * @param joinPoint
	 */
//	@Before(value = "pointCutOne()")
	public void beforeSaveLog(JoinPoint joinPoint){
		System.out.println("在调用"+joinPoint.getSignature().getName()+"方法之前，打印。。。");
	}

	/**
	 * 后置通知
	 * @param joinPoint
	 */
//	@After(value = "pointCutOne()")
	public void afterReturningSaveLog(JoinPoint joinPoint){
		System.out.println("在调用"+joinPoint.getSignature().getName()+"方法之后，打印。。。");
	}

	/**
	 * 异常通知
	 * @param joinPoint
	 * @param e
	 */
//	@AfterThrowing(value = "pointCutOne()",throwing = "e")
	public void afterThrowingSaveLog(JoinPoint joinPoint,Exception e){
		System.out.println("在调用"+joinPoint.getSignature().getName()+"方法时出现了"+e.getClass().getName()+"异常，异常描述："+e.getMessage());
	}
	/**
	 * 最终通知
	 */
//	@AfterReturning(value = "pointCutOne()")
	public void afterSaveLog(JoinPoint joinPoint){
		System.out.println("在调用"+joinPoint.getSignature().getName()+"方法时，无论有没有异常都会打印。。。");
	}
	/**
	 * 环绕通知
	 */
	@Around(value = "pointCutOne()")
	public Object aroundSaveLog(ProceedingJoinPoint proceedingJoinPoint){
		SystemLog systemLog = new SystemLog();
		// 拦截的实体类，就是当前正在执行的controller
		Object target = proceedingJoinPoint.getTarget();
		// 拦截的方法名称。当前正在执行的方法
		String methodName = proceedingJoinPoint.getSignature().getName();
		// 拦截的放参数类型
		Signature sig = proceedingJoinPoint.getSignature();
		MethodSignature msig;
		if (!(sig instanceof MethodSignature)) {
			throw new IllegalArgumentException("该注解只能用于方法");
		}
		msig = (MethodSignature) sig;
		Class[] parameterTypes = msig.getMethod().getParameterTypes();
		// 获得被拦截的方法
		Method method = null;
		try {
			method = target.getClass().getMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException e1) {
			log.error("ControllerLogAopAspect around error",e1);
		} catch (SecurityException e1) {
			log.error("ControllerLogAopAspect around error",e1);
		}
		if (null != method) {
			if (method.isAnnotationPresent(SystemLogAnno.class)) {
				SystemLogAnno systemLogAnno = method.getAnnotation(SystemLogAnno.class);
				systemLog.setOperator("诸葛亮");
				systemLog.setOperAction(systemLogAnno.action());
				systemLog.setOperInfo("诸葛亮对" + systemLogAnno.module() + "进行了"
						+ systemLogAnno.action() + "操作");
			}
		}

		Object proceed = null;
		try {
			proceed =  proceedingJoinPoint.proceed();
			ReturnMsg returnMsg = (ReturnMsg) proceed;
			System.out.println(ReturnCodeEnum.OK.getCode() + "///" + returnMsg.getCode());
			if (ReturnCodeEnum.OK.getCode().equals(returnMsg.getCode())) {
				systemLog.setOperResut(ReturnCodeEnum.OK.getCodeMsg());
			}
			systemLog.setCreateTime(new Date());
			systemLogService.add(systemLog);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return proceed;
	}
}
