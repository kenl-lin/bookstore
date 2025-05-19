package com.ken.bookstore.service;

import com.ken.bookstore.bean.enums.OperationResultCode;
import com.ken.bookstore.bean.request.cart.AddCartItemReq;
import com.ken.bookstore.bean.request.cart.ReduceCartItemQtyReq;
import com.ken.bookstore.bean.request.cart.RemoveCartItemReq;
import com.ken.bookstore.bean.response.cart.BookCartItemResp;
import com.ken.bookstore.bean.response.cart.BookCartItemTotalResp;
import com.ken.bookstore.common.exception.CustomerException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.Ordered;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/**
 * @author ken
 */
@SpringBootTest
@Sql(scripts = {"/ddl/init.sql", "/dml/init.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(3)
public class BookCartServiceTest {
    @Autowired
    private BookCartService bookCartService;

    @Test
    @Order(1)
    public void testAddCartItemSuccess() {
        AddCartItemReq addCartItemReq = new AddCartItemReq();
        addCartItemReq.setUserId(1L);
        addCartItemReq.setBookId(1L);
        addCartItemReq.setQuantity(1L);
        Assertions.assertEquals(true, bookCartService.addCartItem(addCartItemReq));
    }

    @Test
    public void testAddCartItemFail() {
        AddCartItemReq addCartItemReq = new AddCartItemReq();
        addCartItemReq.setUserId(1L);
        addCartItemReq.setBookId(100L);
        addCartItemReq.setQuantity(1L);
        CustomerException exception = Assertions.assertThrows(
                CustomerException.class,
                () -> bookCartService.addCartItem(addCartItemReq)
        );
        Assertions.assertEquals(OperationResultCode.NOT_EXIST_BOOK_ID.getCode(), exception.getCode().getCode());
    }

    @Test
    public void testReduceCartItemSuccess() {
        bookCartService.reduceCartItem(new ReduceCartItemQtyReq(1L, 1L, 1L));
    }

    @Test
    public void testReduceCartItemReduceQuantityFail() {
        CustomerException exception = Assertions.assertThrows(
                CustomerException.class,
                () -> bookCartService.reduceCartItem(new ReduceCartItemQtyReq(1L, 1L, -100L))
        );
        Assertions.assertEquals(OperationResultCode.REDUCE_QUANTITY_ERROR.getCode(), exception.getCode().getCode());
    }

    @Test
    public void testListSuccess() {
        List<BookCartItemResp> list = bookCartService.list(1L);
        Assertions.assertNotEquals(0, list.size());
    }

    @Test
    public void testItemsDetailSuccess() {
        BookCartItemTotalResp bookCartItemTotalResp = bookCartService.itemsDetail(1L);
        Assertions.assertNotNull(bookCartItemTotalResp);
    }

    @Test
    @Order(Ordered.LOWEST_PRECEDENCE)
    public void testRemoveCartItemSuccess() {
        Assertions.assertTrue(bookCartService.removeCartItem(new RemoveCartItemReq(1L, 1L)));
    }

    @Test
    @Order(Ordered.LOWEST_PRECEDENCE)
    public void testRemoveCartItemFail() {
        CustomerException exception = Assertions.assertThrows(
                CustomerException.class,
                () -> bookCartService.removeCartItem(new RemoveCartItemReq(11L, 1L))
        );
        Assertions.assertEquals(OperationResultCode.NOT_EXIST_BOOK_ID.getCode(), exception.getCode().getCode());
    }
}
