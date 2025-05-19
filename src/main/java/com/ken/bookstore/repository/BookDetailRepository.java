package com.ken.bookstore.repository;

import com.ken.bookstore.bean.entity.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ken
 */
@Repository
public interface BookDetailRepository extends JpaRepository<BookDetail, Long> {
}
