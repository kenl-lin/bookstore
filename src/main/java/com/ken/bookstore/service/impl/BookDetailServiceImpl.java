package com.ken.bookstore.service.impl;

import com.ken.bookstore.bean.entity.BookCategory;
import com.ken.bookstore.bean.entity.BookDetail;
import com.ken.bookstore.bean.enums.OperationResultCode;
import com.ken.bookstore.bean.request.book.CreateBookDetailReq;
import com.ken.bookstore.bean.request.book.QueryBookDetailReq;
import com.ken.bookstore.bean.request.book.UpdateBookDetailReq;
import com.ken.bookstore.bean.response.book.BookDetailResp;
import com.ken.bookstore.common.exception.CustomerException;
import com.ken.bookstore.constant.SystemConstant;
import com.ken.bookstore.repository.BookCategoryRepository;
import com.ken.bookstore.repository.BookDetailRepository;
import com.ken.bookstore.service.BookDetailService;
import com.ken.bookstore.utils.LambdaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ken
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookDetailServiceImpl implements BookDetailService {

    private final BookDetailRepository bookDetailRepository;
    private final BookCategoryRepository bookCategoryRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BookDetailResp create(CreateBookDetailReq request) {
        validCategory(request.getCategoryId());
        BookDetail bookDetail = new BookDetail();
        bookDetail.setCategoryId(request.getCategoryId());
        bookDetail.setName(request.getName());
        bookDetail.setAuthor(request.getAuthor());
        bookDetail.setPrice(request.getPrice());
        bookDetail.setCreatedBy(SystemConstant.ADMIN_USER_ID);
        bookDetail.setUpdatedBy(SystemConstant.ADMIN_USER_ID);
        return from(bookDetailRepository.save(bookDetail));
    }

    @Override
    public List<BookDetailResp> list(QueryBookDetailReq request) {
        BookDetail bookDetail = new BookDetail();
        bookDetail.setCategoryId(request.getCategoryId());
        bookDetail.setName(request.getName());
        bookDetail.setAuthor(request.getAuthor());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withMatcher(LambdaUtil.getFieldName(BookDetail::getAuthor), ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher(LambdaUtil.getFieldName(BookDetail::getName), ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher(LambdaUtil.getFieldName(BookDetail::getCategoryId), ExampleMatcher.GenericPropertyMatchers.exact());
        Example<BookDetail> example = Example.of(bookDetail, matcher);
        return bookDetailRepository.findAll(example).stream().map(BookDetailServiceImpl::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BookDetailResp update(UpdateBookDetailReq request) {
        Optional<BookDetail> optionalBookDetail = bookDetailRepository.findById(request.getId());
        if (!optionalBookDetail.isPresent()) {
            throw new CustomerException(OperationResultCode.NOT_EXIST_BOOK_ID);
        }
        validCategory(request.getCategoryId());
        BookDetail bookDetail = optionalBookDetail.get();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        bookDetail.setCategoryId(request.getCategoryId());
        bookDetail.setName(request.getName());
        bookDetail.setAuthor(request.getAuthor());
        bookDetail.setPrice(request.getPrice());
        bookDetail.setUpdatedBy(SystemConstant.ADMIN_USER_ID);
        bookDetail.setUpdatedTime(now);
        return from(bookDetailRepository.save(bookDetail));
    }

    public static BookDetailResp from(BookDetail entity) {
        BookDetailResp bookDetailResp = new BookDetailResp();
        bookDetailResp.setBookId(entity.getId());
        bookDetailResp.setCategoryId(entity.getCategoryId());
        bookDetailResp.setName(entity.getName());
        bookDetailResp.setAuthor(entity.getAuthor());
        bookDetailResp.setPrice(entity.getPrice());
        return bookDetailResp;
    }


    private void validCategory(Long request) {
        Optional<BookCategory> optionalBookCategory = bookCategoryRepository.findById(request);
        if (!optionalBookCategory.isPresent()) {
            throw new CustomerException(OperationResultCode.NOT_EXIST_CATEGORY);
        }
    }
}
