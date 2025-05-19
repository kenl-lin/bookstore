package com.ken.bookstore.bean.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author ken
 */
@Getter
@RequiredArgsConstructor
public enum OperationResultCode {


    EXIST_CATEGORY_NAME("100001", "There are the same names"),
    NOT_EXIST_CATEGORY("100002", "There is no category."),
    NOT_EXIST_BOOK_ID("200002", "Book Id does not exist."),
    NOT_EXIST_ITEM_ID("300001", "Cart item id does not exist."),
    REDUCE_QUANTITY_ERROR("300002", "The quantity that needs to be reduced exceeds the number of shopping carts."),
    /**
     * success code
     */
    SUCCESS("200", "success");

    private final String code;
    private final String message;
}
