package com.gangbb.chapter04.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

/**
 * @author : Gangbb
 * @ClassName : SecurityExceptionHandler
 * @Description :
 * @Date : 2021/3/4 16:24
 */
@ControllerAdvice
public class SecurityExceptionHandler implements SecurityAdviceTrait {}
