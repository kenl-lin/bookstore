package com.ken.bookstore.bean.request.category;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class QueryBookCategoryReq {

    @Length(max = 255, message = "The length of category name the limit")
    @Schema(description = "category name", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 255)
    private String categoryName;
}
