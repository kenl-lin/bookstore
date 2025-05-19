package com.ken.bookstore.bean.response.cart;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ken
 */
@Data
public class BookCartItemResp {

    /**
     * book id
     */
    private Long bookId;

    /**
     * book name
     */
    private String name;

    /**
     * author name.
     */
    private String author;

    /**
     * price.
     */
    private BigDecimal price;

    /**
     * books quantity
     */
    private Long quantity;

    /**
     * books the total price
     */
    private BigDecimal totalPrice;

}
