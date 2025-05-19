package com.ken.bookstore.bean.response.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ken
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCartItemTotalResp {

    private List<BookCartItemResp> items;

    private BigDecimal totalPrice;

}
