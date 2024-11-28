
CREATE TABLE products (
    product_id SERIAL PRIMARY KEY,
    stock INT NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    tax_rate NUMERIC(4, 2) NOT NULL,
    name TEXT NOT NULL,
    description TEXT,
    expiry_date DATE,
    category TEXT,
    supplier TEXT
);