CREATE TABLE `query`.account_balance
(
    iban    VARCHAR(255)   NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (iban)
);
