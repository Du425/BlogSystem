package com.du.blog.exception;

import com.du.blog.response.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //Shiro异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public CommonResult handler(ShiroException e){
        log.error("Shiro运行时异常-----------------",e);
        return CommonResult.failed(e.getMessage(),null);
        //return CommonResult.failed(401,e.getMessage(),null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public CommonResult handler(RuntimeException e){
        log.error("运行时异常-----------------",e);
        return CommonResult.failed(e.getMessage());
    }

}
