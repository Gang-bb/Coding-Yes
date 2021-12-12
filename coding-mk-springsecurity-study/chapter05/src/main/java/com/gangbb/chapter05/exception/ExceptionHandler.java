package com.gangbb.chapter05.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

/**
 * @author : Gangbb
 * @ClassName : ExceptionHandler
 * @Description :
 * @Date : 2021/3/4 16:24
 */
@ControllerAdvice
public class ExceptionHandler implements ProblemHandling {
    @Override
    public boolean isCausalChainsEnabled() {
        return true;
    }
}
