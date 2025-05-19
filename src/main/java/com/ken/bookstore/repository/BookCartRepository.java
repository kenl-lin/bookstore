package com.ken.bookstore.repository;

import com.ken.bookstore.bean.entity.BookCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author ken
 */
@Repository
public interface BookCartRepository extends JpaRepository<BookCart, Long> {

    Optional<BookCart> findByUserIdAndBookId(Long userId, Long bookId);
    List<BookCart> findByUserId(Long userId);
}
