CREATE TABLE product (
                         id INT AUTO_INCREMENT,
                         name VARCHAR(255) NOT NULL,
                         description VARCHAR(255),
                         price DECIMAL(10, 2) NOT NULL,
                         category VARCHAR(50),
                         stock INTEGER,
                         created TIMESTAMP NOT NULL,
                         lastUpdated TIMESTAMP NOT NULL,
                         PRIMARY KEY (id)
);

CREATE INDEX idx_product_category ON product (category);