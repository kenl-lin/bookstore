package com.ken.bookstore.common;

import com.ken.bookstore.bean.response.OperationResult;
import com.ken.bookstore.common.exception.CustomerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ken
 */
@Slf4j
@RestControllerAdvice
@Order(0)
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerException.class)
    public OperationResult<Object> customerExceptionHandler(CustomerException code) {
        return OperationResult.fail(code.getCode());
    }
}
