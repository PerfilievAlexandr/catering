CREATE TABLE users
(
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL
);

INSERT INTO users (
                  email,
                  first_name,
                  last_name,
                  password,
                  status
                  )
    VALUES (
            'test1@mail.ru',
            'Tom',
            'Brown',
            '$2a$10$ySW0fA0S7B0KjcIw5RFrGej5/T12LrZFinV05enPD1rXcXxH6jNje',
            'ACTIVE'
            );

INSERT INTO users (
    email,
    first_name,
    last_name,
    password,
    status
)
VALUES (
           'test2@mail.ru',
           'Ann',
           'Smith',
           '$2a$10$ySW0fA0S7B0KjcIw5RFrGej5/T12LrZFinV05enPD1rXcXxH6jNje',
           'ACTIVE'
       );

CREATE TABLE roles
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('ADMIN');

CREATE TABLE user_roles
(
    user_id    int REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
    role_id    int REFERENCES roles (id) ON UPDATE CASCADE,
    CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id)
);

INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1);

CREATE TABLE refresh_tokens
(
    id SERIAL PRIMARY KEY,
    expiry_date TIMESTAMP NOT NULL,
    token VARCHAR(255) NOT NULL UNIQUE,
    user_id INTEGER,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE customers
(
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL
);

INSERT INTO customers (
    email,
    first_name,
    middle_name,
    last_name,
    phone
)
VALUES (
           'test2@mail.ru',
           'Ann',
           'Brown',
           'Smith',
            '+79062347283'
       );

CREATE TABLE orders
(
    id SERIAL PRIMARY KEY,
    order_number VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    customer_id INTEGER NOT NULL,
    CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES customers (id)
);

INSERT INTO orders (
    order_number,
    status,
    customer_id
)
VALUES (
           '1',
           'READY',
           1
       );

INSERT INTO orders (
    order_number,
    status,
    customer_id
)
VALUES (
           '2',
           'NOT_READY',
           1
       );
