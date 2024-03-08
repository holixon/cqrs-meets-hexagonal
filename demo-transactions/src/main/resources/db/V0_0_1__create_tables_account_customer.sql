CREATE SEQUENCE [transactions].account_seq
    START WITH 1
    INCREMENT BY 50
GO
-- ------------------------------------------------------------------------------#
CREATE SEQUENCE [transactions].customer_seq
    START WITH 1
    INCREMENT BY 50
GO
-- ------------------------------------------------------------------------------#

-- ------------------------------------------------------------------------------#
CREATE TABLE [transactions].account
(
    iban            VARCHAR(255) NOT NULL,
    customer_number VARCHAR(255) NOT NULL,

    CONSTRAINT PK_ACCOUNT_ID PRIMARY KEY (iban)
)
GO
-- ------------------------------------------------------------------------------#
CREATE TABLE [transactions].customer
(
    customer_number VARCHAR(255) NOT NULL,
    customer_name   VARCHAR(255) NOT NULL,

    CONSTRAINT PK_CUSTOMER_ID PRIMARY KEY (customer_number)
)
GO
