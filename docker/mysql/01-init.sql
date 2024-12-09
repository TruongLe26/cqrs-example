CREATE TABLE users (
    id        SERIAL PRIMARY KEY,
    firstname VARCHAR(50),
    lastname  VARCHAR(50),
    state     VARCHAR(10)
);

CREATE TABLE product (
    id          SERIAL PRIMARY KEY,
    description VARCHAR(500),
    price       NUMERIC(10,2) NOT NULL
);

CREATE TABLE purchase_order (
    id          SERIAL PRIMARY KEY,
    user_id     INTEGER REFERENCES users(id),
    product_id  INTEGER REFERENCES product(id),
    order_date  DATE
);