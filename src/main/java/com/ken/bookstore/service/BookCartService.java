package com.ken.bookstore.service;

import com.ken.bookstore.bean.request.cart.AddCartItemReq;
import com.ken.bookstore.bean.request.cart.ReduceCartItemQtyReq;
import com.ken.bookstore.bean.request.cart.RemoveCartItemReq;
import com.ken.bookstore.bean.response.cart.BookCartItemResp;
import com.ken.bookstore.bean.response.cart.BookCartItemTotalResp;
import jakarta.validation.Valid;

import java.util.List;

/**
 * @author ken
 */
public interface BookCartService {

    Boolean removeCartItem(@Valid RemoveCartItemReq request);
    Boolean addCartItem(@Valid AddCartItemReq request);
    Boolean reduceCartItem(@Valid ReduceCartItemQtyReq request);
    List<BookCartItemResp> list(Long userId);
    BookCartItemTotalResp itemsDetail(Long userId);
}
