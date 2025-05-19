package com.ken.bookstore.bean.request.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @author ken
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookCategoryReq {

    @NotNull(message = "category id is required")
    @Max(value = Long.MAX_VALUE)
    @Min(value = 1)
    @Schema(description = "category id", requiredMode = Schema.RequiredMode.REQUIRED, minimum = "1", defaultValue = "1")
    private Long categoryId;

    @NotBlank(message = "category name is required")
    @Length(max = 255, message = "The length of category name the limit")
    @Schema(description = "category name", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255)
    private String categoryName;
}
