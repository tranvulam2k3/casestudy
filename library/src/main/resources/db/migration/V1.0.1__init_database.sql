use manager_library_n1224c1;

CREATE TABLE AUTHOR
(
    author_id INT AUTO_INCREMENT PRIMARY KEY,
    name      NVARCHAR(100),
    image     NVARCHAR(255),
    bio       NVARCHAR(255)
);

CREATE TABLE ROLE
(
    role_id     INT AUTO_INCREMENT PRIMARY KEY,
    role_name   NVARCHAR(100),
    description NVARCHAR(225)
);

CREATE TABLE `USER`
(
    user_id      INT AUTO_INCREMENT PRIMARY KEY,
    full_name    NVARCHAR(225),
    email        NVARCHAR(225),
    phone_number NVARCHAR(20),
    address      NVARCHAR(225),
    status       NVARCHAR(50)
);

CREATE TABLE BOOK
(
    book_id   INT AUTO_INCREMENT PRIMARY KEY,
    title     NVARCHAR(200),
    genre     NVARCHAR(100),
    quantity  INT,
    price     DECIMAL(10,2),
    status    NVARCHAR(100),
    image     NVARCHAR(255),
    author_id INT,
    FOREIGN KEY (author_id) REFERENCES AUTHOR (author_id)
);



CREATE TABLE USER_ROLE
(
    user_id INT,
    role_id INT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES `USER` (user_id),
    FOREIGN KEY (role_id) REFERENCES ROLE (role_id)
);



CREATE TABLE CART
(
    cart_id      INT AUTO_INCREMENT PRIMARY KEY,
    user_id      INT,
    created_date DATE,
    status       NVARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES `USER` (user_id)
);

CREATE TABLE CART_ITEM
(
    cart_item_id INT AUTO_INCREMENT PRIMARY KEY,
    cart_id      INT,
    book_id      INT,
    quantity     INT,
    FOREIGN KEY (cart_id) REFERENCES CART (cart_id),
    FOREIGN KEY (book_id) REFERENCES BOOK (book_id)
);

CREATE TABLE LOAN
(
    loan_id            INT AUTO_INCREMENT PRIMARY KEY,
    user_id            INT,
    book_id            INT,
    borrow_date        DATE,
    due_date           DATE,
    return_date        DATE,
    transaction_status NVARCHAR(100),
    fine_amount        DECIMAL(10,2),
    FOREIGN KEY (user_id) REFERENCES `USER` (user_id),
    FOREIGN KEY (book_id) REFERENCES BOOK (book_id)
);

CREATE TABLE VIOLATION
(
    violation_id   INT AUTO_INCREMENT PRIMARY KEY,
    user_id        INT,
    loan_id        INT,
    violation_type NVARCHAR(100),
    description    NVARCHAR(225),
    fine_amount    DECIMAL(10,2),
    penalty_point  INT,
    violation_date DATE,
    status         NVARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES `USER`(user_id),
    FOREIGN KEY (loan_id) REFERENCES LOAN (loan_id)
);

CREATE TABLE INCIDENT_REPORT
(
    report_id            INT AUTO_INCREMENT PRIMARY KEY,
    user_id              INT,
    incident_description NVARCHAR(225),
    report_type          NVARCHAR(100),
    report_date          DATE,
    handling_status      NVARCHAR(100),
    feedback             NVARCHAR(225),
    FOREIGN KEY (user_id) REFERENCES `USER`(user_id)
);

CREATE TABLE INVENTORY_TRANSACTION
(
    transaction_id   INT AUTO_INCREMENT PRIMARY KEY,
    book_id          INT,
    transaction_type NVARCHAR(100),
    quantity         INT,
    transaction_date DATE,
    description      NVARCHAR(225),
    FOREIGN KEY (book_id) REFERENCES BOOK(book_id)
);

CREATE TABLE MONTHLY_REPORT
(
    report_id     INT AUTO_INCREMENT PRIMARY KEY,
    report_period DATE,
    total_in      INT,
    total_out     INT,
    revenue       DECIMAL(10,2),
    created_date  DATE
);
