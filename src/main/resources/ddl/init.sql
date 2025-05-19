CREATE TABLE IF NOT EXISTS book_detail (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_id BIGINT,
    name             VARCHAR(255) NOT NULL,
    author           VARCHAR(255),
    price            DECIMAL(10, 2),
    created_by       BIGINT,
    created_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by       BIGINT,
    updated_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS book_category (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    created_by      BIGINT,
    created_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by      BIGINT,
    updated_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS book_cart (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id         BIGINT NOT NULL,
    book_id         BIGINT NOT NULL,
    quantity        BIGINT NOT NULL,
    createdBy       BIGINT,
    created_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by      BIGINT,
    updated_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);