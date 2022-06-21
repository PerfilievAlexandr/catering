CREATE TABLE customers (
     id int  NOT NULL    PRIMARY KEY,
     email   VARCHAR(100)    NOT NULL
);

INSERT INTO customers VALUES
                          (1, 'test1@mail.ru'),
                          (2, 'test2@mail.ru'),
                          (3, 'test3@mail.ru');
