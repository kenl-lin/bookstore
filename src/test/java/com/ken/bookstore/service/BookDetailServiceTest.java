package com.ken.bookstore.service;

import com.ken.bookstore.bean.enums.OperationResultCode;
import com.ken.bookstore.bean.request.book.CreateBookDetailReq;
import com.ken.bookstore.bean.request.book.QueryBookDetailReq;
import com.ken.bookstore.bean.request.book.UpdateBookDetailReq;
import com.ken.bookstore.bean.response.book.BookDetailResp;
import com.ken.bookstore.common.exception.CustomerException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ken
 */
@SpringBootTest
@Sql(scripts = {"/ddl/init.sql", "/dml/init.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(2)
public class BookDetailServiceTest {

    @Autowired
    private BookDetailService bookDetailService;

    @Test
    @Order(1)
    public void testCreatedSuccess() {
        CreateBookDetailReq createBookDetailReq = new CreateBookDetailReq();
        createBookDetailReq.setCategoryId(1L);
        createBookDetailReq.setName("Java2");
        createBookDetailReq.setAuthor("ken");
        createBookDetailReq.setPrice(new BigDecimal("99.99"));
        BookDetailResp bookDetailResp = bookDetailService.create(createBookDetailReq);
        Assertions.assertNotNull(bookDetailResp);
    }

    @Test
    public void testCreatedFail() {
        CreateBookDetailReq createBookDetailReq = new CreateBookDetailReq();
        createBookDetailReq.setCategoryId(11L);
        createBookDetailReq.setName("Java");
        createBookDetailReq.setAuthor("ken");
        createBookDetailReq.setPrice(new BigDecimal("99.99"));
        CustomerException exception = Assertions.assertThrows(
                CustomerException.class,
                () -> bookDetailService.create(createBookDetailReq)
        );
        Assertions.assertEquals(OperationResultCode.NOT_EXIST_CATEGORY.getCode(), exception.getCode().getCode());
    }

    @Test
    public void testUpdateSuccess() {
        UpdateBookDetailReq updateBookDetailReq = new UpdateBookDetailReq();
        updateBookDetailReq.setId(1L);
        updateBookDetailReq.setCategoryId(1L);
        updateBookDetailReq.setName("Java2");
        updateBookDetailReq.setAuthor("ken");
        updateBookDetailReq.setPrice(new BigDecimal("99.99"));
        BookDetailResp bookDetailResp = bookDetailService.update(updateBookDetailReq);
        Assertions.assertNotNull(bookDetailResp);
    }

    @Test
    public void testUpdateFail() {
        UpdateBookDetailReq updateBookDetailReq = new UpdateBookDetailReq();
        updateBookDetailReq.setId(110L);
        updateBookDetailReq.setCategoryId(1L);
        updateBookDetailReq.setName("Java2");
        updateBookDetailReq.setAuthor("ken");
        updateBookDetailReq.setPrice(new BigDecimal("99.99"));
        CustomerException exception = Assertions.assertThrows(
                CustomerException.class,
                () -> bookDetailService.update(updateBookDetailReq)
        );
        Assertions.assertEquals(OperationResultCode.NOT_EXIST_BOOK_ID.getCode(), exception.getCode().getCode());
    }

    @Test
    public void testListSuccess() {
        QueryBookDetailReq queryBookDetailReq = new QueryBookDetailReq();
        queryBookDetailReq.setCategoryId(1L);
        List<BookDetailResp> books = bookDetailService.list(queryBookDetailReq);
        Assertions.assertNotEquals(0, books);
    }
}
