CREATE SEQUENCE [command].link_seq
    START WITH 1
    INCREMENT BY 50
GO
-- ------------------------------------------------------------------------------#
CREATE SEQUENCE [command].search_result_item_seq
    START WITH 1
    INCREMENT BY 50
GO
-- ------------------------------------------------------------------------------#
CREATE SEQUENCE [command].data_item_seq
    START WITH 1
    INCREMENT BY 50
GO
-- ------------------------------------------------------------------------------#
CREATE TABLE [command].search_result_item
(
    id         BIGINT       NOT NULL
        DEFAULT (NEXT VALUE FOR [command].search_result_item_seq),
    created    DATETIMEOFFSET(6),
    updated    DATETIMEOFFSET(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),

    href       VARCHAR(255) NOT NULL,

    CONSTRAINT PK_ITEM_ID PRIMARY KEY (id)
)
GO
-- ------------------------------------------------------------------------------#
CREATE TABLE [command].data_item
(
    id                    BIGINT        NOT NULL
        DEFAULT (NEXT VALUE FOR [command].data_item_seq),
    created               DATETIMEOFFSET(6),
    updated               DATETIMEOFFSET(6),
    created_by            VARCHAR(255),
    updated_by            VARCHAR(255),

    center                VARCHAR(255)  NOT NULL,
    date_created          DATETIMEOFFSET(6),
    description           VARCHAR(8000) NOT NULL,
    keywords              VARCHAR(255)  NOT NULL,
    media_type            VARCHAR(255)  NOT NULL,
    nasa_id               VARCHAR(255)  NOT NULL,
    title                 VARCHAR(255)  NOT NULL,
    search_result_item_id BIGINT        NOT NULL

        CONSTRAINT PK_DATA_ITEM_ID PRIMARY KEY (id)
)
GO
-- ------------------------------------------------------------------------------#
ALTER TABLE [command].data_item
    ADD CONSTRAINT FK_DATA_ITEM__SEARCH_RESULT_ITEM FOREIGN KEY (search_result_item_id) REFERENCES [command].search_result_item (id)
GO
-- ------------------------------------------------------------------------------#

CREATE TABLE [command].link
(
    id           BIGINT       NOT NULL
        DEFAULT (NEXT VALUE FOR [command].link_seq),
    created      DATETIMEOFFSET(6),
    updated      DATETIMEOFFSET(6),
    created_by   VARCHAR(255),
    updated_by   VARCHAR(255),

    href         VARCHAR(255) NOT NULL,
    rel          VARCHAR(255) NOT NULL,
    render       VARCHAR(255) NULL,
    data_item_id BIGINT       NOT NULL
        CONSTRAINT PK_LINK_ID PRIMARY KEY (id)
)
GO
-- ------------------------------------------------------------------------------#
ALTER TABLE [command].link
    ADD CONSTRAINT FK_LINK__DATA_ITEM FOREIGN KEY (data_item_id) REFERENCES [command].data_item (id)
GO
-- ------------------------------------------------------------------------------#
