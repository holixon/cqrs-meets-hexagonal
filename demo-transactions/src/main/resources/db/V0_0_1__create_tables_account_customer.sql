CREATE TABLE transactions.customer
(
    customer_number VARCHAR(255) NOT NULL,
    customer_name   VARCHAR(255) NOT NULL,

    CONSTRAINT PK_CUSTOMER_ID PRIMARY KEY (customer_number)
);

CREATE TABLE transactions.account
(
    iban            VARCHAR(255) NOT NULL,
    customer_number VARCHAR(255) NOT NULL,
    CONSTRAINT FK_CustomerNumber FOREIGN KEY (customer_number) REFERENCES customer(customer_number),
    CONSTRAINT PK_ACCOUNT_ID PRIMARY KEY (iban)
);
