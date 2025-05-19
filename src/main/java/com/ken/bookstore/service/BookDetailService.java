package com.ken.bookstore.service;

import com.ken.bookstore.bean.request.book.CreateBookDetailReq;
import com.ken.bookstore.bean.request.book.QueryBookDetailReq;
import com.ken.bookstore.bean.request.book.UpdateBookDetailReq;
import com.ken.bookstore.bean.response.book.BookDetailResp;
import jakarta.validation.Valid;

import java.util.List;

/**
 * @author ken
 */
public interface BookDetailService {
    BookDetailResp create(@Valid CreateBookDetailReq request);
    List<BookDetailResp> list(@Valid QueryBookDetailReq queryBookDetailReq);
    BookDetailResp update(@Valid UpdateBookDetailReq request);
}
