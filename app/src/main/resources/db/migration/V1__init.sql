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

INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('DIRECTOR');
INSERT INTO roles (name) VALUES ('MANAGER');
INSERT INTO roles (name) VALUES ('SALES');
INSERT INTO roles (name) VALUES ('CONDUCTING');
INSERT INTO roles (name) VALUES ('PRODUCTION');
INSERT INTO roles (name) VALUES ('STORAGE');
INSERT INTO roles (name) VALUES ('MARKETING');

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
    last_name VARCHAR(255) NOT NULL,
    phone VARCHAR(255) UNIQUE NOT NULL,
    company_name VARCHAR(255)
);

INSERT INTO customers (
    email,
    first_name,
    last_name,
    phone
)
VALUES (
           'test2@mail.ru',
           'Ann',
           'Smith',
           '+79062347283'
       );

CREATE TABLE orders
(
    id SERIAL PRIMARY KEY,
    number SERIAL,
    status VARCHAR(255) NOT NULL,
    event_date VARCHAR(10) NOT NULL,
    event_reason VARCHAR(255) NOT NULL,
    persons_quantity INTEGER NOT NULL,
    event_type VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    comment VARCHAR(500),
    customer_id INTEGER NOT NULL,
    CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES customers (id)
);

INSERT INTO orders (
    status,
    event_date,
    event_reason,
    persons_quantity,
    event_type,
    address,
    comment,
    customer_id
)
VALUES (
           'DRAFT',
            '08.08.2021',
           'COMPANY_PARTY',
            35,
            'BANQUET',
            'д. Новорочье, дом 5',
            'площадка за озером',
            1
       );

CREATE TABLE menu_items
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    weight_output INTEGER NOT NULL,
    coast INTEGER NOT NULL
);

