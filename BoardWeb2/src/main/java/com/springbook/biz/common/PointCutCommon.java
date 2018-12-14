package com.springbook.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCutCommon {
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut(){}	//�ش� �Լ��� xml���� ID�� ���� �� 
	@Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")
	public void getPointcut(){}	//�ش� �Լ��� xml���� ID�� ���� �� 
}