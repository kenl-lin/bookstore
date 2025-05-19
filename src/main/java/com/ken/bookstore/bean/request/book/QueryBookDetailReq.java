package com.ken.bookstore.bean.request.book;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class QueryBookDetailReq {

    @Max(value = Long.MAX_VALUE)
    @Min(value = 1)
    @Schema(description = "category id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, minimum = "1", defaultValue = "1")
    private Long categoryId;

    @Length(max = 255, message = "The length of name name the limit")
    @Schema(description = "book name", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 255)
    private String name;

    @Length(max = 255, message = "The length of book author the limit")
    @Schema(description = "book author", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255)
    private String author;
}
