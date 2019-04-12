package com.car.commons.exception;

import com.car.commons.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class CustomerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    Object handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(Result.ERRO_CODE);
        logger.error("url {}, msg {}",request.getRequestURL(), e.getMessage());
        return Result.error("操作失败");
    }
}
