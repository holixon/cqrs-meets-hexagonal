create table account_balance(
    iban VARCHAR(255) NOT NULL,
    balance DECIMAL NOT NULL,
    primary key (iban)
);
