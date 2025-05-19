package com.ken.bookstore.bean.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author ken
 */
@Data
@Entity
public class BookCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * book category name
     */
    private String name;

    /**
     * Creator ID
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
     * update time
     */
    private Timestamp updatedTime;
}
