CREATE TABLE customers (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100)
);

CREATE TABLE products (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(100),
    unit_value NUMERIC(20, 2)
);

CREATE TABLE orders (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    customer_id INTEGER REFERENCES customers (id),
    order_date TIMESTAMP,
    order_value NUMERIC(20, 2)
);

CREATE TABLE order_items (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    order_id INTEGER REFERENCES orders (id),
    product_id INTEGER REFERENCES products (id),
    quantity INTEGER
);