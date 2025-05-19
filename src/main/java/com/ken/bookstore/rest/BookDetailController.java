package com.ken.bookstore.rest;

import com.ken.bookstore.bean.request.book.CreateBookDetailReq;
import com.ken.bookstore.bean.request.book.QueryBookDetailReq;
import com.ken.bookstore.bean.request.book.UpdateBookDetailReq;
import com.ken.bookstore.bean.response.OperationResult;
import com.ken.bookstore.bean.response.book.BookDetailResp;
import com.ken.bookstore.service.BookDetailService;
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
@Tag(name = "API for Books")
public class BookDetailController {

    private final BookDetailService bookDetailService;

    @PostMapping("/admin/books")
    @Operation(summary = "crate a book detail data")
    public OperationResult<BookDetailResp> create(@RequestBody @Valid CreateBookDetailReq request) {
        BookDetailResp bookDetailResp = bookDetailService.create(request);
        return OperationResult.success(bookDetailResp);
    }

    @PostMapping("/public/books")
    @Operation(summary = "query all book detail data")
    public OperationResult<List<BookDetailResp>> list(@RequestBody @Valid QueryBookDetailReq queryBookDetailReq){
        return OperationResult.success(bookDetailService.list(queryBookDetailReq));
    }

    @PutMapping("/admin/books")
    @Operation(summary = "update a book detail data")
    public OperationResult<BookDetailResp> update(@RequestBody @Valid UpdateBookDetailReq request) {
        BookDetailResp bookDetailResp = bookDetailService.update(request);
        return OperationResult.success(bookDetailResp);
    }

}
