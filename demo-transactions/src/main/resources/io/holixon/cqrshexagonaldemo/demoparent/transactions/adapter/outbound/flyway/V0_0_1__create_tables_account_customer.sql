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
    id              BIGINT       NOT NULL
        DEFAULT (NEXT VALUE FOR [transactions].account_seq),

    created         DATETIMEOFFSET(6),
    updated         DATETIMEOFFSET(6),
    created_by      VARCHAR(255),
    updated_by      VARCHAR(255),

    account_number  VARCHAR(255) NOT NULL,
    customer_number VARCHAR(255) NOT NULL,

    CONSTRAINT PK_ACCOUNT_ID PRIMARY KEY (id)
)
GO
-- ------------------------------------------------------------------------------#
CREATE TABLE [transactions].customer
(
    id              BIGINT       NOT NULL
        DEFAULT (NEXT VALUE FOR [transactions].customer_seq),
    created         DATETIMEOFFSET(6),
    updated         DATETIMEOFFSET(6),
    created_by      VARCHAR(255),
    updated_by      VARCHAR(255),

    customer_number VARCHAR(255) NOT NULL,

    CONSTRAINT PK_CUSTOMER_ID PRIMARY KEY (id)
)
GO
