package com.ken.bookstore.bean.request.cart;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ken
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReduceCartItemQtyReq {


    @NotNull
    @Max(value = Long.MAX_VALUE)
    @Min(value = 1)
    @Schema(description = "user id", requiredMode = Schema.RequiredMode.REQUIRED, defaultValue = "1")
    private Long userId;

    @NotNull
    @Max(value = Long.MAX_VALUE)
    @Min(value = 1)
    @Schema(description = "book id", requiredMode = Schema.RequiredMode.REQUIRED, defaultValue = "1")
    private Long bookId;

    @NotNull
    @Max(value = Long.MAX_VALUE)
    @Schema(description = "book quantity", requiredMode = Schema.RequiredMode.REQUIRED, defaultValue = "1")
    private Long quantity;
}
