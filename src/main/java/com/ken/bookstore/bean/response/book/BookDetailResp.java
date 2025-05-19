package com.ken.bookstore.bean.response.book;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ken
 */
@Data
public class BookDetailResp {
    private Long bookId;
    private Long categoryId;
    private String name;
    private String author;
    private BigDecimal price;
}
