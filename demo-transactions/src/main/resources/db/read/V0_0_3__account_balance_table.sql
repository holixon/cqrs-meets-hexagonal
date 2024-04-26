CREATE TABLE `cqrs-meets-hexagonal-read-db`.account_balance(
    iban VARCHAR(255) NOT NULL,
    balance DECIMAL NOT NULL,
    PRIMARY KEY (iban)
);
