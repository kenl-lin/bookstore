package com.ken.bookstore.bean.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author ken
 */
@Data
@Entity
public class BookCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * user Id
     */
    private Long userId;

    /**
     * book id
     */
    private Long bookId;

    /**
     * The quantity of books
     */
    private Long quantity;
    private Timestamp createdTime;
    private Timestamp updatedTime;

}
