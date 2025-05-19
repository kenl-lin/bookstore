package com.ken.bookstore.service.impl;

import com.ken.bookstore.bean.entity.BookCart;
import com.ken.bookstore.bean.entity.BookDetail;
import com.ken.bookstore.bean.enums.OperationResultCode;
import com.ken.bookstore.bean.request.cart.AddCartItemReq;
import com.ken.bookstore.bean.request.cart.ReduceCartItemQtyReq;
import com.ken.bookstore.bean.request.cart.RemoveCartItemReq;
import com.ken.bookstore.bean.response.cart.BookCartItemResp;
import com.ken.bookstore.bean.response.cart.BookCartItemTotalResp;
import com.ken.bookstore.common.exception.CustomerException;
import com.ken.bookstore.repository.BookCartRepository;
import com.ken.bookstore.repository.BookDetailRepository;
import com.ken.bookstore.service.BookCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ken
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookCartServiceImpl implements BookCartService {

    private final BookCartRepository bookCartRepository;
    private final BookDetailRepository bookDetailRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeCartItem(RemoveCartItemReq request) {
        Optional<BookCart> optionalBookCart = bookCartRepository.findByUserIdAndBookId(request.getUserId(), request.getBookId());
        if (!optionalBookCart.isPresent()) {
            throw new CustomerException(OperationResultCode.NOT_EXIST_BOOK_ID);
        }
        bookCartRepository.delete(optionalBookCart.get());
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addCartItem(AddCartItemReq request) {
        Optional<BookDetail> optionalBookDetail = bookDetailRepository.findById(request.getBookId());
        if (!optionalBookDetail.isPresent()) {
            throw new CustomerException(OperationResultCode.NOT_EXIST_BOOK_ID);
        }
        BookCart bookCart;
        Optional<BookCart> optionalBookCart = bookCartRepository.findByUserIdAndBookId(request.getUserId(), request.getBookId());
        if (optionalBookCart.isPresent()) {
            bookCart = optionalBookCart.get();
            bookCart.setQuantity(bookCart.getQuantity() + request.getQuantity());
            bookCart.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
        } else {
            bookCart = new BookCart();
            bookCart.setBookId(request.getBookId());
            bookCart.setUserId(request.getUserId());
            bookCart.setQuantity(request.getQuantity());
        }
        bookCartRepository.save(bookCart);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean reduceCartItem(ReduceCartItemQtyReq request) {
        Optional<BookCart> optionalBookCart = bookCartRepository.findByUserIdAndBookId(request.getUserId(), request.getBookId());
        if (!optionalBookCart.isPresent()) {
            throw new CustomerException(OperationResultCode.NOT_EXIST_BOOK_ID);
        }
        BookCart bookCart = optionalBookCart.get();
        if (request.getQuantity() < 0 && Math.abs(request.getQuantity()) > bookCart.getQuantity()) {
            throw new CustomerException(OperationResultCode.REDUCE_QUANTITY_ERROR);
        }
        if (request.getQuantity() < 0 && Math.abs(request.getQuantity()) == bookCart.getQuantity()) {
            bookCartRepository.delete(bookCart);
            return Boolean.TRUE;

        }
        bookCart.setQuantity(bookCart.getQuantity() + request.getQuantity());
        bookCart.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
        bookCartRepository.save(bookCart);
        return Boolean.TRUE;
    }

    @Override
    public List<BookCartItemResp> list(Long userId) {
        List<BookCart> bookCarts = bookCartRepository.findByUserId(userId);
        Map<Long, Long> bookMap = bookCarts.stream().collect(Collectors.toMap(BookCart::getBookId, BookCart::getQuantity));
        List<BookDetail> bookDetails = bookDetailRepository.findAllById(bookMap.keySet());
        List<BookCartItemResp> result = new ArrayList<>();
        for (BookDetail bookDetail : bookDetails) {
            BookCartItemResp node = new BookCartItemResp();
            node.setBookId(bookDetail.getId());
            node.setName(bookDetail.getName());
            node.setAuthor(bookDetail.getAuthor());
            node.setQuantity(bookMap.get(bookDetail.getId()));
            node.setPrice(bookDetail.getPrice());
            node.setTotalPrice(bookDetail.getPrice().multiply(new BigDecimal(node.getQuantity())));
            result.add(node);
        }
        return result;
    }

    @Override
    public BookCartItemTotalResp itemsDetail(Long userId) {
        List<BookCart> bookCarts = bookCartRepository.findByUserId(userId);
        Map<Long, Long> bookMap = bookCarts.stream().collect(Collectors.toMap(BookCart::getBookId, BookCart::getQuantity));
        List<BookDetail> bookDetails = bookDetailRepository.findAllById(bookMap.keySet());
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<BookCartItemResp> result = new ArrayList<>();
        for (BookDetail bookDetail : bookDetails) {
            BookCartItemResp node = new BookCartItemResp();
            node.setBookId(bookDetail.getId());
            node.setName(bookDetail.getName());
            node.setAuthor(bookDetail.getAuthor());
            node.setQuantity(bookMap.get(bookDetail.getId()));
            node.setPrice(bookDetail.getPrice());
            node.setTotalPrice(bookDetail.getPrice().multiply(new BigDecimal(node.getQuantity())));
            result.add(node);
            totalPrice = totalPrice.add(node.getTotalPrice());
        }
        return new BookCartItemTotalResp(result, totalPrice);
    }
}