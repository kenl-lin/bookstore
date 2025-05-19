package com.ken.bookstore.repository;

import com.ken.bookstore.bean.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ken
 */
@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
    boolean existsByName(String categoryName);

    List<BookCategory> findByNameContaining(String categoryName);
}
