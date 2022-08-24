package com.example.onlinebookstore.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Component
@Order(1)
public class PermissionCheck {
    // 定义一个切面，括号内写入第1步中自定义注解的路径
    @Pointcut("@annotation(com.example.onlinebookstore.common.PermissionAnnotation)")
    private void permissionCheck() {
    }

    @Autowired
    Response response;

    @Around("permissionCheck()")
    public Object permissionCheckFirst(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpSession session = (HttpSession) RequestContextHolder.currentRequestAttributes().resolveReference(RequestAttributes.REFERENCE_SESSION);
        //已登录
        if(session.getAttribute("username")!=null)
        {
            return joinPoint.proceed();
        }
        return response.toJSON(401,"未授权访问");

    }
}
