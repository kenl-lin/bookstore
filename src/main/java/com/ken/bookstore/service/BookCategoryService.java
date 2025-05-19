package com.ken.bookstore.service;

import com.ken.bookstore.bean.request.category.CreateBookCategoryReq;
import com.ken.bookstore.bean.request.category.QueryBookCategoryReq;
import com.ken.bookstore.bean.request.category.UpdateBookCategoryReq;
import com.ken.bookstore.bean.response.category.BookCategoryResp;

import java.util.List;

/**
 * @author ken
 */
public interface BookCategoryService {
    BookCategoryResp create(CreateBookCategoryReq request);
    BookCategoryResp update(UpdateBookCategoryReq request);
    List<BookCategoryResp> list(QueryBookCategoryReq queryBookCategoryReq);
}
