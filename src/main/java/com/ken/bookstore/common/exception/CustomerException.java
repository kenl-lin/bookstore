package com.ken.bookstore.common.exception;

import com.ken.bookstore.bean.enums.OperationResultCode;
import lombok.Getter;


/**
 * @author ken
 */
@Getter
public class CustomerException extends RuntimeException {

    /**
     * error code
     */
    private final OperationResultCode code;

    public CustomerException(OperationResultCode code) {
        super(code.getMessage());
        this.code = code;
    }
}
