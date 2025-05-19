package com.ken.bookstore.bean.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author ken
 */
@Data
@Entity
public class BookDetail {

    /**
     * book id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * book category id
     */
    private Long categoryId;

    /**
     * book name
     */
    private String name;

    /**
     * author name.
     */
    private String author;

    /**
     * price.
     */
    private BigDecimal price;

    /**
     * creator id
     */
    private Long createdBy;

    /**
     * created time
     */
    private Timestamp createdTime;

    /**
     * update id
     */
    private Long updatedBy;

    /**
     * updated time
     */
    private Timestamp updatedTime;

}
