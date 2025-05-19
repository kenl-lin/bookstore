package com.ken.bookstore.service.impl;

import com.ken.bookstore.bean.entity.BookCategory;
import com.ken.bookstore.bean.enums.OperationResultCode;
import com.ken.bookstore.bean.request.category.CreateBookCategoryReq;
import com.ken.bookstore.bean.request.category.QueryBookCategoryReq;
import com.ken.bookstore.bean.request.category.UpdateBookCategoryReq;
import com.ken.bookstore.bean.response.category.BookCategoryResp;
import com.ken.bookstore.common.exception.CustomerException;
import com.ken.bookstore.constant.SystemConstant;
import com.ken.bookstore.repository.BookCategoryRepository;
import com.ken.bookstore.service.BookCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ken
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookCategoryServiceImpl implements BookCategoryService {

    private final BookCategoryRepository bookCategoryRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BookCategoryResp create(CreateBookCategoryReq request) {
        // Check whether there are the same names
        boolean exist = bookCategoryRepository.existsByName(request.getCategoryName());
        if (exist) {
            throw new CustomerException(OperationResultCode.EXIST_CATEGORY_NAME);
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        BookCategory bookCategory = new BookCategory();
        bookCategory.setName(request.getCategoryName());
        bookCategory.setCreatedBy(SystemConstant.ADMIN_USER_ID);
        bookCategory.setUpdatedBy(SystemConstant.ADMIN_USER_ID);
        BookCategory saveResult = bookCategoryRepository.save(bookCategory);
        return from(saveResult);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BookCategoryResp update(UpdateBookCategoryReq request) {
        Optional<BookCategory> optional = bookCategoryRepository.findById(request.getCategoryId());
        if (!optional.isPresent()) {
            throw new CustomerException(OperationResultCode.NOT_EXIST_CATEGORY);
        }
        BookCategory bookCategory = optional.get();
        bookCategory.setName(request.getCategoryName());
        bookCategory.setUpdatedBy(SystemConstant.ADMIN_USER_ID);
        bookCategory.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
        return from(bookCategoryRepository.save(bookCategory));
    }

    @Override
    public List<BookCategoryResp> list(QueryBookCategoryReq queryBookCategoryReq) {
        if (Objects.isNull(queryBookCategoryReq.getCategoryName())) {
            return bookCategoryRepository.findAll()
                    .stream().map(BookCategoryServiceImpl::from).collect(Collectors.toList());
        }
        return bookCategoryRepository.findByNameContaining(queryBookCategoryReq.getCategoryName())
                .stream().map(BookCategoryServiceImpl::from).collect(Collectors.toList());
    }


    public static BookCategoryResp from(BookCategory entity) {
        BookCategoryResp detail = new BookCategoryResp();
        detail.setId(entity.getId());
        detail.setName(entity.getName());
        return detail;
    }
}
