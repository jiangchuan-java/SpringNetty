package com.ifeng.fhh.fhhService.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**}
 *
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-6
 */
//@Aspect
//@Component
public class ForAOPTest {

    @Around("execution(* com.ifeng.fhh.fhhService.web.action.AccountInfoFromEsAction.route(com.ifeng.fhh.zmt.web.route.ActionRequest))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("123");
        return pjp.proceed();
    }
}
