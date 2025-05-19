package com.ken.bookstore.rest;

import com.ken.bookstore.bean.request.cart.AddCartItemReq;
import com.ken.bookstore.bean.request.cart.ReduceCartItemQtyReq;
import com.ken.bookstore.bean.request.cart.RemoveCartItemReq;
import com.ken.bookstore.bean.response.OperationResult;
import com.ken.bookstore.bean.response.cart.BookCartItemResp;
import com.ken.bookstore.bean.response.cart.BookCartItemTotalResp;
import com.ken.bookstore.service.BookCartService;
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
@Tag(name = "API for shopping Cart items")
public class BookCartItemController {

    private final BookCartService bookCartService;

    @PostMapping("/public/cart/items")
    @Operation(summary = "add items from your shopping cart")
    public OperationResult<Boolean> addCartItem(@RequestBody @Valid AddCartItemReq request) {
        return OperationResult.success(bookCartService.addCartItem(request));
    }

    @DeleteMapping("/public/cart/items")
    @Operation(summary = "Remove items from your shopping cart")
    public OperationResult<Boolean> removeCartItem(@RequestBody @Valid RemoveCartItemReq request) {
        return OperationResult.success(bookCartService.removeCartItem(request));
    }

    @PutMapping("/public/cart/items")
    @Operation(summary = "Increase or decrease the number of items in the shopping cart")
    public OperationResult<Boolean> reduceCartItem(@RequestBody @Valid ReduceCartItemQtyReq request) {
        return OperationResult.success(bookCartService.reduceCartItem(request));
    }

    @GetMapping("/public/cart/items")
    @Operation(summary = "Get all shopping cart items")
    public OperationResult<List<BookCartItemResp>> listItems(@RequestParam("userId") Long userId) {
        return OperationResult.success(bookCartService.list(userId));
    }

    @GetMapping("/public/cart/detail")
    @Operation(summary = "Get details and total price of all shopping cart items")
    public OperationResult<BookCartItemTotalResp> itemsDetail(@RequestParam("userId") Long userId) {
        return OperationResult.success(bookCartService.itemsDetail(userId));
    }
}
