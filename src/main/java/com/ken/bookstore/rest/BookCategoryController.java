package com.ken.bookstore.rest;

import com.ken.bookstore.bean.request.category.CreateBookCategoryReq;
import com.ken.bookstore.bean.request.category.QueryBookCategoryReq;
import com.ken.bookstore.bean.request.category.UpdateBookCategoryReq;
import com.ken.bookstore.bean.response.OperationResult;
import com.ken.bookstore.bean.response.category.BookCategoryResp;
import com.ken.bookstore.service.BookCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ken
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "API for Book Categories")
public class BookCategoryController {

    private final BookCategoryService bookCategoryService;

    @PostMapping("/admin/book/categories")
    @Operation(summary = "crate a book category data")
    public OperationResult<BookCategoryResp> create(@RequestBody @Valid CreateBookCategoryReq request) {
        BookCategoryResp bookCategoryResp = bookCategoryService.create(request);
        return OperationResult.success(bookCategoryResp);
    }

    @PutMapping("/admin/book/categories")
    @Operation(summary = "delete a book category data")
    public OperationResult<BookCategoryResp> update(@RequestBody @Valid UpdateBookCategoryReq request) {
        BookCategoryResp bookCategoryResp = bookCategoryService.update(request);
        return OperationResult.success(bookCategoryResp);
    }

    @PostMapping("/public/book/categories")
    @Operation(summary = "query all book category data")
    public OperationResult<List<BookCategoryResp>> list(@RequestBody @Valid QueryBookCategoryReq queryBookCategoryReq){
        return OperationResult.success(bookCategoryService.list(queryBookCategoryReq));
    }



}
