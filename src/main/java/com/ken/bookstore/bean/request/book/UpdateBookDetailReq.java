package com.ken.bookstore.bean.request.book;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

/**
 * @author ken
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookDetailReq {

    @NotNull
    @Max(value = Long.MAX_VALUE)
    @Min(value = 1)
    @Schema(description = "book id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @NotNull
    @Max(value = Long.MAX_VALUE)
    @Min(value = 1)
    @Schema(description = "book category id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long categoryId;

    @NotBlank(message = "book name is required")
    @Length(max = 255, message = "The length of book name the limit")
    @Schema(description = "book name", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255)
    private String name;

    @NotBlank(message = "book author is required")
    @Length(max = 255, message = "The length of book author the limit")
    @Schema(description = "book author", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255)
    private String author;

    @NotNull(message = "book price is required")
    @DecimalMin(value = "0", message = "book price cannot less than 0")
    @Digits(integer = 8, fraction = 2, message = "The integer digit cannot be greater than 8, and the decimal place can only be 2 decimal places")
    @Schema(description = "book price", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal price;

}
