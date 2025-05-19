MERGE INTO book_category (name, created_by, updated_by)
    KEY (name)
VALUES
    ('Java',  -1, -1),
    ('Mysql', -1, -1);

MERGE INTO book_detail (category_id, name, author, price, created_by, updated_by)
    KEY (name)
VALUES
    (1, 'Spring Boot', 'Test1', 59.99, -1, -1),
    (1, 'Java', 'Test2', 79.50, -1, -1),
    (1, 'Spring Cloud', 'Test3', 65.00, -1,  -1),
    (2, 'MySQL ', 'Test3', 49.00, -1,  -1);