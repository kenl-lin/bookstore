package com.ken.bookstore.service;

import com.ken.bookstore.bean.enums.OperationResultCode;
import com.ken.bookstore.bean.request.category.CreateBookCategoryReq;
import com.ken.bookstore.bean.request.category.QueryBookCategoryReq;
import com.ken.bookstore.bean.request.category.UpdateBookCategoryReq;
import com.ken.bookstore.bean.response.category.BookCategoryResp;
import com.ken.bookstore.common.exception.CustomerException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/**
 * @author ken
 */
@SpringBootTest
@Sql(scripts = {"/ddl/init.sql", "/dml/init.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(1)
public class BookCategoryServiceTest {

    @Autowired
    private BookCategoryService bookCategoryService;

    @Test
    @Order(1)
    public void testCreateSuccess() {
        BookCategoryResp bookCategoryResp = bookCategoryService.create(new CreateBookCategoryReq("Java1"+System.currentTimeMillis()));
        Assertions.assertNotNull(bookCategoryResp);
    }

    @Test
    public void testCreateFail() {
        CustomerException exception = Assertions.assertThrows(
                CustomerException.class,
                () -> bookCategoryService.create(new CreateBookCategoryReq("Java"))
        );
        Assertions.assertEquals(OperationResultCode.EXIST_CATEGORY_NAME.getCode(), exception.getCode().getCode());
    }

    @Test
    public void testUpdateSuccess() {
        BookCategoryResp bookCategoryResp = bookCategoryService.update(new UpdateBookCategoryReq(1L, "Java"));
        Assertions.assertNotNull(bookCategoryResp);
    }

    @Test
    public void testUpdateFail() {
        CustomerException exception = Assertions.assertThrows(
                CustomerException.class,
                () -> bookCategoryService.update(new UpdateBookCategoryReq(100L, "Java11"))
        );
        Assertions.assertEquals(OperationResultCode.NOT_EXIST_CATEGORY.getCode(), exception.getCode().getCode());
    }

    @Test
    public void testListSuccess() {
        List<BookCategoryResp> bookCategoryResps = bookCategoryService.list(new QueryBookCategoryReq("Java"));
        Assertions.assertNotEquals(0, bookCategoryResps.size());
    }
}
