package com.ken.bookstore.bean.response;

import com.ken.bookstore.bean.enums.OperationResultCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ken
 */
@Data
public class OperationResult<T> {

    @Schema(description = "operation resul code")
    private String code;

    @Schema(description = "Description of the response")
    private String message;

    @Schema(description = "The data returned when the request is successful")
    private T data;

    public static <T> OperationResult<T> success(T data) {
        OperationResult<T> result = new OperationResult<>();
        result.setData(data);
        result.setCode(OperationResultCode.SUCCESS.getCode());
        result.setMessage(OperationResultCode.SUCCESS.getMessage());
        return result;
    }

    public static <T> OperationResult<T> fail(OperationResultCode code) {
        OperationResult<T> result = new OperationResult<>();
        result.setCode(code.getCode());
        result.setMessage(code.getMessage());
        return result;
    }

}
